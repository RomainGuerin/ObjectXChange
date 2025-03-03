package org.esiee.servlet;

import org.esiee.dao.*;
import org.esiee.dao.UserDaoImpl;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.esiee.service.UserService;

@WebServlet("/register")
public class Authentication extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserDao userDao = new UserDaoImpl();
        ItemDao itemDao = new ItemDaoImpl();
        CategoryDao categoryDao = new CategoryDaoImpl();
        ExchangeDao exchangeDao = new ExchangeDaoImpl();
        UserService userService = new UserService(userDao, itemDao, categoryDao, exchangeDao);
    }
}
