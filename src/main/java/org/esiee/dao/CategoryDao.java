package org.esiee.dao;

import org.esiee.model.Category;
import java.util.List;

public interface CategoryDao extends GenericDao<Category> {
    List<Category> getAllCategory();
}
