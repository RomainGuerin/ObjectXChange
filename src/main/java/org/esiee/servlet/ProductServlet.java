package org.esiee.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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

@WebServlet("/products/*")
public class ProductServlet extends HttpServlet {
    public static final String CATEGORY_ID = "categoryId";
    private static final Logger LOGGER = Logger.getLogger(ProductServlet.class.getName());
    private final transient UserManager userManager;

    public ProductServlet() {
        UserService userService = new UserService(
                new UserDaoImpl(), new ProductDaoImpl(), new CategoryDaoImpl(), new ExchangeDaoImpl());
        this.userManager = new UserManager(userService);
    }

    private void redirectTo(HttpServletResponse response, HttpServletRequest request, String path) {
        try {
            response.sendRedirect(request.getContextPath() + path);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, String.format("Redirection failed to: %s", path), e);
        }
    }

    private void redirectToLogin(HttpServletRequest request, HttpServletResponse response) {
        redirectTo(response, request, "/?showLoginModal=true");
    }

    private void redirectToUserProducts(HttpServletRequest request, HttpServletResponse response) {
        redirectTo(response, request, "/products/user");
    }

    private int parseCategoryId(String categoryFilter) {
        if (categoryFilter != null && !categoryFilter.isEmpty()) {
            try {
                return Integer.parseInt(categoryFilter);
            } catch (NumberFormatException e) {
                LOGGER.log(Level.WARNING, String.format("Invalid categoryId format: %s", categoryFilter), e);
            }
        }
        return -1;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("categoryList", userManager.getAllCategory());
        String pathInfo = request.getPathInfo();
        try {
            if (pathInfo == null || "/".equals(pathInfo)) {
                handleProductList(request, response);
            } else if ("/user".equals(pathInfo)) {
                handleUserProducts(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (ServletException | IOException e) {
            LOGGER.log(Level.SEVERE, "Error processing request", e);
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            } catch (IOException ex) {
                LOGGER.log(Level.SEVERE, "Error sending error response", ex);
            }
        }
    }

    private void handleProductList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nameFilter = request.getParameter("name");
        int categoryId = parseCategoryId(request.getParameter(CATEGORY_ID));
        request.setAttribute("productList", userManager.getAllProductsFiltered(nameFilter, categoryId));
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    private void handleUserProducts(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            redirectToLogin(request, response);
            return;
        }
        request.setAttribute("productList", userManager.getAllProductsByUserId(user.getId()));
        request.getRequestDispatcher("/myProducts.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            redirectToLogin(request, response);
            return;
        }

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String image = request.getParameter("image");
        int categoryId = parseCategoryId(request.getParameter(CATEGORY_ID));

        if (categoryId == -1) {
            redirectToUserProducts(request, response);
            return;
        }

        userManager.addProduct(name, description, image, user.getId(), categoryId, true);
        redirectToUserProducts(request, response);
    }
}