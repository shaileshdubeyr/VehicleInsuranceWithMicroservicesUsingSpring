package com.Insurance.user.service;

import com.Insurance.user.configuration.RabbitMQConfig;
import com.Insurance.user.dto.LoginDTO;
import com.Insurance.user.dto.ResponseDTO;
import com.Insurance.user.dto.UserDTO;
import com.Insurance.user.exception.UserException;
import com.Insurance.user.model.UserData;
import com.Insurance.user.repository.UserRepository;
import com.Insurance.user.util.UserUtility;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserUtility userUtility;


    @Autowired
    EmailService emailService;


    /**
     *
     * @param userDTO holds the meta data of the user
     * @return the response of the database operation with message
     */
    @Override
    public ResponseEntity<ResponseDTO> saveUser(UserDTO userDTO) {
        //creating object of the user and saving into database
        UserData userData = userRepository.save( new UserData(userDTO));
        //generated token for verification
        String token = userUtility.createToken(userData.getId());
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_A, userData);
        //creating response object for the user
        ResponseDTO responseDTO =  new ResponseDTO("data saved with", userData, token);
        System.out.println(responseDTO);
        //returning the response to the user
        return new  ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    /**
     * @param token to verify the user
     * @return response of the user with confirmation message
     */
    @Override
    public ResponseEntity<ResponseDTO> getUserById(String token) {
        //decoding the token for verification of the user and will get user id
        int id_token = userUtility.decodeToken(token);
        System.out.println(id_token);
        //retriving the data from the databse with the user id
        Optional<UserData> userData = userRepository.findById(id_token);
        //creating the response object
        ResponseDTO responseDTO = new ResponseDTO("get call success", userData, " "+id_token);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

        /**
         * @param token to verify the user
         * @param userDTO metadata of the user
         * @return the responseDTO if data updated
         */
    @Override
    public ResponseEntity<ResponseDTO> UpdateData(String token, UserDTO userDTO) {
        //creating the userDATA object to update the data
        UserData userData = new UserData(userDTO);
        //decoding the token for user verification
        int token_id = userUtility.decodeToken(token);
        //setting the user id to update data particular user
        userData.setId(token_id);
        System.out.println("the token is "+token_id);
        //updating the data into database
        userRepository.save(userData);
        //creating the response object
        ResponseDTO responseDTO =  new ResponseDTO("data saved with ", userData, "token decoded");
        //returning the response object if data update successfully
        return new  ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    /**
     * @param token to verify the user
     * @return the responseDTO if all user found
     */
    @Override
    public ResponseEntity<ResponseDTO> getAllUsersById(String token) {
        //taking the list of the user from database
        List<UserData> userData = userRepository.findAll();
        //creating the response of the object
        ResponseDTO responseDTO = new ResponseDTO("get all", userData, "verify user by ");
        //returning the response object if data found successfully
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    /**
     *
     * @param email user email for login
     * @param password user password for login
     * @param token to verify the user
     * @return the responseDTO if user logged in success
     * @throws NoSuchFieldException if user not found it will throw exception
     */
    @Override
    public ResponseEntity<ResponseDTO> login(String email, String password, String token) throws NoSuchFieldException {
        //finding the user with unique Emailid
        UserData userData = userRepository.findByemail(email);
        //decoding the token for verification
        int tokId = userUtility.decodeToken(token);
        //checking if email, password and id match then login the uer otherwise throw exception
        if(userData.getEmail().equals(email) && userData.getPassword().equals(password) && tokId == userData.getId()) {
            String token_generation = userUtility.createToken(userData.getId());
            System.out.println(userData);
            //creating the response object
            ResponseDTO responseDTO = new ResponseDTO("login success", userData, token_generation);
            //returning the response object
            return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
        } else {
            //throwing exception if user not match
            throw new NoSuchFieldException("no data found");
        }
    }

    /**
     *
     * @param token to verify the user
     * @return the response of the object if user is verified
     */
    @Override
    public ResponseEntity<ResponseDTO> verify(String token) {
        //decoding the token and with user id retriving the user data
        Optional<UserData> userData = userRepository.findById(userUtility.decodeToken(token));
        //checking user is present or not if not then throwing custom exception
        if (userData.isEmpty()) {
            throw new UserException("user not found and verification not done");
        }
        //setting the true if user is present
        userData.get().setVerified(true);
        //saving the data in the database with userid
        userRepository.save(userData.get());
        //creating the responseDTO
        ResponseDTO responseDTO = new ResponseDTO(" The employee has been verified ", userData, token);
        //returning the response of the user
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    /**
     *
     * @param forgetDTO new password containing the object
     * @param token to verifi the user
     * @return ResponseDTO return the response if password in changed
     */
    @Override
    public ResponseEntity<ResponseDTO> forgetPassword(LoginDTO forgetDTO, String token) {
        //user finding by email
        UserData userData = userRepository.findByemail(forgetDTO.email);
        //calling the metdod to execute forget password work
        emailService.forgetPassword(userData,token);
        return null;
    }

    /**
     *
     * @param resetPasswordDTO have the new password
     * @return ResponseDto return the object with changed password
     * @purpose to change the password
     */
    @Override
    public ResponseEntity<ResponseDTO> resetPassword(LoginDTO resetPasswordDTO) {
        //getting the user
        Optional<UserData> userData =  userRepository.findById(userUtility.decodeToken(resetPasswordDTO.token));
        //seeting the password
        userData.get().setPassword(resetPasswordDTO.password);
        //saving the user with new password
        userRepository.save(userData.get());
        //creating the responseDTO
        ResponseDTO responseDTO = new ResponseDTO("password reset successful", userData, resetPasswordDTO.token);
        //returning the responseDTO
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    /**
     *
     * @param id to fetch the user
     * @return the ResponseDTO if user deleted
     * @Purpose to delete the user
     */
    @Override
    public ResponseEntity<ResponseDTO> deleteUser(int id) {
        //deleting the user by id
        userRepository.deleteById(id);
        ResponseDTO responseDTO = new ResponseDTO("delete success by id ", id, "");
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @Override
    public UserData getUserByIdForAPI(int id) {
        Optional<UserData> user = userRepository.findById(id);
        if(user.isPresent())
            return user.get();
        throw new UserException("the user not match with record with this id");
    }
}
