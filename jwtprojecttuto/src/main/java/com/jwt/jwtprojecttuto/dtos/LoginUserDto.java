package com.jwt.jwtprojecttuto.dtos;

import lombok.Data;

@Data
public class LoginUserDto {
    private String email;
    
    private String password;
}