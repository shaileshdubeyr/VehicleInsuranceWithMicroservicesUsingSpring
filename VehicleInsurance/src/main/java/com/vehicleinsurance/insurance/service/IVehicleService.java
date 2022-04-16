package com.vehicleinsurance.insurance.service;

import com.vehicleinsurance.insurance.dto.CategoryData;
import com.vehicleinsurance.insurance.dto.InsuranceDTO;
import com.vehicleinsurance.insurance.dto.ResponseDTO;
import com.vehicleinsurance.insurance.dto.UserData;
import org.springframework.http.ResponseEntity;

public interface IVehicleService {
    public ResponseEntity<ResponseDTO> saveInsurance(InsuranceDTO dto);
    public ResponseEntity<ResponseDTO> getInsuranceById(int id);
    public ResponseEntity<ResponseDTO> getAllInsuranceById();
    public ResponseEntity<ResponseDTO> updateInsuranceDetails(int id, InsuranceDTO dto);
    ResponseEntity<ResponseDTO> deleteInsurance(int id);

}
