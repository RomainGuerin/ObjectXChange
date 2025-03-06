package org.esiee.model;

import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void constructorWithAllFieldsShouldInitializeCorrectly() {
        Date now = new Date();
        Product product = new Product(1, "Laptop", "High-end gaming laptop", now, "image.jpg", 10, 5, true);

        assertEquals(1, product.getId());
        assertEquals("Laptop", product.getName());
        assertEquals("High-end gaming laptop", product.getDescription());
        assertEquals(now, product.getDateCreated());
        assertEquals("image.jpg", product.getImage());
        assertEquals(10, product.getUserId());
        assertEquals(5, product.getCategoryId());
        assertTrue(product.isAvailable());
    }

    @Test
    void constructorWithoutIdAndDateShouldInitializeCorrectly() {
        Product product = new Product("Laptop", "High-end gaming laptop", "image.jpg", 10, 5, true);

        assertEquals("Laptop", product.getName());
        assertEquals("High-end gaming laptop", product.getDescription());
        assertEquals("image.jpg", product.getImage());
        assertEquals(10, product.getUserId());
        assertEquals(5, product.getCategoryId());
        assertTrue(product.isAvailable());
        assertEquals(0, product.getId());
        assertNull(product.getDateCreated());
    }

    @Test
    void constructorWithIdAndAvailabilityShouldInitializeCorrectly() {
        Product product = new Product(1, true);

        assertEquals(1, product.getId());
        assertTrue(product.isAvailable());
        assertNull(product.getName());
        assertNull(product.getDescription());
        assertNull(product.getDateCreated());
        assertNull(product.getImage());
        assertEquals(0, product.getUserId());
        assertEquals(0, product.getCategoryId());
    }

    @Test
    void settersShouldUpdateFieldsCorrectly() {
        Date now = new Date();
        Product product = new Product(1, "Laptop", "High-end gaming laptop", now, "image.jpg", 10, 5, true);

        product.setId(2);
        product.setName("Smartphone");
        product.setDescription("Latest model smartphone");
        Date newDate = new Date();
        product.setDateCreated(newDate);
        product.setImage("new_image.jpg");
        product.setUserId(20);
        product.setCategoryId(10);
        product.setAvailable(false);

        assertEquals(2, product.getId());
        assertEquals("Smartphone", product.getName());
        assertEquals("Latest model smartphone", product.getDescription());
        assertEquals(newDate, product.getDateCreated());
        assertEquals("new_image.jpg", product.getImage());
        assertEquals(20, product.getUserId());
        assertEquals(10, product.getCategoryId());
        assertFalse(product.isAvailable());
    }
}