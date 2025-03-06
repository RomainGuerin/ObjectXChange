package org.esiee.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    @Test
    void constructorWithAllFieldsShouldInitializeCorrectly() {
        Category category = new Category(1, "Electronics");

        assertEquals(1, category.getId());
        assertEquals("Electronics", category.getName());
    }

    @Test
    void constructorWithNameOnlyShouldInitializeCorrectly() {
        Category category = new Category("Books");

        assertEquals("Books", category.getName());
        assertEquals(0, category.getId());
    }

    @Test
    void settersShouldUpdateFieldsCorrectly() {
        Category category = new Category(1, "Electronics");

        category.setId(2);
        category.setName("Toys");

        assertEquals(2, category.getId());
        assertEquals("Toys", category.getName());
    }
}