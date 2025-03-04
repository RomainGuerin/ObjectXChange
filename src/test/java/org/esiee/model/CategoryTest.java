package org.esiee.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    @Test
    void constructorWithIdAndNameShouldSetFieldsCorrectly() {
        Category category = new Category(1, "Electronics");
        assertEquals(1, category.getId());
        assertEquals("Electronics", category.getName());
    }

    @Test
    void constructorWithNameShouldSetNameFieldCorrectly() {
        Category category = new Category("Books");
        assertEquals("Books", category.getName());
    }

    @Test
    void setIdShouldUpdateIdField() {
        Category category = new Category(1, "Electronics");
        category.setId(2);
        assertEquals(2, category.getId());
    }

    @Test
    void setNameShouldUpdateNameField() {
        Category category = new Category(1, "Electronics");
        category.setName("Books");
        assertEquals("Books", category.getName());
    }

    @Test
    void constructorWithNameShouldSetIdToDefaultValue() {
        Category category = new Category("Books");
        assertEquals(0, category.getId());
    }

    @Test
    void setNameShouldHandleNullValue() {
        Category category = new Category(1, "Electronics");
        category.setName(null);
        assertNull(category.getName());
    }
}