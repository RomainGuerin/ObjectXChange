package org.esiee.dao;

import org.esiee.model.Product;

import java.util.List;

/**
 * ProductDao interface provides methods to perform CRUD operations on Product objects.
 */
public interface ProductDao extends GenericDao<Product> {

    /**
     * Retrieves a list of all products.
     *
     * @return a list of Product objects
     */
    List<Product> getAllProducts();

    /**
     * Retrieves a list of products by a specific user ID.
     *
     * @param userId the ID of the user
     * @return a list of Product objects associated with the user ID
     */
    List<Product> getProductByUserId(int userId);

    /**
     * Retrieves a list of products filtered by name and category ID.
     *
     * @param name the name of the product
     * @param categoryId the ID of the category
     * @return a list of filtered Product objects
     */
    List<Product> getFilteredProducts(String name, int categoryId);

    /**
     * Retrieves a product by its ID.
     *
     * @param productId the ID of the product
     * @return the Product object with the specified ID
     */
    Product getProductById(int productId);
}