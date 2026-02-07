package com.jwt.jwtprojecttuto.controllers;

import com.jwt.jwtprojecttuto.entities.User;
import com.jwt.jwtprojecttuto.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

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
        // Arrange
        User mockUser = new User();
        mockUser.setFullName("testuser"); // Ensure this matches the field you're asserting

        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(mockUser);

        try (MockedStatic<SecurityContextHolder> mockedContextHolder = mockStatic(SecurityContextHolder.class)) {
            SecurityContext mockContext = mock(SecurityContext.class);
            when(mockContext.getAuthentication()).thenReturn(authentication);
            mockedContextHolder.when(SecurityContextHolder::getContext).thenReturn(mockContext);

            // Act
            ResponseEntity<User> response = userController.authenticatedUser();

            // Assert
            assertEquals(200, response.getStatusCodeValue());
            assertNotNull(response.getBody());
            assertEquals("testuser", response.getBody().getFullName());
        }
    }


    @Test
    void testAllUsers() {
        // Arrange
        User user1 = new User();
        user1.setFullName("user1");

        User user2 = new User();
        user2.setFullName("user2");

        List<User> mockUsers = Arrays.asList(user1, user2);
        when(userService.allUsers()).thenReturn(mockUsers);

        // Act
        ResponseEntity<List<User>> response = userController.allUsers();

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        assertEquals("user1", response.getBody().get(0).getFullName());
    }
}
