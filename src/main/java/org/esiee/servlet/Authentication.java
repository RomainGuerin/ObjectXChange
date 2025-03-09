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
    public static final String FAILED_TO_REDIRECT_AFTER_REGISTRATION = "Failed to redirect after registration";
    public static final String FAILED_TO_REDIRECT_AFTER_LOGIN = "Failed to redirect after login";
    private static final Logger LOGGER = Logger.getLogger(Authentication.class.getName());
    private final transient UserManager userManager;

    public Authentication() {
        UserService userService = new UserService(new UserDaoImpl(), new ProductDaoImpl(), new CategoryDaoImpl(), new ExchangeDaoImpl());
        this.userManager = new UserManager(userService);
    }

    private void handleRedirect(HttpServletRequest request, HttpServletResponse response, String path, String logMessage) {
        try {
            response.sendRedirect(request.getContextPath() + path);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, logMessage, ex);
        }
    }

    private void checkValidityPasswordEmail(HttpServletRequest request, HttpServletResponse response, IllegalArgumentException e) {
        String errorPath = e.toString().contains("Invalid email format") || e.toString().contains("Password must be at least 8 characters")
                ? "/?error=invalid" : "/?error=exists";
        handleRedirect(request, response, errorPath, FAILED_TO_REDIRECT_AFTER_REGISTRATION);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getServletPath();
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if ("/register".equals(action)) {
            String name = request.getParameter("name");
            try {
                userManager.register(name, email, password);
                handleRedirect(request, response, "/?showLoginModal=true", FAILED_TO_REDIRECT_AFTER_REGISTRATION);
            } catch (IllegalArgumentException e) {
                checkValidityPasswordEmail(request, response, e);
            }
        } else if ("/login".equals(action)) {
            try {
                User user = userManager.login(email, password);
                request.getSession().setAttribute("user", user);
                handleRedirect(request, response, "/?success=loggedin", FAILED_TO_REDIRECT_AFTER_LOGIN);
            } catch (IllegalArgumentException e) {
                handleRedirect(request, response, "/?error=invalid", FAILED_TO_REDIRECT_AFTER_LOGIN);
            }
        }
    }
}