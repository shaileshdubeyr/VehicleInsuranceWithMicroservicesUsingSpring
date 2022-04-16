package com.vehicleinsurance.insurance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserData {
    private int id;
    private String fullName;
    private String permanentAddress;
    private String temporaryAddress;
    private String mobileNumber;
    private String email;
    private String password;
    private int age;
    private String occupation;
    private String familyBackground;
    private String kyc;
    private String healthCondition;
    private String vehicleData;
    private Date date;
    private Boolean verified;
}
