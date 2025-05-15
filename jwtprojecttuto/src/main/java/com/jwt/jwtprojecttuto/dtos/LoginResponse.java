package com.jwt.jwtprojecttuto.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LoginResponse {
    private String token;

    private Long expiresIn;

    public String getToken() {
        return token;
    }
}