package com.vehicleinsurance.insurance.model;

import com.vehicleinsurance.insurance.dto.CategoryData;
import com.vehicleinsurance.insurance.dto.InsuranceDTO;
import com.vehicleinsurance.insurance.dto.UserData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "insurance_final")
public class InsuranceData {
    @Id
    @GeneratedValue
    private int id;
    private int userId;
    private int categoryId ;

    public InsuranceData(InsuranceDTO dto){
        this.userId = dto.getUserId();
        this.categoryId = dto.getCategoryId();
    }

    public InsuranceData(Integer id, InsuranceDTO dto){
        this.id = id;
        this.userId = dto.getUserId();
        this.categoryId = dto.getCategoryId();
    }
}
