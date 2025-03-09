package org.esiee.servlet;

import jakarta.servlet.RequestDispatcher;
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

@WebServlet("/")
public class HomeServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(HomeServlet.class.getName());
    private final transient UserManager userManager;

    public HomeServlet() {
        UserService userService = new UserService(new UserDaoImpl(), new ProductDaoImpl(), new CategoryDaoImpl(), new ExchangeDaoImpl());
        this.userManager = new UserManager(userService);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        List<Product> productList = userManager.getAllProducts();
        request.setAttribute("productList", productList);

        List<Category> categoryList = userManager.getAllCategory();
        request.setAttribute("categoryList", categoryList);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            List<Product> userProductList = userManager.getAllProductsByUserId(user.getId());
            request.setAttribute("userProductList", userProductList);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            LOGGER.log(Level.SEVERE, "Erreur lors du transfert vers /index.jsp", e);
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de l'affichage de la page d'accueil");
            } catch (IOException ex) {
                LOGGER.log(Level.SEVERE, "Échec de l'envoi du message d'erreur", ex);
            }
        }
    }
}
