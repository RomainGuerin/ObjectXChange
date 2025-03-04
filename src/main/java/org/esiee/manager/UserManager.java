package org.esiee.manager;

import org.esiee.model.*;
import org.esiee.service.UserService;

import java.util.Date;
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

    public void addProduct(String name, String description, Date dateCreated, String image, int userId, int category, boolean isAvailable) {
        userService.addProduct(new Product(name, description, dateCreated, image, userId, category, isAvailable));
    }

    public boolean updateProduct(int productId, boolean available) {
        return userService.updateProduct(productId, available);
    }

    public List<Product> getAllProducts() {
        return userService.getAllProducts();
    }

    public List<Product> getAllProductsByUserId(int userId) {
        return userService.getAllProductsByUserId(userId);
    }

    public List<Product> getAllProductsFiltered(String name, int category) {
        return userService.getAllProductsFiltered(name, category);
    }

    public List<Category> getAllCategory() {
        return userService.getAllCategory();
    }

    public void setNewExchange(int productIdAsked, int productIdOffered, Status status, Date dateUpdated) {
        userService.setNewExchange(new Exchange(productIdAsked, productIdOffered, status, dateUpdated));
    }

    public boolean updateExchange(int exchangeId, Status status, Date dateUpdated) {
        return userService.updateExchange(new Exchange(exchangeId, status, dateUpdated));
    }

    public List<Exchange> getAllExchangesByUserId(int userId) {
        return userService.getAllExchangesByUserId(userId);
    }
}