package org.esiee.dao;

import org.esiee.model.Product;

import java.util.List;

public interface ProductDao extends GenericDao<Product> {
    List<Product> getAllProducts();
    List<Product> getProductByUserId(int userId);
    List<Product> getFilteredProducts(String name, int categoryId);
    Product getProductById(int productId);
}
