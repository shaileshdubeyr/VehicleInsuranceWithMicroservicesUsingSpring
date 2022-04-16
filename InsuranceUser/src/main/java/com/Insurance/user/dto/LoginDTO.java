package com.Insurance.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
    @NotBlank(message = "Eamil not blank")
    public String email;
    @Max(15)
    @NotBlank(message = "password not blank")
    public String password;
    public  String token;
}
