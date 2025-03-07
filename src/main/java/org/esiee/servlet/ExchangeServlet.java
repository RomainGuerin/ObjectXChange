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
import org.esiee.model.Exchange;
import org.esiee.model.Product;
import org.esiee.model.Status;
import org.esiee.model.User;
import org.esiee.service.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/exchange")
public class ExchangeServlet extends HttpServlet {
    private final UserManager userManager;

    public ExchangeServlet() {
        UserService userService = new UserService(new UserDaoImpl(), new ProductDaoImpl(), new CategoryDaoImpl(), new ExchangeDaoImpl());
        this.userManager = new UserManager(userService);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Vérifier que l'utilisateur est connecté
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/?showLoginModal=true");
            return;
        }
        // Récupérer les échanges liés à l'utilisateur
        List<Exchange> exchangeList = userManager.getAllExchangesByUserId(user.getId());
        request.setAttribute("exchangeList", exchangeList);

        List<Exchange> receivedExchanges = new ArrayList<>();
        List<Exchange> sentExchanges = new ArrayList<>();
        List<Product> productAsked = userManager.getAllProductsByUserId(user.getId());
        for (Exchange exchange : exchangeList) {
            for (Product product : productAsked) {
                if (exchange.getProductIdAsked() == product.getId()) {
                    receivedExchanges.add(exchange);
                }
                if (exchange.getProductIdOffered() == product.getId()) {
                    sentExchanges.add(exchange);
                }
            }
        }
        request.setAttribute("receivedExchanges", receivedExchanges);
        request.setAttribute("sentExchanges", sentExchanges);

        request.getRequestDispatcher("/exchange.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/?showLoginModal=true");
            return;
        }

        // Si le paramètre "exchangeId" est présent, il s'agit d'une mise à jour d'un échange existant
        String exchangeIdParam = request.getParameter("exchangeId");
        if (exchangeIdParam != null) {
            int exchangeId = Integer.parseInt(exchangeIdParam);
            String newStatusStr = request.getParameter("newStatus");
            Status newStatus = Status.valueOf(newStatusStr);

            // Récupérer l'échange par son ID (implémentez getExchangeById dans UserManager)
            Exchange exchange = userManager.getExchangeById(exchangeId);
            if (exchange == null) {
                response.sendRedirect(request.getContextPath() + "/exchange?error=exchangeNotFound");
                return;
            }

            // Si on accepte l'échange, il faut vérifier la disponibilité des produits
            if (newStatus == Status.Accepted) {
                Product productAsked = userManager.getProductById(exchange.getProductIdAsked());
                Product productOffered = userManager.getProductById(exchange.getProductIdOffered());

                if (productAsked == null || productOffered == null) {
                    response.sendRedirect(request.getContextPath() + "/exchange?error=productNotFound");
                    return;
                }
                if (!productAsked.isAvailable() || !productOffered.isAvailable()) {
                    // Si un produit n'est plus disponible, on refuse l'échange
                    exchange.setStatus(Status.Denied);
                    exchange.setDateUpdated(new java.util.Date());
                    userManager.updateExchange(exchange, Status.Denied);
                    response.sendRedirect(request.getContextPath() + "/exchange?error=productNotAvailable");
                    return;
                }

                // Les deux produits sont disponibles : on les marque comme non disponibles
                userManager.updateProduct(productAsked, false);
                userManager.updateProduct(productOffered, false);

                // On met à jour l'échange en Accepted
                exchange.setStatus(Status.Accepted);
                exchange.setDateUpdated(new java.util.Date());
                boolean updated = userManager.updateExchange(exchange, Status.Accepted);
                if (updated) {
                    response.sendRedirect(request.getContextPath() + "/exchange?success=exchangeUpdated");
                } else {
                    response.sendRedirect(request.getContextPath() + "/exchange?error=exchangeUpdateError");
                }
            } else {
                // Pour les autres statuts (ex : Denied), mise à jour simple sans modification de la disponibilité
                exchange.setStatus(newStatus);
                exchange.setDateUpdated(new java.util.Date());
                boolean updated = userManager.updateExchange(exchange, newStatus);
                if (updated) {
                    response.sendRedirect(request.getContextPath() + "/exchange?success=exchangeUpdated");
                } else {
                    response.sendRedirect(request.getContextPath() + "/exchange?error=exchangeUpdateError");
                }
            }
            return;
        }

        // Sinon, il s'agit de la création d'un nouvel échange
        try {
            int productIdAsked = Integer.parseInt(request.getParameter("productIdAsked"));
            int productIdOffered = Integer.parseInt(request.getParameter("productIdOffered"));

            // Récupérer le produit demandé pour vérifier qu'il n'appartient pas à l'utilisateur et qu'il est disponible
            Product productAsked = userManager.getProductById(productIdAsked);
            if (productAsked == null) {
                response.sendRedirect(request.getContextPath() + "/exchange?error=productNotFound");
                return;
            }
            if (productAsked.getUserId() == user.getId()) {
                response.sendRedirect(request.getContextPath() + "/exchange?error=ownProduct");
                return;
            }
            if (!productAsked.isAvailable()) {
                response.sendRedirect(request.getContextPath() + "/exchange?error=productNotAvailable");
                return;
            }

            // Vérifier également que le produit offert existe et est disponible
            Product productOffered = userManager.getProductById(productIdOffered);
            if (productOffered == null) {
                response.sendRedirect(request.getContextPath() + "/exchange?error=productNotFound");
                return;
            }
            if (!productOffered.isAvailable()) {
                response.sendRedirect(request.getContextPath() + "/exchange?error=productNotAvailable");
                return;
            }

            // Optionnel : Vérifier qu'un échange n'existe pas déjà pour ces produits
            // if (userManager.isExchangeAlreadyExists(productIdAsked, productIdOffered)) {
            //     response.sendRedirect(request.getContextPath() + "/exchange?error=exchangeExists");
            //     return;
            // }

            // Créer un nouvel échange avec le statut Pending
            userManager.setNewExchange(productIdAsked, productIdOffered, Status.Pending);
            response.sendRedirect(request.getContextPath() + "/exchange?success=exchangeCreated");
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/exchange?error=exchangeError");
        }
    }
}
