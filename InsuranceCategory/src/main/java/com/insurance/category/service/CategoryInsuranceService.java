package com.insurance.category.service;

import com.insurance.category.dto.CategoryDTO;
import com.insurance.category.dto.ResponseDTO;
import com.insurance.category.exception.InsuranceException;
import com.insurance.category.model.CategoryData;
import com.insurance.category.repositoty.InsuranceRepository;
import com.insurance.category.util.CategoryUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryInsuranceService implements ICategoryService {
    @Autowired
    InsuranceRepository repository;

    @Autowired
    CategoryUtility utility;

    @Override
    public ResponseEntity<ResponseDTO> saveInsurance(CategoryDTO insuranceDTO) {
        //checking here for duplicate record
        if (repository.findByinsuranceCode().equals(null)) {
            //if no record with this id the saving to database
            CategoryData insuranceData = repository.save(new CategoryData(insuranceDTO));
            String token = utility.createToken(insuranceData.getInsuranceId());
            ResponseDTO responseDTO = new ResponseDTO("save sucess", insuranceData, token);
            return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
        }
        throw new InsuranceException("this insurance is present");
    }

    @Override
    public ResponseEntity<ResponseDTO> getInsuranceById(String token) {

        //decoding the token for verification of the user and will get user id
        int id_token = utility.decodeToken(token);
        System.out.println(id_token);
        Optional<CategoryData> userData = repository.findById(id_token);
        if (userData.isEmpty()) {
            throw new InsuranceException("the User data is not found in database");
        }
        //creating the response object
        ResponseDTO responseDTO = new ResponseDTO("get call success", userData, token);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDTO> getAllInsuranceById(String token) {
        //taking the list of the user from database
        List<CategoryData> insuranceData = repository.findAll();
        if(insuranceData.isEmpty()){
            throw new InsuranceException("No one insurance register");
        }
        //creating the response of the object
        ResponseDTO responseDTO = new ResponseDTO("get all", insuranceData, "verify user by ");
        //returning the response object if data found successfully
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    @Override
    public CategoryData getForApiById(int id) {
        //cheeking particular record
        Optional<CategoryData> categoryData =  repository.findById(id);
        if(categoryData.isEmpty())
            throw new InsuranceException("No record found with this id");
        return categoryData.get();
    }

    @Override
    public ResponseEntity<ResponseDTO> UpdatInsuranceDetails(String token, CategoryDTO insuranceDTO) {
        CategoryData insuranceData = new CategoryData(insuranceDTO);
        if(insuranceData.equals(null)){
            throw new InsuranceException("data is not found to update");
        }
        //decoding the token for user verification
        int token_id = utility.decodeToken(token);
        //setting the user id to update data particular user
        insuranceData.setInsuranceId(token_id);
        System.out.println("the token is "+token_id);
        //updating the data into database
        repository.save(insuranceData);
        //creating the response object
        ResponseDTO responseDTO =  new ResponseDTO("data saved with ", insuranceData, "token decoded");
        //returning the response object if data update successfully
        return new  ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDTO> deleteInsurance(int id) {
        //deleting particular if match with id
        if(repository.findById(id).isPresent()){
            repository.deleteById(id);
            ResponseDTO responseDTO = new ResponseDTO("delete success by id ", id, "");
            return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
        }
        //if no record match the throwing exception
        throw new InsuranceException("the record is not found with this id to delete");
    }
}
