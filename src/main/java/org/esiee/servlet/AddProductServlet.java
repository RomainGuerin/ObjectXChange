//package org.esiee.servlet;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import org.esiee.dao.ProductDao;
//import org.esiee.dao.ProductDaoImpl;
//import org.esiee.model.Product;
//import org.esiee.model.User;
//
//import java.io.IOException;
//
//@WebServlet("/addProduct")
//public class AddProductServlet extends HttpServlet {
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        // Récupérer l'utilisateur connecté depuis la session
//        HttpSession session = request.getSession();
//        User user = (User) session.getAttribute("user");
//
//        // Vérifier si l'utilisateur est connecté
//        if (user == null) {
//            response.sendRedirect(request.getContextPath() + "/login.jsp"); // Rediriger vers la page de connexion
//            return;
//        }
//
//        // Récupérer les données du formulaire
//        String name = request.getParameter("name");
//        String description = request.getParameter("description");
//        String image = request.getParameter("image");
//        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
//        int userId = user.getId(); // Utiliser l'ID de l'utilisateur connecté
//
//        // Créer un objet Product
//        Product product = new Product(name, description, image, userId, categoryId, true);
//
//        // Enregistrer le produit dans la base de données
//        ProductDao productDao = new ProductDaoImpl();
//        productDao.save(product);
//
//        // Rediriger vers la page d'accueil
//        response.sendRedirect(request.getContextPath() + "/");
//    }
//}