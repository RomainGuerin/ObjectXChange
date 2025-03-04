package org.esiee.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    @Test
    void constructorWithAllFieldsShouldSetFieldsCorrectly() {
        Item item = new Item(1, "Laptop", 101, 202);
        assertEquals(1, item.getId());
        assertEquals("Laptop", item.getName());
        assertEquals(101, item.getUserId());
        assertEquals(202, item.getCategory());
    }

    @Test
    void constructorWithoutIdShouldSetFieldsCorrectly() {
        Item item = new Item("Laptop", 101, 202);
        assertEquals("Laptop", item.getName());
        assertEquals(101, item.getUserId());
        assertEquals(202, item.getCategory());
    }

    @Test
    void setIdShouldUpdateIdField() {
        Item item = new Item(1, "Laptop", 101, 202);
        item.setId(2);
        assertEquals(2, item.getId());
    }

    @Test
    void setNameShouldUpdateNameField() {
        Item item = new Item(1, "Laptop", 101, 202);
        item.setName("Tablet");
        assertEquals("Tablet", item.getName());
    }

    @Test
    void setUserIdShouldUpdateUserIdField() {
        Item item = new Item(1, "Laptop", 101, 202);
        item.setUserId(102);
        assertEquals(102, item.getUserId());
    }

    @Test
    void setCategoryShouldUpdateCategoryField() {
        Item item = new Item(1, "Laptop", 101, 202);
        item.setCategory(203);
        assertEquals(203, item.getCategory());
    }

    @Test
    void setNameShouldHandleNullValue() {
        Item item = new Item(1, "Laptop", 101, 202);
        item.setName(null);
        assertNull(item.getName());
    }
}