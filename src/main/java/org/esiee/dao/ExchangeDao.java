package org.esiee.dao;

import org.esiee.model.Exchange;

import java.util.List;

public interface ExchangeDao extends GenericDao<Exchange> {
    List<Exchange> getExchangesByUserId(int userId);
}
