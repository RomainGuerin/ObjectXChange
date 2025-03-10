package org.esiee.dao;

import org.esiee.model.Exchange;

import java.util.List;

/**
 * ExchangeDao interface provides methods to perform CRUD operations on Exchange objects.
 */
public interface ExchangeDao extends GenericDao<Exchange> {

    /**
     * Retrieves a list of exchanges by a specific user ID.
     *
     * @param userId the ID of the user
     * @return a list of Exchange objects associated with the user ID
     */
    List<Exchange> getExchangesByUserId(int userId);

    /**
     * Retrieves an exchange by its ID.
     *
     * @param productId the ID of the exchange
     * @return the Exchange object with the specified ID
     */
    Exchange getExchangeById(int productId);
}