package org.esiee.service;

import org.esiee.dao.CategoryDao;
import org.esiee.dao.ExchangeDao;
import org.esiee.dao.ProductDao;
import org.esiee.dao.UserDao;
import org.esiee.model.*;

import java.sql.Timestamp;
import java.util.List;

public class UserService {
    private final UserDao userDao;
    private final ProductDao productDao;
    private final CategoryDao categoryDao;
    private final ExchangeDao exchangeDao;

    public UserService(UserDao userDao, ProductDao productDao, CategoryDao categoryDao, ExchangeDao exchangeDao) {
        this.userDao = userDao;
        this.productDao = productDao;
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
        //validUser.setPassword("");
        return validUser;
    }

    public void addProduct(Product product) {
        productDao.save(product);
    }

    public boolean updateProduct(Product product, boolean isAvailable) {
        product.setAvailable(isAvailable);
        return productDao.update(product);
    }

    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    public List<Product> getAllProductsByUserId(int userId) {
        return productDao.getProductByUserId(userId);
    }

    public List<Product> getAllProductsFiltered(String name, int category) {
        return productDao.getFilteredProducts(name, category);
    }

    public List<Category> getAllCategory() {
        return categoryDao.getAllCategory();
    }

    public void setNewExchange(Exchange exchange) {
        exchangeDao.save(exchange);
    }

    public boolean updateExchange(Exchange exchange, Status status) {
        exchange.setStatus(Status.valueOf(status.toString()));
        exchange.setDateUpdated(new Timestamp(System.currentTimeMillis()));
        return exchangeDao.update(exchange);
    }

    public List<Exchange> getAllExchangesByUserId(int userId) {
        return exchangeDao.getExchangesByUserId(userId);
    }

    public Product getProductById(int productId) {
        return productDao.getProductById(productId);
    }

    public Exchange getExchangeById(int productId) {
        return exchangeDao.getExchangeById(productId);
    }
}