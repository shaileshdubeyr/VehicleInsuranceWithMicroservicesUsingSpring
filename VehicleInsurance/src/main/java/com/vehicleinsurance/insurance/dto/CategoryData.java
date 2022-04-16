package com.vehicleinsurance.insurance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryData {
    public int id;
    public String insuranceName;
    public String insuranceStatus;
    public String insuranceScheme;
    public String insuranceCode;
    public String insurancePeriod;
}
