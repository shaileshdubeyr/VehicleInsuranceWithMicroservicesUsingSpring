package com.insurance.category.exception;

import com.insurance.category.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class InsuranceCategoryException {

    @ExceptionHandler(com.insurance.category.exception.InsuranceException.class)
    public ResponseEntity<ResponseDTO> userNotFound(com.insurance.category.exception.InsuranceException message){
        ResponseDTO responseDTO = new ResponseDTO("exception whilw processing rest request", message.getMessage(),"token not verified");
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.BAD_REQUEST);
    }
}
