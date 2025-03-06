package org.esiee.servlet;

import org.esiee.dao.*;

import org.esiee.model.Category;
import org.esiee.model.Product;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.esiee.model.User;

import java.io.IOException;
import java.util.List;

import org.esiee.service.UserService;

@WebServlet("/")
public class HomeServlet extends HttpServlet {
    private UserService userService; // Utilisation de UserService

    @Override
    public void init() throws ServletException {
        // Initialisation du UserService avec les DAOs nécessaires
        this.userService = new UserService(
                new UserDaoImpl(),      // DAO pour les utilisateurs
                new ProductDaoImpl(),    // DAO pour les produits
                new CategoryDaoImpl(),   // DAO pour les catégories
                new ExchangeDaoImpl()    // DAO pour les échanges
        );
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Récupérer tous les produits via UserService
        List<Product> productList = userService.getAllProducts();
        request.setAttribute("productList", productList);

        // Récupérer toutes les catégories via UserService
        List<Category> categoryList = userService.getAllCategory();
        request.setAttribute("categoryList", categoryList);

        // Récupérer les produits de l'utilisateur connecté via UserService
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            List<Product> userProductList = userService.getAllProductsByUserId(user.getId());
            request.setAttribute("userProductList", userProductList);
        }

        // Transférer à la JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }
}