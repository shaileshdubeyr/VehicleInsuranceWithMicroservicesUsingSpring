package com.vehicleinsurance.insurance.service;

import com.vehicleinsurance.insurance.dto.CategoryData;
import com.vehicleinsurance.insurance.dto.InsuranceDTO;
import com.vehicleinsurance.insurance.dto.ResponseDTO;
import com.vehicleinsurance.insurance.dto.UserData;
import com.vehicleinsurance.insurance.exception.InsuranceException;
import com.vehicleinsurance.insurance.model.InsuranceData;
import com.vehicleinsurance.insurance.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService implements IVehicleService {
    @Autowired
    VehicleRepository repository;

    @Autowired
    RestTemplate template;

    /**
     *
     * @param dto holds the metadata of the user insurance
     * @return the response of the database operation with message
     */
    @Override
    public ResponseEntity<ResponseDTO> saveInsurance(InsuranceDTO dto) {
        CategoryData responseCategory = template.getForObject("http://categoryservice/category/ApiById/" + dto.getCategoryId(), CategoryData.class);
        UserData responseUser = template.getForObject("http://userservice/user/ApiById/" + dto.getUserId(), UserData.class);
        if(responseCategory.equals(null) || responseUser.equals(null)){
            throw new InsuranceException("the end category is" +responseCategory + "the user obj is "+responseUser);
        }
        InsuranceData insuranceData = repository.save(new InsuranceData(dto));
        ResponseDTO responseDTO = new ResponseDTO("save success", insuranceData);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    /**
     * @param id to get the insurance
     * @return response of the user with confirmation message
     */
    @Override
    public ResponseEntity<ResponseDTO> getInsuranceById(int id) {
        Optional<InsuranceData> data =  repository.findById(id);
        if (data.isEmpty()){
            throw new InsuranceException("the record is not match with this id"+id);
        }
        ResponseDTO responseDTO = new ResponseDTO("get call success", data);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    /**
     * @param @Null
     * @return the responseDTO get all insurance if found
     */
    @Override
    public ResponseEntity<ResponseDTO> getAllInsuranceById() {
       List<InsuranceData> list = repository.findAll();
       if (list.isEmpty()){
           throw new InsuranceException("No one record is match");
       }
       ResponseDTO responseDTO = new ResponseDTO("get all success", list);
       return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    /**
     * @param id to get the category
     * @param dto metadata of the user
     * @return the responseDTO if data updated
     */
    @Override
    public ResponseEntity<ResponseDTO> updateInsuranceDetails(int id, InsuranceDTO dto) {
        CategoryData responseCategory = template.getForObject("http://categoryservice/category/ApiById/" + dto.getCategoryId(), CategoryData.class);
        UserData responseUser = template.getForObject("http://userservice/user/ApiById/" + dto.getUserId(), UserData.class);
        if (responseCategory.equals(null) || responseUser.equals(null)){
            throw new InsuranceException("the category id not found"+responseCategory + "the user is not match" +responseUser);
        }
        repository.save(new InsuranceData(id,dto));
        ResponseDTO responseDTO = new ResponseDTO("update success", dto);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    /**
     * @param id to delete the insurance
     * @return the responseDTO if id insurance delete
     */
    @Override
    public ResponseEntity<ResponseDTO> deleteInsurance(int id) {
         repository.deleteById(id);
         ResponseDTO responseDTO = new ResponseDTO("delete success", id);
         return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
}
