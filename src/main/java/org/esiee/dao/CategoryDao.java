package org.esiee.dao;

import org.esiee.model.Category;
import java.util.List;

/**
 * CategoryDao interface provides methods to perform CRUD operations on Category objects.
 */
public interface CategoryDao extends GenericDao<Category> {

    /**
     * Retrieves a list of all categories.
     *
     * @return a list of Category objects
     */
    List<Category> getAllCategory();
}