package com.vehicleinsurance.insurance.exception;

import com.vehicleinsurance.insurance.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class InsuranceCategoryException {

    @ExceptionHandler(com.vehicleinsurance.insurance.exception.InsuranceException.class)
    public ResponseEntity<ResponseDTO> userNotFound(com.vehicleinsurance.insurance.exception.InsuranceException message){
        ResponseDTO responseDTO = new ResponseDTO("exception while processing rest request", message.getMessage());
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.BAD_REQUEST);
    }
}
