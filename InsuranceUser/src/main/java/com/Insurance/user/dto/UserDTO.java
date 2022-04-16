package com.Insurance.user.dto;

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
public class UserDTO {
    @Size(min = 5, max = 50, message = "full name should be grater or equal to 5 charatar")
    public String fullName;
    public String permanentAddress;
    public String temporaryAddress;
    @Min(value = 10, message = "Mobile Number not grater than 10 digit")
    public String mobileNumber;
    @NotBlank(message = "the mail not blank")
    public String email;
    @Size(min = 8, max = 20, message = "password min 8 charater max 20")
    public String password;
    @Min(18)
    public int age;
    public String occupation;
    public String familyBackground;
    public String kyc;
    public String healthCondition;
    public String vehicleData;
    public Date date = new Date(System.currentTimeMillis());
}
