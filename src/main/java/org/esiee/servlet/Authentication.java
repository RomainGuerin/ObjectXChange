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
                // Rediriger avec un message de succès
                response.sendRedirect(request.getContextPath() + "/?showLoginModal=true");
            } catch (IllegalArgumentException e) {
                // Rediriger avec un message d'erreur
                response.sendRedirect(request.getContextPath() + "/?error=exists");
            }
        } else if ("/login".equals(action)) {
            try {
                User user = userManager.login(email, password);
                request.getSession().setAttribute("user", user);
                // Rediriger avec un message de succès
                response.sendRedirect(request.getContextPath() + "/?success=loggedin");
            } catch (IllegalArgumentException e) {
                // Rediriger avec un message d'erreur
                response.sendRedirect(request.getContextPath() + "/?error=invalid");
            }
        }
    }
}
