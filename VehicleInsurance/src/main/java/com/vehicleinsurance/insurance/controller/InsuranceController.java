package com.vehicleinsurance.insurance.controller;

import com.vehicleinsurance.insurance.dto.InsuranceDTO;
import com.vehicleinsurance.insurance.dto.ResponseDTO;
import com.vehicleinsurance.insurance.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/insurance")
@EnableDiscoveryClient
public class InsuranceController {

    @Autowired
    VehicleService service;

    @PostMapping("/registerInsurance")
    public ResponseEntity<ResponseDTO> getInsurance(@RequestBody InsuranceDTO dto){
        return service.saveInsurance(dto);
    }

    @GetMapping("/getInsurance")
    public ResponseEntity<ResponseDTO> getAllRegisterInsurance(){
        return service.getAllInsuranceById();
    }

    @GetMapping("/getById")
    public ResponseEntity<ResponseDTO> getById(@PathVariable int id){
        return service.getInsuranceById(id);
    }

    @PutMapping("/upDate")
    public ResponseEntity<ResponseDTO> upDatebyId(@PathVariable int id, @RequestBody InsuranceDTO dto){
        return service.updateInsuranceDetails(id, dto);
    }
    @DeleteMapping("/deleteInsurance")
    public ResponseEntity<ResponseDTO> deleteInsurance(@PathVariable int id){
        return service.deleteInsurance(id);
    }
}
