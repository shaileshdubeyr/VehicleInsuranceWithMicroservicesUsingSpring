package com.insurance.category.controller;

import com.insurance.category.dto.CategoryDTO;
import com.insurance.category.dto.ResponseDTO;
import com.insurance.category.model.CategoryData;
import com.insurance.category.service.CategoryInsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@EnableDiscoveryClient
@RequestMapping("/category")
public class InsuranceCategoryController {
    @Autowired
    CategoryInsuranceService service;

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> saveInsurance(@RequestBody @Valid CategoryDTO categoryDTO){
        return service.saveInsurance(categoryDTO);
    }

    @PutMapping("/update/{token}")
    public ResponseEntity<ResponseDTO> updateInsurance(@PathVariable String token, @RequestBody @Valid CategoryDTO insuranceDTO){
        System.out.println("update");
        return service.UpdatInsuranceDetails(token, insuranceDTO);
    }

    @GetMapping("/getAllUsers")
    public  ResponseEntity<ResponseDTO> getAllCategoryById(@RequestParam String token){
        return service.getAllInsuranceById(token);
    }

    @GetMapping("/getById")
    public ResponseEntity<ResponseDTO> getById(@RequestParam("token") String token){
        return service.getInsuranceById(token);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteUser(@PathVariable int id){
        return service.deleteInsurance(id);
    }

    //API call by id for RestTemplet
    @GetMapping("/ApiById/{id}")
    public CategoryData getForAPI(@PathVariable int id){
        return service.getForApiById(id);
    }
}
