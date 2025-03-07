package org.esiee.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.esiee.dao.CategoryDaoImpl;
import org.esiee.dao.ExchangeDaoImpl;
import org.esiee.dao.ProductDaoImpl;
import org.esiee.dao.UserDaoImpl;
import org.esiee.manager.UserManager;
import org.esiee.model.User;
import org.esiee.service.UserService;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet({"/register", "/login"})
public class Authentication extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(Authentication.class.getName());
    private final UserManager userManager;

    public Authentication() {
        UserService userService = new UserService(new UserDaoImpl(), new ProductDaoImpl(), new CategoryDaoImpl(), new ExchangeDaoImpl());
        this.userManager = new UserManager(userService);
    }

    private static void checkValidityPasswordEmail(HttpServletRequest request, HttpServletResponse response, IllegalArgumentException e) {
        if (
                e.toString().contains("Invalid email format") ||
                        e.toString().contains("Password must be at least 8 characters")
        ) {
            try {
                response.sendRedirect(request.getContextPath() + "/?error=invalid");
            } catch (IOException ex) {
                LOGGER.log(Level.SEVERE, "Failed to redirect after registration", ex);
            }
        } else {
            try {
                response.sendRedirect(request.getContextPath() + "/?error=exists");
            } catch (IOException ex) {
                LOGGER.log(Level.SEVERE, "Failed to redirect after registration", ex);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getServletPath();
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if ("/register".equals(action)) {
            String name = request.getParameter("name");
            try {
                userManager.register(name, email, password);
                response.sendRedirect(request.getContextPath() + "/?showLoginModal=true");
            } catch (IllegalArgumentException e) {
                checkValidityPasswordEmail(request, response, e);
            }
        } else if ("/login".equals(action)) {
            try {
                User user = userManager.login(email, password);
                request.getSession().setAttribute("user", user);
                response.sendRedirect(request.getContextPath() + "/?success=loggedin");
            } catch (IllegalArgumentException e) {
                response.sendRedirect(request.getContextPath() + "/?error=invalid");
            }
        }
    }
}