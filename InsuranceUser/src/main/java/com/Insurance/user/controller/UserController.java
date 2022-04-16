package com.Insurance.user.controller;

import com.Insurance.user.dto.LoginDTO;
import com.Insurance.user.dto.ResponseDTO;
import com.Insurance.user.dto.UserDTO;
import com.Insurance.user.model.UserData;
import com.Insurance.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@EnableDiscoveryClient
public class UserController {
    @Autowired
    UserService userService;


    @PostMapping("/registration")
    public ResponseEntity<ResponseDTO> registerUser(@RequestBody @Valid UserDTO userDTO){
        //publishing data to queue
        return userService.saveUser(userDTO);
    }

    /**
     *
     * @param loginDTO
     * @return ResponseDTO status to the user
     * @throws NoSuchFieldException
     */
    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody @Valid LoginDTO loginDTO) throws NoSuchFieldException {
        return userService.login(loginDTO.email, loginDTO.password, loginDTO.token);
    }

    /**
     *
     * @param forgetDTO
     * @param token for verification user
     * @return ResponseDTO status to the user
     */
    @PostMapping("/forgetPassword/{token}")
    public ResponseEntity<ResponseDTO> forgetPassword(@RequestBody @Valid LoginDTO forgetDTO, @RequestParam String token){
        return  userService.forgetPassword(forgetDTO, token);
    }

    /**
     *
     * @param resetPasswordDTO
     * @return ResponseDTO status to the user
     */
    @PostMapping("/reset/password")
    public ResponseEntity<ResponseDTO> resetPassword(@RequestBody @Valid LoginDTO resetPasswordDTO) {
        return userService.resetPassword(resetPasswordDTO);
    }

    /**
     *
     * @param token for verification user
     * @return ResponseDTO status to the user
     * @Puropse to retrive all the user data from database
     */
    @GetMapping("/getAllUsers")
    public  ResponseEntity<ResponseDTO> getAllById(@RequestParam String token){
        return userService.getAllUsersById(token);
    }

    /**
     *
     * @param token for verification user
     * @return ResponseDTO status to the user
     * @Puropse to retrive the user data from database by id
     */
    @GetMapping("/getById")
    public ResponseEntity<ResponseDTO> getById(@RequestParam String token){
        return userService.getUserById(token);
    }

    /**
     *
     * @param token for verification user
     * @return ResponseDTO status to the user
     * @Purpose to verify the user with token
     */
    @GetMapping("/verify/{token}")
    public ResponseEntity<ResponseDTO> verify(@PathVariable String token){
        System.out.println(token.toString());
        return userService.verify(token);
    }

    /**
     *
     * @param id deleting the user with the id
     * @return ResponseDTO to the user
     * @purpose to delete the user if id is present
     */
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteUser(@RequestParam int id){
        return userService.deleteUser(id);
    }

    // RestTempletCalll for the user
    @GetMapping("/ApiById/{id}")
    public UserData getApiById(@PathVariable int id){
        System.out.println("id is "+id);
        return userService.getUserByIdForAPI(id);
    }
}
