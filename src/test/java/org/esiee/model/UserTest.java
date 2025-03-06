package org.esiee.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void constructorWithAllFieldsShouldInitializeCorrectly() {
        User user = new User(1, "John Doe", "john.doe@example.com", "password123");

        assertEquals(1, user.getId());
        assertEquals("John Doe", user.getName());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals("password123", user.getPassword());
    }

    @Test
    void constructorWithNameEmailAndPasswordShouldInitializeCorrectly() {
        User user = new User("John Doe", "john.doe@example.com", "password123");

        assertEquals("John Doe", user.getName());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals("password123", user.getPassword());
        assertEquals(0, user.getId());
    }

    @Test
    void constructorWithEmailAndPasswordShouldInitializeCorrectly() {
        User user = new User("john.doe@example.com", "password123");

        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals("password123", user.getPassword());
        assertNull(user.getName());
        assertEquals(0, user.getId());
    }

    @Test
    void settersShouldUpdateFieldsCorrectly() {
        User user = new User(1, "John Doe", "john.doe@example.com", "password123");

        user.setId(2);
        user.setName("Jane Doe");
        user.setEmail("jane.doe@example.com");
        user.setPassword("newpassword123");

        assertEquals(2, user.getId());
        assertEquals("Jane Doe", user.getName());
        assertEquals("jane.doe@example.com", user.getEmail());
        assertEquals("newpassword123", user.getPassword());
    }
}