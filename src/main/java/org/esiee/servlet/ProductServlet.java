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

@WebServlet("/products/*")
public class ProductServlet extends HttpServlet {
    private final UserManager userManager;

    public ProductServlet() {
        UserService userService = new UserService(new UserDaoImpl(), new ProductDaoImpl(), new CategoryDaoImpl(), new ExchangeDaoImpl());
        this.userManager = new UserManager(userService);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        List<Category> categoryList = userManager.getAllCategory();
        request.setAttribute("categoryList", categoryList);

        if (pathInfo == null || pathInfo.equals("/")) {
            handleProductList(request, response);
        } else if (pathInfo.equals("/user")) {
            handleUserProducts(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void handleProductList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nameFilter = request.getParameter("name");
        String categoryFilter = request.getParameter("categoryId");
        int categoryId = (categoryFilter != null && !categoryFilter.isEmpty()) ? Integer.parseInt(categoryFilter) : -1;

        List<Product> productList = userManager.getAllProductsFiltered(nameFilter, categoryId);
        request.setAttribute("productList", productList);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    private void handleUserProducts(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/?showLoginModal=true");
            return;
        }
        List<Product> productList = userManager.getAllProductsByUserId(user.getId());
        request.setAttribute("productList", productList);
        request.getRequestDispatcher("/myProducts.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/?showLoginModal=true");
            return;
        }

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String image = request.getParameter("image");
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));

        userManager.addProduct(name, description, image, user.getId(), categoryId, true);

        response.sendRedirect(request.getContextPath() + "/products/user");
    }
}