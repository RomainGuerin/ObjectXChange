package org.esiee.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    private static Category category;

    @BeforeAll
    static void setUpBeforeAll() {
        category = new Category(1, "Electronics");
    }

    @Test
    void constructorWithAllFieldsShouldInitializeCorrectly() {
        assertAll(
            () -> assertNotNull(category),
            () -> assertEquals(1, category.getId()),
            () -> assertEquals("Electronics", category.getName())
        );
    }

    @Test
    void settersShouldUpdateFieldsCorrectly() {
        category.setId(2);
        category.setName("Toys");

        assertAll(
            () -> assertTrue(category.getId() > 0),
            () -> assertEquals(2, category.getId()),
            () -> assertEquals("Toys", category.getName())
        );
    }

    @Test
    void constructorShouldThrowExceptionForInvalidArguments() {
        assertAll(
            () -> assertThrows(IllegalArgumentException.class, () -> new Category(-1, "Invalid")),
            () -> assertThrows(IllegalArgumentException.class, () -> new Category(1, null))
        );
    }

    @Test
    void setterShouldThrowExceptionForInvalidArguments() {
        assertAll(
            () -> assertThrows(IllegalArgumentException.class, () -> category.setId(-1)),
            () -> assertThrows(IllegalArgumentException.class, () -> category.setName(null))
        );
    }
}