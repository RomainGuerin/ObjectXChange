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
import org.esiee.model.Category;
import org.esiee.model.Product;
import org.esiee.model.User;
import org.esiee.service.UserService;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/products/*")
public class ProductServlet extends HttpServlet {
    public static final String CATEGORY_ID = "categoryId";
    private static final Logger LOGGER = Logger.getLogger(ProductServlet.class.getName());
    private final transient UserManager userManager;

    public ProductServlet() {
        UserService userService = new UserService(new UserDaoImpl(), new ProductDaoImpl(), new CategoryDaoImpl(), new ExchangeDaoImpl());
        this.userManager = new UserManager(userService);
    }

    private static void redirectionUserProduct(HttpServletResponse response, HttpServletRequest request, String path, String Redirection_failed_after_adding_product) {
        try {
            response.sendRedirect(request.getContextPath() + path);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, Redirection_failed_after_adding_product, e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String pathInfo = request.getPathInfo();
        List<Category> categoryList = userManager.getAllCategory();
        request.setAttribute("categoryList", categoryList);

        try {
            if (pathInfo == null || pathInfo.equals("/")) {
                handleProductList(request, response);
            } else if (pathInfo.equals("/user")) {
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
        String categoryFilter = request.getParameter(CATEGORY_ID);
        int categoryId = -1;
        if (categoryFilter != null && !categoryFilter.isEmpty()) {
            try {
                categoryId = Integer.parseInt(categoryFilter);
            } catch (NumberFormatException e) {
                LOGGER.log(Level.WARNING, String.format("Invalid categoryId format: %s", categoryFilter), e);
            }
        }

        List<Product> productList = userManager.getAllProductsFiltered(nameFilter, categoryId);
        request.setAttribute("productList", productList);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    private void handleUserProducts(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            try {
                response.sendRedirect(request.getContextPath() + "/?showLoginModal=true");
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Redirection failed for user login modal", e);
            }
            return;
        }
        List<Product> productList = userManager.getAllProductsByUserId(user.getId());
        request.setAttribute("productList", productList);
        request.getRequestDispatcher("/myProducts.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            try {
                response.sendRedirect(request.getContextPath() + "/?showLoginModal=true");
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Redirection failed for user login modal", e);
            }
            return;
        }

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String image = request.getParameter("image");
        int categoryId;
        try {
            categoryId = Integer.parseInt(request.getParameter(CATEGORY_ID));
        } catch (NumberFormatException e) {
            LOGGER.log(Level.WARNING, String.format("Invalid categoryId format: %s", request.getParameter(CATEGORY_ID)), e);
            redirectionUserProduct(response, request, "/products/user", "Redirection failed after adding product");
            return;
        }

        userManager.addProduct(name, description, image, user.getId(), categoryId, true);

        redirectionUserProduct(response, request, "/products/user", "Redirection failed after adding product");
    }
}
