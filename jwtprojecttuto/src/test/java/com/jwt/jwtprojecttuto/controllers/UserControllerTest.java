package com.jwt.jwtprojecttuto.controllers;

import com.jwt.jwtprojecttuto.entities.User;
import com.jwt.jwtprojecttuto.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import utils.Constants;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class UserControllerTest {

    private UserService userService;
    private UserController userController;

    @BeforeEach
    void setUp() {
        userService = mock(UserService.class);
        userController = new UserController(userService);
    }

    @Test
    void testAuthenticatedUser() {

        User mockUser = new User();
        mockUser.setFullName(Constants.FULLNAME);

        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(mockUser);

        try (MockedStatic<SecurityContextHolder> mockedContextHolder = mockStatic(SecurityContextHolder.class)) {
            SecurityContext mockContext = mock(SecurityContext.class);
            when(mockContext.getAuthentication()).thenReturn(authentication);
            mockedContextHolder.when(SecurityContextHolder::getContext).thenReturn(mockContext);

            ResponseEntity<User> response = userController.authenticatedUser();

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertNotNull(response.getBody());
            assertEquals(Constants.FULLNAME, response.getBody().getFullName());
        }
    }


    @Test
    void testAllUsers() {

        User user1 = new User();
        user1.setFullName("user1");

        User user2 = new User();
        user2.setFullName("user2");

        List<User> mockUsers = Arrays.asList(user1, user2);
        when(userService.allUsers()).thenReturn(mockUsers);

        ResponseEntity<List<User>> response = userController.allUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        assertEquals("user1", response.getBody().get(0).getFullName());
    }
}
