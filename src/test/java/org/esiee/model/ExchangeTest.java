package org.esiee.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ExchangeTest {

    private static Date now;
    private Exchange exchangeWithAllFields;
    private Exchange exchangeWithoutIdAndDates;

    @BeforeAll
    static void setUpBeforeAll() {
        now = new Date();
    }

    @BeforeEach
    void setUpBeforeEach() {
        exchangeWithAllFields = new Exchange(1, 2, 3, Status.Pending, now, now);
        exchangeWithoutIdAndDates = new Exchange(2, 3, Status.Pending);
    }

    @Test
    void constructorWithAllFieldsShouldInitializeCorrectly() {
        assertAll(
            () -> assertEquals(1, exchangeWithAllFields.getId()),
            () -> assertEquals(2, exchangeWithAllFields.getProductIdAsked()),
            () -> assertEquals(3, exchangeWithAllFields.getProductIdOffered()),
            () -> assertEquals(Status.Pending, exchangeWithAllFields.getStatus()),
            () -> assertEquals(now, exchangeWithAllFields.getDateCreated()),
            () -> assertEquals(now, exchangeWithAllFields.getDateUpdated())
        );
    }

    @Test
    void constructorWithoutIdAndDatesShouldInitializeCorrectly() {
        assertAll(
            () -> assertEquals(2, exchangeWithoutIdAndDates.getProductIdAsked()),
            () -> assertEquals(3, exchangeWithoutIdAndDates.getProductIdOffered()),
            () -> assertEquals(Status.Pending, exchangeWithoutIdAndDates.getStatus()),
            () -> assertNull(exchangeWithoutIdAndDates.getDateCreated()),
            () -> assertNull(exchangeWithoutIdAndDates.getDateUpdated())
        );
    }

    @Test
    void settersShouldUpdateFieldsCorrectly() {
        Date newDate = new Date();
        exchangeWithAllFields.setId(10);
        exchangeWithAllFields.setProductIdAsked(20);
        exchangeWithAllFields.setProductIdOffered(30);
        exchangeWithAllFields.setStatus(Status.Accepted);
        exchangeWithAllFields.setDateCreated(newDate);
        exchangeWithAllFields.setDateUpdated(newDate);

        assertAll(
            () -> assertEquals(10, exchangeWithAllFields.getId()),
            () -> assertEquals(20, exchangeWithAllFields.getProductIdAsked()),
            () -> assertEquals(30, exchangeWithAllFields.getProductIdOffered()),
            () -> assertEquals(Status.Accepted, exchangeWithAllFields.getStatus()),
            () -> assertEquals(newDate, exchangeWithAllFields.getDateCreated()),
            () -> assertEquals(newDate, exchangeWithAllFields.getDateUpdated())
        );
    }
}