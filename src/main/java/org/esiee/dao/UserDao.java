package org.esiee.dao;

import org.esiee.model.User;

public interface UserDao extends GenericDao<User> {
    User getByEmail(String email);
}