package org.esiee.manager;

import org.esiee.model.*;
import org.esiee.service.UserService;

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

    public void addProduct(String name, String description, String image, int userId, int category, boolean isAvailable) {
        userService.addProduct(new Product(name, description, image, userId, category, isAvailable));
    }

    public boolean updateProduct(Product product, boolean available) {
        return userService.updateProduct(product, available);
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

    public void setNewExchange(int productIdAsked, int productIdOffered, Status status) {
        userService.setNewExchange(new Exchange(productIdAsked, productIdOffered, status));
    }

    public boolean updateExchange(Exchange exchange, Status status) {
        return userService.updateExchange(exchange, status);
    }

    public List<Exchange> getAllExchangesByUserId(int userId) {
        return userService.getAllExchangesByUserId(userId);
    }

    public Product getProductById(int productId) {
        return userService.getProductById(productId);
    }

    public Exchange getExchangeById(int productId) {
        return userService.getExchangeById(productId);
    }
}