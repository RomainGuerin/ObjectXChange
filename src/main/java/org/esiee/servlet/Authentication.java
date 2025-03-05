package org.esiee.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.esiee.dao.*;
import org.esiee.manager.UserManager;
import org.esiee.model.User;
import org.esiee.service.UserService;

import java.io.IOException;

@WebServlet({"/register", "/login"})
public class Authentication extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserDao userDao = new UserDaoImpl();
        ProductDao productDao = new ProductDaoImpl();
        CategoryDao categoryDao = new CategoryDaoImpl();
        ExchangeDao exchangeDao = new ExchangeDaoImpl();
        UserService userService = new UserService(userDao, productDao, categoryDao, exchangeDao);
        UserManager userManager = new UserManager(userService);

        if ("/register".equals(action)) {
            String name = request.getParameter("name");
            try {
                userManager.register(name, email, password);
                response.sendRedirect("index.jsp?success=registered");
            } catch (IllegalArgumentException e) {
                response.sendRedirect("index.jsp?error=exists");
            }
        } else if ("/login".equals(action)) {
            try {
                User user = userManager.login(email, password);
                request.getSession().setAttribute("user", user);
                response.sendRedirect("index.jsp");
            } catch (IllegalArgumentException e) {
                response.sendRedirect("index.jsp?error=invalid");
            }
        }
    }
}
