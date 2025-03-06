package org.esiee.servlet;

import org.esiee.dao.CategoryDao;
import org.esiee.dao.CategoryDaoImpl;
import org.esiee.dao.ProductDao;
import org.esiee.dao.ProductDaoImpl;
import org.esiee.model.Category;
import org.esiee.model.Product;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.esiee.model.User;

import java.io.IOException;
import java.util.List;

@WebServlet("/")
public class HomeServlet extends HttpServlet {
    private ProductDao productDao = new ProductDaoImpl();
    private CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Récupérer tous les produits
        List<Product> productList = productDao.getAllProducts();
        request.setAttribute("productList", productList);

        // Récupérer toutes les catégories
        List<Category> categoryList = categoryDao.getAllCategory();
        request.setAttribute("categoryList", categoryList);

        // Récupérer les produits de l'utilisateur connecté
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            List<Product> userProductList = productDao.getProductByUserId(user.getId());
            request.setAttribute("userProductList", userProductList);
        }

        // Transférer à la JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }
}