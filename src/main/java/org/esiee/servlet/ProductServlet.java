package org.esiee.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.esiee.dao.ProductDao;
import org.esiee.dao.ProductDaoImpl;
import org.esiee.model.Product;
import org.esiee.model.User;

import java.io.IOException;
import java.util.List;
@WebServlet("/products/*")
public class ProductServlet extends HttpServlet {
    private ProductDao productDao = new ProductDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            // Récupérer les paramètres du filtre
            String nameFilter = request.getParameter("name");
            String categoryFilter = request.getParameter("categoryId");
            int categoryId = (categoryFilter != null && !categoryFilter.isEmpty()) ? Integer.parseInt(categoryFilter) : -1;

            // Récupérer les produits filtrés
            List<Product> productList = productDao.getFilteredProducts(nameFilter, categoryId);
            request.setAttribute("productList", productList);

            // Rediriger vers la page d'affichage
            request.getRequestDispatcher("/index.jsp").forward(request, response);
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

        // Créer et enregistrer le produit
        Product product = new Product(name, description, image, user.getId(), categoryId, true);
        productDao.save(product);

        // Rediriger vers la liste des produits de l'utilisateur
        response.sendRedirect(request.getContextPath() + "/products/user");
    }
}