package org.esiee.service;

import org.esiee.dao.CategoryDao;
import org.esiee.dao.ExchangeDao;
import org.esiee.dao.ProductDao;
import org.esiee.dao.UserDao;
import org.esiee.model.*;

import java.sql.Timestamp;
import java.util.List;

/**
 * UserService class provides various user-related operations by interacting with DAOs.
 */
public class UserService {
    private final UserDao userDao;
    private final ProductDao productDao;
    private final CategoryDao categoryDao;
    private final ExchangeDao exchangeDao;

    /**
     * Constructor to initialize UserService with DAO instances.
     *
     * @param userDao the UserDao instance
     * @param productDao the ProductDao instance
     * @param categoryDao the CategoryDao instance
     * @param exchangeDao the ExchangeDao instance
     */
    public UserService(UserDao userDao, ProductDao productDao, CategoryDao categoryDao, ExchangeDao exchangeDao) {
        this.userDao = userDao;
        this.productDao = productDao;
        this.categoryDao = categoryDao;
        this.exchangeDao = exchangeDao;
    }

    /**
     * Registers a new user.
     *
     * @param user the User object to be registered
     * @throws IllegalArgumentException if the user already exists
     */
    public void registerUser(User user) {
        if (userDao.getByEmail(user.getEmail()) != null) {
            throw new IllegalArgumentException("User already exists");
        }
        userDao.save(user);
    }

    /**
     * Logs in a user.
     *
     * @param user the User object containing login credentials
     * @return the logged-in User object with password set to null
     * @throws IllegalArgumentException if the credentials are invalid
     */
    public User loginUser(User user) {
        User validUser = userDao.getByEmail(user.getEmail());
        if (validUser == null || !validUser.getPassword().equals(user.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }
        return validUser;
    }

    /**
     * Adds a new product.
     *
     * @param product the Product object to be added
     */
    public void addProduct(Product product) {
        productDao.save(product);
    }

    /**
     * Updates an existing product.
     *
     * @param product the Product object to be updated
     * @param isAvailable the new availability status of the product
     * @return true if the update was successful, false otherwise
     */
    public boolean updateProduct(Product product, boolean isAvailable) {
        product.setAvailable(isAvailable);
        return productDao.update(product);
    }

    /**
     * Retrieves all products.
     *
     * @return a list of all Product objects
     */
    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    /**
     * Retrieves all products added by a specific user.
     *
     * @param userId the ID of the user
     * @return a list of Product objects added by the user
     */
    public List<Product> getAllProductsByUserId(int userId) {
        return productDao.getProductByUserId(userId);
    }

    /**
     * Retrieves all products filtered by name and category.
     *
     * @param name the name filter
     * @param category the category filter
     * @return a list of filtered Product objects
     */
    public List<Product> getAllProductsFiltered(String name, int category) {
        return productDao.getFilteredProducts(name, category);
    }

    /**
     * Retrieves all categories.
     *
     * @return a list of all Category objects
     */
    public List<Category> getAllCategory() {
        return categoryDao.getAllCategory();
    }

    /**
     * Sets a new exchange.
     *
     * @param exchange the Exchange object to be set
     */
    public void setNewExchange(Exchange exchange) {
        exchangeDao.save(exchange);
    }

    /**
     * Updates an existing exchange.
     *
     * @param exchange the Exchange object to be updated
     * @param status the new status of the exchange
     * @return true if the update was successful, false otherwise
     */
    public boolean updateExchange(Exchange exchange, Status status) {
        exchange.setStatus(Status.valueOf(status.toString()));
        exchange.setDateUpdated(new Timestamp(System.currentTimeMillis()));
        return exchangeDao.update(exchange);
    }

    /**
     * Retrieves all exchanges by a specific user.
     *
     * @param userId the ID of the user
     * @return a list of Exchange objects by the user
     */
    public List<Exchange> getAllExchangesByUserId(int userId) {
        return exchangeDao.getExchangesByUserId(userId);
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param productId the ID of the product
     * @return the Product object with the specified ID
     */
    public Product getProductById(int productId) {
        return productDao.getProductById(productId);
    }

    /**
     * Retrieves an exchange by its ID.
     *
     * @param productId the ID of the exchange
     * @return the Exchange object with the specified ID
     */
    public Exchange getExchangeById(int productId) {
        return exchangeDao.getExchangeById(productId);
    }
}