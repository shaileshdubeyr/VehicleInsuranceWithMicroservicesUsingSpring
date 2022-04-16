package com.insurance.category.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    @NotBlank(message = "name is mednetory")
    public String insuranceName;
    public String insuranceStatus;
    public String insuranceScheme;
    @NotBlank(message = "insurance code in madnetory")
    public String insuranceCode;
    @NotBlank(message = "insurance period is  not blank")
    public String insurancePeriod;
}
