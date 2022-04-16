package com.insurance.category.model;

import com.insurance.category.dto.CategoryDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "insuranceCategory")
public class CategoryData {
    @Id
    @GeneratedValue
    private Integer insuranceId;
    private String insuranceName;
    private String insuranceStatus;
    private String insuranceScheme;
    private String insuranceCode;
    private String insurancePeriod;
    private LocalDate date = LocalDate.now();
    private LocalTime time = LocalTime.now();

    public CategoryData(CategoryDTO insuranceDTO){
        this.insuranceName = insuranceDTO.insuranceName;
        this.insuranceStatus = insuranceDTO.insuranceStatus;
        this.insuranceScheme = insuranceDTO.insuranceScheme;
        this.insuranceCode = insuranceDTO.insuranceCode;
        this.insurancePeriod = insuranceDTO.insurancePeriod;

    }
}
