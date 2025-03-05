package org.esiee.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

class ExchangeTest {

    @Test
    void constructorWithAllFieldsShouldSetFieldsCorrectly() {
        Date dateCreated = new Date();
        Date dateUpdated = new Date();
        Exchange exchange = new Exchange(1, 101, 202, Exchange.Status.Pending, dateCreated, dateUpdated);
        assertEquals(1, exchange.getId());
        assertEquals(101, exchange.getItem_id_asked());
        assertEquals(202, exchange.getItem_id_offered());
        assertEquals(Exchange.Status.Pending, exchange.getStatus());
        assertEquals(dateCreated, exchange.getDate_created());
        assertEquals(dateUpdated, exchange.getDate_updated());
    }

    @Test
    void constructorWithoutIdAndDateCreatedShouldSetFieldsCorrectly() {
        Date dateUpdated = new Date();
        Exchange exchange = new Exchange(101, 202, Exchange.Status.Pending, dateUpdated);
        assertEquals(101, exchange.getItem_id_asked());
        assertEquals(202, exchange.getItem_id_offered());
        assertEquals(Exchange.Status.Pending, exchange.getStatus());
        assertEquals(dateUpdated, exchange.getDate_updated());
    }

    @Test
    void constructorWithIdStatusAndDateUpdatedShouldSetFieldsCorrectly() {
        Date dateUpdated = new Date();
        Exchange exchange = new Exchange(1, Exchange.Status.Pending, dateUpdated);
        assertEquals(1, exchange.getId());
        assertEquals(Exchange.Status.Pending, exchange.getStatus());
        assertEquals(dateUpdated, exchange.getDate_updated());
    }

    @Test
    void setIdShouldUpdateIdField() {
        Exchange exchange = new Exchange(1, 101, 202, Exchange.Status.Pending, new Date(), new Date());
        exchange.setId(2);
        assertEquals(2, exchange.getId());
    }

    @Test
    void setItemIdAskedShouldUpdateItemIdAskedField() {
        Exchange exchange = new Exchange(1, 101, 202, Exchange.Status.Pending, new Date(), new Date());
        exchange.setItem_id_asked(102);
        assertEquals(102, exchange.getItem_id_asked());
    }

    @Test
    void setItemIdOfferedShouldUpdateItemIdOfferedField() {
        Exchange exchange = new Exchange(1, 101, 202, Exchange.Status.Pending, new Date(), new Date());
        exchange.setItem_id_offered(203);
        assertEquals(203, exchange.getItem_id_offered());
    }

    @Test
    void setStatusShouldUpdateStatusField() {
        Exchange exchange = new Exchange(1, 101, 202, Exchange.Status.Pending, new Date(), new Date());
        exchange.setStatus(Exchange.Status.Accepted);
        assertEquals(Exchange.Status.Accepted, exchange.getStatus());
    }

    @Test
    void setDateCreatedShouldUpdateDateCreatedField() {
        Date newDateCreated = new Date();
        Exchange exchange = new Exchange(1, 101, 202, Exchange.Status.Pending, new Date(), new Date());
        exchange.setDate_created(newDateCreated);
        assertEquals(newDateCreated, exchange.getDate_created());
    }

    @Test
    void setDateUpdatedShouldUpdateDateUpdatedField() {
        Date newDateUpdated = new Date();
        Exchange exchange = new Exchange(1, 101, 202, Exchange.Status.Pending, new Date(), new Date());
        exchange.setDate_updated(newDateUpdated);
        assertEquals(newDateUpdated, exchange.getDate_updated());
    }

    @Test
    void setStatusShouldHandleNullValue() {
        Exchange exchange = new Exchange(1, 101, 202, Exchange.Status.Pending, new Date(), new Date());
        exchange.setStatus(null);
        assertNull(exchange.getStatus());
    }
}