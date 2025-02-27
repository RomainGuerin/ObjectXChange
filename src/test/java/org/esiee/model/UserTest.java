package org.esiee.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void getName_returnsCorrectName() {
        User user = new User("John Doe", "john.doe@example.com", "password123");
        assertEquals("John Doe", user.getName());
    }

    @Test
    void getEmail_returnsCorrectEmail() {
        User user = new User("John Doe", "john.doe@example.com", "password123");
        assertEquals("john.doe@example.com", user.getEmail());
    }

    @Test
    void getPassword_returnsCorrectPassword() {
        User user = new User("John Doe", "john.doe@example.com", "password123");
        assertEquals("password123", user.getPassword());
    }

    @Test
    void setName_updatesNameCorrectly() {
        User user = new User("John Doe", "john.doe@example.com", "password123");
        user.setName("Jane Doe");
        assertEquals("Jane Doe", user.getName());
    }

    @Test
    void setEmail_updatesEmailCorrectly() {
        User user = new User("John Doe", "john.doe@example.com", "password123");
        user.setEmail("jane.doe@example.com");
        assertEquals("jane.doe@example.com", user.getEmail());
    }

    @Test
    void setPassword_updatesPasswordCorrectly() {
        User user = new User("John Doe", "john.doe@example.com", "password123");
        user.setPassword("newpassword123");
        assertEquals("newpassword123", user.getPassword());
    }
}