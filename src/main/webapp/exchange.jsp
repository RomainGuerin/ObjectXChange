<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html data-theme="emerald" xml:lang="fr" lang="fr">
<head>
    <title>Mes échanges</title>
    <link href="https://cdn.jsdelivr.net/npm/daisyui@4.12.24/dist/full.min.css" rel="stylesheet" type="text/css" />
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body>
<!-- Toast de notifications -->
<div class="toast toast-bottom toast-end">
    <c:if test="${not empty param.success}">
        <div id="successToast" class="alert alert-success alert-soft">
            <c:choose>
                <c:when test="${param.success == 'exchangeCreated'}">
                    Votre proposition d'échange a été envoyée avec succès.
                </c:when>
                <c:when test="${param.success == 'exchangeUpdated'}">
                    Le statut de l'échange a été mis à jour.
                </c:when>
            </c:choose>
        </div>
    </c:if>
    <c:if test="${not empty param.error}">
        <div id="errorToast" class="alert alert-error">
            <c:choose>
                <c:when test="${param.error == 'exchangeExists'}">
                    Erreur : Un échange est déjà en cours pour ces produits.
                </c:when>
                <c:when test="${param.error == 'ownProduct'}">
                    Erreur : Vous ne pouvez pas proposer un échange sur votre propre produit.
                </c:when>
                <c:otherwise>
                    Erreur lors de la création ou de la mise à jour de l'échange.
                </c:otherwise>
            </c:choose>
        </div>
    </c:if>
</div>

<!-- Barre de navigation -->
<div class="navbar bg-base-100 mb-8">
    <div class="flex-1">
        <a href="${pageContext.request.contextPath}" class="btn btn-ghost text-xl">
            Object<span class="text-primary">X</span>Change
        </a>
    </div>
    <div class="flex-none">
        <ul class="menu menu-horizontal px-1">
            <c:choose>
                <c:when test="${not empty sessionScope.user}">
                    <div class="flex items-center">
                        <div class="dropdown">
                            <button class="btn btn-ghost m-1">
                                Bienvenue, ${sessionScope.user.name}
                            </button>
                            <ul class="dropdown-content menu bg-base-100 rounded-box z-1 w-52 p-2 shadow-sm">
                                <li>
                                    <a href="${pageContext.request.contextPath}/products/user">
                                        Mes produits
                                    </a>
                                </li>
                                <li>
                                    <a href="${pageContext.request.contextPath}/exchange">
                                        Mes échanges
                                    </a>
                                </li>
                            </ul>
                        </div>
                        <div>
                            <li>
                                <a class="ml-2 btn btn-outline btn-error" href="${pageContext.request.contextPath}/logout">
                                    Déconnexion
                                </a>
                            </li>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <li>
                        <button class="btn btn-ghost" onclick="modal_connexion.showModal()">
                            Connexion
                        </button>
                    </li>
                    <li>
                        <button class="ml-2 btn btn-outline" onclick="modal_inscription.showModal()">
                            Inscription
                        </button>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</div>

<!-- Tableau des échanges reçus -->
<div class="mx-6">
    <h2 class="text-2xl font-bold mb-4">Échanges Reçus</h2>
    <div class="overflow-x-auto">
        <table class="table table-zebra w-full">
            <thead>
            <tr>
                <!-- Bouton d'info -->
                <th>Info</th>
                <th>Produit Demande (ID)</th>
                <th>Produit Offert (ID)</th>
                <th>Statut</th>
                <th>Date Création</th>
                <th>Date Mise à Jour</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="exchange" items="${receivedExchanges}">
                <tr>
                    <td>
                        <button class="btn btn-info btn-xs"
                                onclick="showExchangeInfo(this)"
                                data-asked="${exchange.productIdAsked}"
                                data-offered="${exchange.productIdOffered}">
                            i
                        </button>
                    </td>
                    <td>${exchange.productIdAsked}</td>
                    <td>${exchange.productIdOffered}</td>
                    <td>${exchange.status}</td>
                    <td>
                        <fmt:formatDate value="${exchange.dateCreated}" pattern="dd/MM/yyyy HH:mm" />
                    </td>
                    <td>
                        <fmt:formatDate value="${exchange.dateUpdated}" pattern="dd/MM/yyyy HH:mm" />
                    </td>
                    <td>
                        <!-- Boutons d'action disponibles si le statut est Pending -->
                        <form action="${pageContext.request.contextPath}/exchange" method="post" style="display:inline;">
                            <input type="hidden" name="exchangeId" value="${exchange.id}" />
                            <input type="hidden" name="newStatus" value="ACCEPTED" />
                            <button class="btn btn-success btn-sm" type="submit"
                                    <c:if test="${exchange.status ne 'PENDING'}">disabled</c:if>>
                                Accepter
                            </button>
                        </form>
                        <form action="${pageContext.request.contextPath}/exchange" method="post" style="display:inline;">
                            <input type="hidden" name="exchangeId" value="${exchange.id}" />
                            <input type="hidden" name="newStatus" value="DENIED" />
                            <button class="btn btn-error btn-sm" type="submit"
                                    <c:if test="${exchange.status ne 'PENDING'}">disabled</c:if>>
                                Refuser
                            </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty receivedExchanges}">
                <tr>
                    <td colspan="7" class="text-center">Aucun échange reçu.</td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>
</div>

<!-- Tableau des échanges envoyés -->
<div class="mx-6 mt-8">
    <h2 class="text-2xl font-bold mb-4">Échanges Envoyés</h2>
    <div class="overflow-x-auto">
        <table class="table table-zebra w-full">
            <thead>
            <tr>
                <th>Info</th>
                <th>Produit Demande (ID)</th>
                <th>Produit Offert (ID)</th>
                <th>Statut</th>
                <th>Date Création</th>
                <th>Date Mise à Jour</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="exchange" items="${sentExchanges}">
                <tr>
                    <td>
                        <button class="btn btn-info btn-xs"
                                onclick="showExchangeInfo(this)"
                                data-asked="${exchange.productIdAsked}"
                                data-offered="${exchange.productIdOffered}">
                            i
                        </button>
                    </td>
                    <td>${exchange.productIdAsked}</td>
                    <td>${exchange.productIdOffered}</td>
                    <td>${exchange.status}</td>
                    <td>
                        <fmt:formatDate value="${exchange.dateCreated}" pattern="dd/MM/yyyy HH:mm" />
                    </td>
                    <td>
                        <fmt:formatDate value="${exchange.dateUpdated}" pattern="dd/MM/yyyy HH:mm" />
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty sentExchanges}">
                <tr>
                    <td colspan="6" class="text-center">Aucun échange envoyé.</td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>
</div>

<!-- Modal d'information sur l'échange -->
<dialog id="infoModal" class="modal">
    <div class="modal-box w-11/12 max-w-4xl">
        <div class="flex justify-between items-start">
            <h3 class="text-lg font-bold">Détails de l'échange</h3>
            <button class="btn btn-sm btn-circle" onclick="document.getElementById('infoModal').close()">✕</button>
        </div>
        <div class="flex mt-4">
            <!-- Colonne gauche : informations sur le produit offert -->
            <div class="w-1/2 pr-2 border-r">
                <h4 class="font-semibold">Produit Offert</h4>
                <div id="offeredInfo">
                    <!-- Ici, vous pouvez afficher d'autres informations si nécessaire -->
                </div>
            </div>
            <!-- Colonne droite : informations sur le produit demandé -->
            <div class="w-1/2 pl-2">
                <h4 class="font-semibold">Produit Demande</h4>
                <div id="askedInfo">
                    <!-- Ici, vous pouvez afficher d'autres informations si nécessaire -->
                </div>
            </div>
        </div>
    </div>
</dialog>

<!-- Scripts -->
<script>
    // Masquage automatique des notifications
    const successToast = document.getElementById('successToast');
    if (successToast) {
        setTimeout(() => {
            successToast.classList.add('hide');
            setTimeout(() => successToast.remove(), 500);
        }, 3000);
    }
    const errorToast = document.getElementById('errorToast');
    if (errorToast) {
        setTimeout(() => {
            errorToast.classList.add('hide');
            setTimeout(() => errorToast.remove(), 500);
        }, 3000);
    }

    // Affichage du modal d'information
    function showExchangeInfo(button) {
        var asked = button.getAttribute("data-asked");
        var offered = button.getAttribute("data-offered");
        document.getElementById("askedInfo").innerHTML = "Produit demandé (ID) : " + asked;
        document.getElementById("offeredInfo").innerHTML = "Produit offert (ID) : " + offered;
        document.getElementById("infoModal").showModal();
    }
</script>
</body>
</html>
