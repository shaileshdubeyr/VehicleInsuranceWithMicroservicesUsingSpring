package com.insurance.category.service;

import com.insurance.category.dto.CategoryDTO;
import com.insurance.category.dto.ResponseDTO;
import com.insurance.category.model.CategoryData;
import org.springframework.http.ResponseEntity;

public interface ICategoryService {
    public ResponseEntity<ResponseDTO> saveInsurance(CategoryDTO insuranceDTO);
    public ResponseEntity<ResponseDTO> getInsuranceById(String token);
    public ResponseEntity<ResponseDTO> getAllInsuranceById(String token);
    public ResponseEntity<ResponseDTO> UpdatInsuranceDetails(String token, CategoryDTO insuranceDTO);
    ResponseEntity<ResponseDTO> deleteInsurance(int id);

    CategoryData getForApiById(int id);
}
