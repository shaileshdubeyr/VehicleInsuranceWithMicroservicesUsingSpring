package com.Insurance.user.model;

import com.Insurance.user.dto.UserDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "InsuranceUserTable")
@Data
@NoArgsConstructor
public class UserData {
    @Id
    @GeneratedValue
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

    public UserData(UserDTO userDTO){
        this.fullName = userDTO.fullName;
        this.permanentAddress = userDTO.permanentAddress;
        this.temporaryAddress = userDTO.temporaryAddress;
        this.mobileNumber = userDTO.mobileNumber;
        this.email = userDTO.email;
        this.password = userDTO.password;
        this.age = userDTO.age;
        this.occupation = userDTO.occupation;
        this.familyBackground = userDTO.familyBackground;
        this.kyc = userDTO.kyc;
        this.healthCondition = userDTO.healthCondition;
        this.vehicleData = userDTO.vehicleData;
        this.date = userDTO.date;
        this.verified = false;
    }

}
