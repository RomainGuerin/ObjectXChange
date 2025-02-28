package org.esiee.dao;

import org.esiee.model.User;

public interface UserDao extends GenericDAO<User> {
    User getByEmail(String email);
}