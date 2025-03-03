package org.esiee.dao;

import org.esiee.model.Category;
import org.esiee.model.Item;

import java.util.List;

public interface ItemDao extends GenericDao<Item> {
    List<Item> getAllItems();
    List<Item> getItemsByUserId(int userId);
    List<Item> getFilteredItems(String name, int categoryId);
}
