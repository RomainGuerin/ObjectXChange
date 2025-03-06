package org.esiee.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.esiee.dao.*;
import org.esiee.model.Product;
import org.esiee.model.User;
import java.util.List;

import java.io.IOException;
import org.esiee.service.UserService;

@WebServlet("/products/*")
public class ProductServlet extends HttpServlet {
    private UserService userService; // Utilisation de UserService

    @Override
    public void init() throws ServletException {
        // Initialisation du UserService avec les DAOs nécessaires
        this.userService = new UserService(
                new UserDaoImpl(),      // DAO pour les utilisateurs
                new ProductDaoImpl(),  // DAO pour les produits
                new CategoryDaoImpl(), // DAO pour les catégories
                new ExchangeDaoImpl()   // DAO pour les échanges
        );
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo(); // Récupère le chemin après "/products/"

        if (pathInfo == null || pathInfo.equals("/")) {
            // Rediriger vers la home pour lister tous les produits
            response.sendRedirect(request.getContextPath() + "/");
        } else if (pathInfo.equals("/user")) {
            // Lister les produits de l'utilisateur connecté
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            if (user == null) {
                response.sendRedirect(request.getContextPath() + "/login.jsp");
                return;
            }
            List<Product> productList = userService.getAllProductsByUserId(user.getId());
            request.setAttribute("productList", productList);
            request.getRequestDispatcher("/myProducts.jsp").forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Ajouter un produit
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        // Récupérer les données du formulaire
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String image = request.getParameter("image");
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));

        // Créer et enregistrer le produit via UserService
        Product product = new Product(name, description, image, user.getId(), categoryId, true);
        userService.addProduct(product);

        // Rediriger vers la liste des produits de l'utilisateur
        response.sendRedirect(request.getContextPath() + "/products/user");
    }
}