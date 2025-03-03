package org.esiee.manager;

import org.esiee.model.Category;
import org.esiee.model.Exchange;
import org.esiee.model.Item;
import org.esiee.service.UserService;
import org.esiee.model.User;

import java.util.List;

public class UserManager {
    private final UserService userService;

    public UserManager(UserService userService) {
        this.userService = userService;
    }

    public void register(String name, String email, String password) {
        userService.registerUser(new User(name, email, password));
    }

    public User login(String email, String password) {
        return userService.loginUser(new User(email, password));
    }

    public void addItem(String name, int userId, int category) {
        userService.addItem(new Item(name, userId, category));
    }

    public boolean updateItem(int itemId, String name, int userId, int category) {
        return userService.updateItem(itemId, name, userId, category);
    }

    public List<Item> getAllItems() {
        return userService.getAllItems();
    }

    public List<Item> getItemsByUserId(int userId) {
        return userService.getItemsByUserId(userId);
    }

    public List<Item> getFilteredItems(String name, int category) {
        return userService.getFilteredItems(name, category);
    }

    public List<Category> getAllCategory() {
        return userService.getAllCategory();
    }

    public void setNewExchange(int item_id_asked, int item_id_offered, String status, String date_updated) {
        userService.setNewExchange(new Exchange(item_id_asked, item_id_offered, status, date_updated));
    }

    public boolean updateExchange(int exchangeId, String status, String date_updated) {
    return userService.updateExchange(new Exchange(exchangeId, status, date_updated));
    }

    public List<Exchange> getMyExchanges(int userId) {
        return userService.getMyExchanges(userId);
    }
}