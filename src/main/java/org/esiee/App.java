package org.esiee;

import org.esiee.dao.*;
import org.esiee.manager.UserManager;
import org.esiee.model.Exchange;
import org.esiee.model.Product;
import org.esiee.model.Status;
import org.esiee.model.User;
import org.esiee.service.UserService;

import java.util.List;

/**
 * The main application class that establishes a connection to a SQLite database
 * and initializes the UserDao and UserService.
 */
public class App {
    /**
     * The main method that serves as the entry point of the application.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        // Initialize UserDao implementation
        UserDao userDao = new UserDaoImpl();
        ProductDao productDao = new ProductDaoImpl();
        CategoryDao categoryDao = new CategoryDaoImpl();
        ExchangeDao exchangeDao = new ExchangeDaoImpl();
        UserService userService = new UserService(userDao, productDao, categoryDao, exchangeDao);
        UserManager userManager = new UserManager(userService);

        // Register a new user
//        userManager.register("John Doe", "test", "password");

        // Login a user
        User test = userManager.login("test", "password");
        System.out.println(test.getId());

        // List all products
        System.out.println(userManager.getAllProducts());

        // Add a new product
        int random = (int) (Math.random() * 1000);
        userManager.addProduct("produit " + random, "description", "image", test.getId(), 1, true);

        // List all products
        System.out.println(userManager.getAllProducts());

        // Update a product
        Product product = userManager.getAllProducts().get(userManager.getAllProducts().size() - 1);
        System.out.println(userManager.updateProduct(product, false));

        // List all products by user id
        System.out.println(userManager.getAllProductsByUserId(test.getId()));

        // List all products filtered
        System.out.println(userManager.getAllProductsFiltered("produit", 1));

        // List all categories
        System.out.println(userManager.getAllCategory());

        // Set a new exchange
        userManager.setNewExchange(1, product.getId(), Status.valueOf("Pending"));

        // Set a new exchange
        List<Exchange> exchanges = userManager.getAllExchangesByUserId(test.getId());
        if (!exchanges.isEmpty()) {
            Exchange exchange = exchanges.get(exchanges.size() - 1);
            System.out.println(userManager.updateExchange(exchange, Status.valueOf("Denied")));
        }
    }
}