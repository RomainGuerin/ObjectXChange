package org.esiee.dao;

import org.esiee.model.User;

import java.sql.*;

/**
 * Implementation of the UserDao interface for interacting with the User data in a SQLite database.
 */
public class UserDaoImpl implements UserDao {
    @Override
    public boolean save(User entity) {
        return false; //todo
    }

    @Override
    public User getById(int id) {
        return null; //todo
    }

    @Override
    public User getByEmail(String email) {
        return null; //todo
    }

    @Override
    public boolean update(User entity) {
        return false;
    }

    @Override
    public boolean delete(User entity) {
        return false;
    }
}