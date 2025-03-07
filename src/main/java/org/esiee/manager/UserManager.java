package org.esiee.manager;

    import org.esiee.model.*;
    import org.esiee.service.UserService;

    import java.util.List;

    /**
     * UserManager class handles user-related operations by delegating to UserService.
     */
    public class UserManager {
        private final UserService userService;

        /**
         * Constructor to initialize UserManager with a UserService instance.
         *
         * @param userService the UserService instance to be used by UserManager
         */
        public UserManager(UserService userService) {
            this.userService = userService;
        }

        /**
         * Registers a new user.
         *
         * @param name     the name of the user
         * @param email    the email of the user
         * @param password the password of the user
         */
        public void register(String name, String email, String password) {
            userService.registerUser(new User(name, email, password));
        }

        /**
         * Logs in a user.
         *
         * @param email    the email of the user
         * @param password the password of the user
         * @return the logged-in user
         */
        public User login(String email, String password) {
            return userService.loginUser(new User(email, password));
        }

        /**
         * Adds a new product.
         *
         * @param name        the name of the product
         * @param description the description of the product
         * @param image       the image URL of the product
         * @param userId      the ID of the user adding the product
         * @param category    the category ID of the product
         * @param isAvailable the availability status of the product
         */
        public void addProduct(String name, String description, String image, int userId, int category, boolean isAvailable) {
            userService.addProduct(new Product(name, description, image, userId, category, isAvailable));
        }

        /**
         * Updates an existing product.
         *
         * @param product   the product to be updated
         * @param available the new availability status of the product
         * @return true if the update was successful, false otherwise
         */
        public boolean updateProduct(Product product, boolean available) {
            return userService.updateProduct(product, available);
        }

        /**
         * Retrieves all products.
         *
         * @return a list of all products
         */
        public List<Product> getAllProducts() {
            return userService.getAllProducts();
        }

        /**
         * Retrieves all products added by a specific user.
         *
         * @param userId the ID of the user
         * @return a list of products added by the user
         */
        public List<Product> getAllProductsByUserId(int userId) {
            return userService.getAllProductsByUserId(userId);
        }

        /**
         * Retrieves all products filtered by name and category.
         *
         * @param name     the name filter
         * @param category the category filter
         * @return a list of filtered products
         */
        public List<Product> getAllProductsFiltered(String name, int category) {
            return userService.getAllProductsFiltered(name, category);
        }

        /**
         * Retrieves all categories.
         *
         * @return a list of all categories
         */
        public List<Category> getAllCategory() {
            return userService.getAllCategory();
        }

        /**
         * Sets a new exchange.
         *
         * @param productIdAsked  the ID of the product being asked for
         * @param productIdOffered the ID of the product being offered
         * @param status          the status of the exchange
         */
        public void setNewExchange(int productIdAsked, int productIdOffered, Status status) {
            userService.setNewExchange(new Exchange(productIdAsked, productIdOffered, status));
        }

        /**
         * Updates an existing exchange.
         *
         * @param exchange the exchange to be updated
         * @param status   the new status of the exchange
         * @return true if the update was successful, false otherwise
         */
        public boolean updateExchange(Exchange exchange, Status status) {
            return userService.updateExchange(exchange, status);
        }

        /**
         * Retrieves all exchanges by a specific user.
         *
         * @param userId the ID of the user
         * @return a list of exchanges by the user
         */
        public List<Exchange> getAllExchangesByUserId(int userId) {
            return userService.getAllExchangesByUserId(userId);
        }

        /**
         * Retrieves a product by its ID.
         *
         * @param productId the ID of the product
         * @return the product with the specified ID
         */
        public Product getProductById(int productId) {
            return userService.getProductById(productId);
        }

        /**
         * Retrieves an exchange by its ID.
         *
         * @param productId the ID of the exchange
         * @return the exchange with the specified ID
         */
        public Exchange getExchangeById(int productId) {
            return userService.getExchangeById(productId);
        }
    }