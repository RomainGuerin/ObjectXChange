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

@WebServlet({"/register", "/login"})
public class Authentication extends HttpServlet {
    private final UserManager userManager;

    public Authentication() {
        UserService userService = new UserService(new UserDaoImpl(), new ProductDaoImpl(), new CategoryDaoImpl(), new ExchangeDaoImpl());
        this.userManager = new UserManager(userService);
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
                if (
                        e.toString().contains("Invalid email format") ||
                                e.toString().contains("Password must be at least 8 characters")
                ) {
                    response.sendRedirect(request.getContextPath() + "/?error=invalid");
                } else {
                    response.sendRedirect(request.getContextPath() + "/?error=exists");
                }
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