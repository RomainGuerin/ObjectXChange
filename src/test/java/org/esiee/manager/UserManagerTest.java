package org.esiee.manager;

import org.esiee.service.UserService;
import org.esiee.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserManagerTest {

    private UserService userService;
    private UserManager userManager;

    @BeforeEach
    void setUp() {
        userService = Mockito.mock(UserService.class);
        userManager = new UserManager(userService);
    }

    @Test
    void registerShouldCallUserServiceRegisterUser() {
        User user = new User("John Doe", "john.doe@example.com", "password123");
        userManager.register(user.getName(), user.getEmail(), user.getPassword());
        verify(userService, times(1)).registerUser(any(User.class));
    }

    @Test
    void registerShouldThrowExceptionIfUserAlreadyExists() {
        User user = new User("John Doe", "john.doe@example.com", "password123");
        doThrow(new IllegalArgumentException("User already exists")).when(userService).registerUser(any(User.class));
        assertThrows(IllegalArgumentException.class, () -> userManager.register(user.getName(), user.getEmail(), user.getPassword()));
    }

    @Test
    void loginShouldReturnUserIfCredentialsAreValid() {
        User user = new User("john.doe@example.com", "password123");
        when(userService.loginUser(any(User.class))).thenReturn(user);
        User loggedInUser = userManager.login(user.getEmail(), user.getPassword());
        assertEquals(user, loggedInUser);
    }

    @Test
    void loginShouldThrowExceptionIfCredentialsAreInvalid() {
        User user = new User("john.doe@example.com", "wrongpassword");
        when(userService.loginUser(any(User.class))).thenThrow(new IllegalArgumentException("Invalid credentials"));
        assertThrows(IllegalArgumentException.class, () -> userManager.login(user.getEmail(), user.getPassword()));
    }
}