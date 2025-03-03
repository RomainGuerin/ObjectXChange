package org.esiee.service;

import org.esiee.dao.CategoryDao;
import org.esiee.dao.ExchangeDao;
import org.esiee.dao.ItemDao;
import org.esiee.dao.UserDao;
import org.esiee.model.Exchange;
import org.esiee.model.Item;
import org.esiee.model.User;
import org.esiee.model.Category;

import java.util.List;

public class UserService {
    private final UserDao userDao;
    private final ItemDao itemDao;
    private final CategoryDao categoryDao;
    private final ExchangeDao exchangeDao;

    public UserService(UserDao userDao, ItemDao itemDao, CategoryDao categoryDao, ExchangeDao exchangeDao) {
        this.userDao = userDao;
        this.itemDao = itemDao;
        this.categoryDao = categoryDao;
        this.exchangeDao = exchangeDao;
    }

    public void registerUser(User user) {
        if (userDao.getByEmail(user.getEmail()) != null) {
            throw new IllegalArgumentException("User already exists");
        }
        userDao.save(user);
    }

    public User loginUser(User user) {
        User validUser = userDao.getByEmail(user.getEmail());
        if (validUser == null || !validUser.getPassword().equals(user.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }
        return validUser;
    }

    public void addItem(Item item) {
        itemDao.save(item);
    }

    public boolean updateItem(int itemId, String name, int userId, int category) {
        return itemDao.update(new Item(itemId, name, userId, category));
    }

    public List<Item> getAllItems() {
        return itemDao.getAllItems();
    }

    public List<Item> getItemsByUserId(int userId) {
        return itemDao.getItemsByUserId(userId);
    }

    public List<Item> getFilteredItems(String name, int category) {
        return itemDao.getFilteredItems(name, category);
    }

    public List<Category> getAllCategory() {
        return categoryDao.getAllCategory();
    }

    public void setNewExchange(Exchange exchange) {
        exchangeDao.save(exchange);
    }

    public boolean updateExchange(Exchange exchange) {
        return exchangeDao.update(exchange);
    }

    public List<Exchange> getMyExchanges(int userId) {
        return exchangeDao.getExchangesByUserId(userId);
    }
}