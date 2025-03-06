package org.esiee.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private static User userWithAllFields;
    private User userWithNameEmailAndPassword;
    private User userWithEmailAndPassword;

    @BeforeAll
    static void setUpBeforeAll() {
        userWithAllFields = new User(1, "John Doe", "john.doe@example.com", "Password123!");
    }

    @BeforeEach
    void setUpBeforeEach() {
        userWithNameEmailAndPassword = new User("John Doe", "john.doe@example.com", "Password123!");
        userWithEmailAndPassword = new User("john.doe@example.com", "Password123!");
    }

    @Test
    void constructorWithAllFieldsShouldInitializeCorrectly() {
        assertAll(
            () -> assertEquals(1, userWithAllFields.getId()),
            () -> assertEquals("John Doe", userWithAllFields.getName()),
            () -> assertEquals("john.doe@example.com", userWithAllFields.getEmail()),
            () -> assertEquals("Password123!", userWithAllFields.getPassword())
        );
    }

    @Test
    void constructorWithNameEmailAndPasswordShouldInitializeCorrectly() {
        assertAll(
            () -> assertEquals("John Doe", userWithNameEmailAndPassword.getName()),
            () -> assertEquals("john.doe@example.com", userWithNameEmailAndPassword.getEmail()),
            () -> assertEquals("Password123!", userWithNameEmailAndPassword.getPassword()),
            () -> assertEquals(0, userWithNameEmailAndPassword.getId())
        );
    }

    @Test
    void constructorWithEmailAndPasswordShouldInitializeCorrectly() {
        assertAll(
            () -> assertEquals("john.doe@example.com", userWithEmailAndPassword.getEmail()),
            () -> assertEquals("Password123!", userWithEmailAndPassword.getPassword()),
            () -> assertNull(userWithEmailAndPassword.getName()),
            () -> assertEquals(0, userWithEmailAndPassword.getId())
        );
    }

    @Test
    void settersShouldUpdateFieldsCorrectly() {
        userWithAllFields.setId(2);
        userWithAllFields.setName("Jane Doe");
        userWithAllFields.setEmail("jane.doe@example.com");
        userWithAllFields.setPassword("newPassword123!");

        assertAll(
            () -> assertEquals(2, userWithAllFields.getId()),
            () -> assertEquals("Jane Doe", userWithAllFields.getName()),
            () -> assertEquals("jane.doe@example.com", userWithAllFields.getEmail()),
            () -> assertEquals("newPassword123!", userWithAllFields.getPassword())
        );
    }
}