package com.Insurance.user.service;

import com.Insurance.user.dto.LoginDTO;
import com.Insurance.user.dto.ResponseDTO;
import com.Insurance.user.dto.UserDTO;
import com.Insurance.user.model.UserData;
import org.springframework.http.ResponseEntity;

public interface IUserService {
    public ResponseEntity<ResponseDTO> saveUser(UserDTO userDTO);

    public ResponseEntity<ResponseDTO> getUserById(String token);

    public ResponseEntity<ResponseDTO> UpdateData(String token, UserDTO userDTO);

    public ResponseEntity<ResponseDTO> getAllUsersById(String token);

    public ResponseEntity<ResponseDTO> login(String email, String password, String token) throws NoSuchFieldException;

    ResponseEntity<ResponseDTO> verify(String token);

    ResponseEntity<ResponseDTO> forgetPassword(LoginDTO forgetDTO, String token);

    ResponseEntity<ResponseDTO> resetPassword(LoginDTO resetPasswordDTO);

    ResponseEntity<ResponseDTO> deleteUser(int id);

    UserData getUserByIdForAPI(int id);
}