package org.esiee.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    void userConstructorWithIdShouldInitializeFields() {
        User user = new User(1, "John Doe", "john.doe@example.com", "password123");
        assertEquals(1, user.getId());
        assertEquals("John Doe", user.getName());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals("password123", user.getPassword());
    }

    @Test
    void userConstructorWithoutIdShouldInitializeFields() {
        User user = new User("John Doe", "john.doe@example.com", "password123");
        assertEquals("John Doe", user.getName());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals("password123", user.getPassword());
    }

    @Test
    void userConstructorWithEmailAndPasswordShouldInitializeFields() {
        User user = new User("john.doe@example.com", "password123");
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals("password123", user.getPassword());
    }

    @Test
    void setIdShouldUpdateId() {
        User user = new User("John Doe", "john.doe@example.com", "password123");
        user.setId(2);
        assertEquals(2, user.getId());
    }

    @Test
    void setNameShouldUpdateName() {
        User user = new User("John Doe", "john.doe@example.com", "password123");
        user.setName("Jane Doe");
        assertEquals("Jane Doe", user.getName());
    }

    @Test
    void setEmailShouldUpdateEmail() {
        User user = new User("John Doe", "john.doe@example.com", "password123");
        user.setEmail("jane.doe@example.com");
        assertEquals("jane.doe@example.com", user.getEmail());
    }

    @Test
    void setPasswordShouldUpdatePassword() {
        User user = new User("John Doe", "john.doe@example.com", "password123");
        user.setPassword("newpassword123");
        assertEquals("newpassword123", user.getPassword());
    }
}
