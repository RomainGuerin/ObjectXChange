package org.esiee.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class ProductTest {

    private static Date now;

    @Spy
    private Product productWithAllFields;

    private Product productWithoutIdAndDate;

    @BeforeAll
    static void setUpBeforeAll() {
        now = new Date();
    }

    @BeforeEach
    void setUpBeforeEach() {
        productWithAllFields = new Product(1, "Laptop", "High-end gaming laptop", now, "image.jpg", 10, 5, true);
        MockitoAnnotations.openMocks(this);
        productWithoutIdAndDate = new Product("Laptop", "High-end gaming laptop", "image.jpg", 10, 5, true);
    }

    @Test
    void constructorWithAllFieldsShouldInitializeCorrectly() {
        assertAll(
                () -> assertEquals(1, productWithAllFields.getId()),
                () -> assertEquals("Laptop", productWithAllFields.getName()),
                () -> assertEquals("High-end gaming laptop", productWithAllFields.getDescription()),
                () -> assertEquals(now, productWithAllFields.getDateCreated()),
                () -> assertEquals("image.jpg", productWithAllFields.getImage()),
                () -> assertEquals(10, productWithAllFields.getUserId()),
                () -> assertEquals(5, productWithAllFields.getCategoryId()),
                () -> assertTrue(productWithAllFields.isAvailable())
        );
    }

    @Test
    void constructorWithoutIdAndDateShouldInitializeCorrectly() {
        assertAll(
                () -> assertEquals("Laptop", productWithoutIdAndDate.getName()),
                () -> assertEquals("High-end gaming laptop", productWithoutIdAndDate.getDescription()),
                () -> assertEquals("image.jpg", productWithoutIdAndDate.getImage()),
                () -> assertEquals(10, productWithoutIdAndDate.getUserId()),
                () -> assertEquals(5, productWithoutIdAndDate.getCategoryId()),
                () -> assertTrue(productWithoutIdAndDate.isAvailable()),
                () -> assertEquals(0, productWithoutIdAndDate.getId()),
                () -> assertNull(productWithoutIdAndDate.getDateCreated())
        );
    }

    @Test
    void settersShouldUpdateFieldsCorrectly() {
        Date newDate = new Date();
        productWithAllFields.setId(2);
        productWithAllFields.setName("Smartphone");
        productWithAllFields.setDescription("Latest model smartphone");
        productWithAllFields.setDateCreated(newDate);
        productWithAllFields.setImage("new_image.jpg");
        productWithAllFields.setUserId(20);
        productWithAllFields.setCategoryId(10);
        productWithAllFields.setAvailable(false);

        // Verify that the setter methods were called
        verify(productWithAllFields).setId(2);
        verify(productWithAllFields).setName("Smartphone");
        verify(productWithAllFields).setDescription("Latest model smartphone");
        verify(productWithAllFields).setDateCreated(newDate);
        verify(productWithAllFields).setImage("new_image.jpg");
        verify(productWithAllFields).setUserId(20);
        verify(productWithAllFields).setCategoryId(10);
        verify(productWithAllFields).setAvailable(false);

        assertAll(
                () -> assertEquals(2, productWithAllFields.getId()),
                () -> assertEquals("Smartphone", productWithAllFields.getName()),
                () -> assertEquals("Latest model smartphone", productWithAllFields.getDescription()),
                () -> assertEquals(newDate, productWithAllFields.getDateCreated()),
                () -> assertEquals("new_image.jpg", productWithAllFields.getImage()),
                () -> assertEquals(20, productWithAllFields.getUserId()),
                () -> assertEquals(10, productWithAllFields.getCategoryId()),
                () -> assertFalse(productWithAllFields.isAvailable())
        );
    }
}