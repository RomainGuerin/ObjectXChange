package org.esiee.model;

import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;
import org.esiee.model.Status.*;

class ExchangeTest {

    @Test
    void constructorWithAllFieldsShouldInitializeCorrectly() {
        Date now = new Date();
        Exchange exchange = new Exchange(1, 2, 3, Status.Pending, now, now);

        assertEquals(1, exchange.getId());
        assertEquals(2, exchange.getProductIdAsked());
        assertEquals(3, exchange.getProductIdOffered());
        assertEquals(Status.Pending, exchange.getStatus());
        assertEquals(now, exchange.getDateCreated());
        assertEquals(now, exchange.getDateUpdated());
    }

    @Test
    void constructorWithoutIdAndDatesShouldInitializeCorrectly() {
        Exchange exchange = new Exchange(2, 3, Status.Pending);

        assertEquals(2, exchange.getProductIdAsked());
        assertEquals(3, exchange.getProductIdOffered());
        assertEquals(Status.Pending, exchange.getStatus());
        assertNull(exchange.getDateCreated());
        assertNull(exchange.getDateUpdated());
    }

    @Test
    void constructorWithIdStatusAndDateUpdatedShouldInitializeCorrectly() {
        Date now = new Date();
        Exchange exchange = new Exchange(1, Status.Pending, now);

        assertEquals(1, exchange.getId());
        assertEquals(Status.Pending, exchange.getStatus());
        assertEquals(now, exchange.getDateUpdated());
        assertEquals(0, exchange.getProductIdAsked());
        assertEquals(0, exchange.getProductIdOffered());
        assertNull(exchange.getDateCreated());
    }

    @Test
    void settersShouldUpdateFieldsCorrectly() {
        Date now = new Date();
        Exchange exchange = new Exchange(1, 2, 3, Status.Pending, now, now);

        exchange.setId(10);
        exchange.setProductIdAsked(20);
        exchange.setProductIdOffered(30);
        exchange.setStatus(Status.Accepted);
        Date newDate = new Date();
        exchange.setDateCreated(newDate);
        exchange.setDateUpdated(newDate);

        assertEquals(10, exchange.getId());
        assertEquals(20, exchange.getProductIdAsked());
        assertEquals(30, exchange.getProductIdOffered());
        assertEquals(Status.Accepted, exchange.getStatus());
        assertEquals(newDate, exchange.getDateCreated());
        assertEquals(newDate, exchange.getDateUpdated());
    }
}