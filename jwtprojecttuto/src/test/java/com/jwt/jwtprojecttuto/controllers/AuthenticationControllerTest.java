package com.jwt.jwtprojecttuto.controllers;


import com.jwt.jwtprojecttuto.dtos.LoginResponse;
import com.jwt.jwtprojecttuto.dtos.LoginUserDto;
import com.jwt.jwtprojecttuto.dtos.RegisterUserDto;
import com.jwt.jwtprojecttuto.entities.User;
import com.jwt.jwtprojecttuto.services.AuthenticationService;
import com.jwt.jwtprojecttuto.services.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AuthenticationControllerTest {

    private JwtService jwtService;
    private AuthenticationService authenticationService;
    private AuthenticationController authenticationController;

    @BeforeEach
    void setUp() {
        jwtService = mock(JwtService.class);
        authenticationService = mock(AuthenticationService.class);
        authenticationController = new AuthenticationController(jwtService, authenticationService);
    }

    @Test
    void testRegister_shouldReturnRegisteredUser() {
        // Arrange
        RegisterUserDto registerUserDto = new RegisterUserDto();
        User mockUser = new User();
        mockUser.setFullName("testuser");

        when(authenticationService.signup(registerUserDto)).thenReturn(mockUser);

        // Act
        ResponseEntity<User> response = authenticationController.register(registerUserDto);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("testuser", response.getBody().getFullName());
        verify(authenticationService, times(1)).signup(registerUserDto);
    }

    @Test
    void testAuthenticate_shouldReturnLoginResponseWithToken() {
        // Arrange
        LoginUserDto loginUserDto = new LoginUserDto();
        User mockUser = new User();
        mockUser.setFullName("testuser");

        String mockToken = "mock-jwt-token";
        long expirationTime = 3600000L;

        when(authenticationService.authenticate(loginUserDto)).thenReturn(mockUser);
        when(jwtService.generateToken(mockUser)).thenReturn(mockToken);
        when(jwtService.getExpirationTime()).thenReturn(expirationTime);

        // Act
        ResponseEntity<LoginResponse> response = authenticationController.authenticate(loginUserDto);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockToken, response.getBody().getToken());
        assertEquals(expirationTime, response.getBody().getExpiresIn());

        verify(authenticationService, times(1)).authenticate(loginUserDto);
        verify(jwtService, times(1)).generateToken(mockUser);
        verify(jwtService, times(1)).getExpirationTime();
    }
}
