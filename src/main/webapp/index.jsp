<%--
  Created by IntelliJ IDEA.
  User: romai
  Date: 27/02/2025"
  Time: 15:31
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html data-theme="emerald">
<head>
  <title>ObjectXChange</title>
  <link rel="icon" type="image/x-icon" href="images/icon.ico">
  <link href="https://cdn.jsdelivr.net/npm/daisyui@4.12.24/dist/full.min.css" rel="stylesheet" type="text/css" />
  <script src="https://cdn.tailwindcss.com"></script>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>

<div class="navbar bg-base-100 mb-8">
  <div class="flex-1">
    <a href="${pageContext.request.contextPath}" class="btn btn-ghost text-xl">Object<span class="text-primary">X</span>Change</a>
  </div>
  <div class="flex-none">
    <ul class="menu menu-horizontal px-1">
      <c:choose>
        <c:when test="${not empty sessionScope.user}">
          <div class="flex items-center">
          <div class="dropdown">
            <div tabindex="0" role="button" class="btn btn-ghost m-1">Bienvenue, ${sessionScope.user.name}</div>
            <ul tabindex="0" class="dropdown-content menu bg-base-100 rounded-box z-1 w-52 p-2 shadow-sm">
              <li><a href="${pageContext.request.contextPath}/products/user">Mes produits</a></li>
              <li><a href="${pageContext.request.contextPath}/exchange.jsp">Mes échanges</a></li>
            </ul>
          </div>
          <div>
            <li><a class="ml-2 btn btn-outline btn-error" href="${pageContext.request.contextPath}/logout">Déconnexion</a></li>
          </div>
          </div>
        </c:when>
        <c:otherwise>
          <li><button class="btn btn-ghost" onclick="modal_connexion.showModal()">Connexion</button></li>
          <li><button class="ml-2 btn btn-outline" onclick="modal_inscription.showModal()">Inscription</button></li>
        </c:otherwise>
      </c:choose>
    </ul>
  </div>
</div>

<div class="mx-6">
  <div class="mb-6 flex items-center justify-between align-center">
    <div class="font-semibold text-xl">Liste objets</div>
    <form action="${pageContext.request.contextPath}/products" method="get" class="flex items-center gap-4 mb-6">
      <input type="text" name="name" placeholder="Rechercher un produit..." class="input input-bordered w-full max-w-xs" value="${param.name}">
      <select name="categoryId" class="select select-bordered w-full max-w-xs">
        <option value="">Toutes les catégories</option>
        <c:forEach var="category" items="${categoryList}">
          <option value="${category.id}" ${param.categoryId == category.id ? 'selected' : ''}>${category.name}</option>
        </c:forEach>
      </select>
      <button type="submit" class="btn btn-primary">Filtrer</button>
    </form>
  </div>
  <div class="flex justify-end mb-4">
    <c:if test="${not empty sessionScope.user}">
      <button onclick="modal_add_object.showModal()" class="btn btn-neutral">Ajouter un objet</button>
    </c:if>
  </div>

  <div class="grid grid-cols-1 md:grid-cols-3 lg:grid-cols-4 xl:grid-cols-5 gap-4 mb-4">
    <c:forEach var="product" items="${productList}">
      <div class="card card-compact bg-base-100 shadow-xl">
        <figure>
          <img src="${product.image}" alt="${product.name}" class="w-full h-52 object-cover"/>
        </figure>
        <div class="card-body">
          <h2 class="card-title">
              ${product.name}
            <c:set var="categoryName" value="Inconnue"/>
            <c:forEach var="category" items="${categoryList}">
              <c:if test="${category.id == product.categoryId}">
                <c:set var="categoryName" value="${category.name}"/>
              </c:if>
            </c:forEach>
            <div class="badge">${categoryName}</div>
          </h2>
          <p>${product.description}</p>
          <c:if test="${not empty sessionScope.user}">
          <div class="card-actions justify-end mt-4">
            <button class="btn" onclick="modal_product_client.showModal()">Proposer un échange</button>
          </div>
          </c:if>
        </div>
      </div>
    </c:forEach>
  </div>

  <dialog id="modal_add_object" class="modal">
    <div class="modal-box">
      <form method="dialog">
        <button class="btn btn-sm btn-circle btn-ghost absolute right-2 top-2">✕</button>
      </form>
      <h3 class="text-lg font-bold mb-4">Ajouter un objet</h3>
      <form action="${pageContext.request.contextPath}/products" method="post">
        <div class="items-center">
          <label class="input input-bordered flex items-center gap-2 mb-2">
            <input type="text" name="name" class="grow" placeholder="Nom de l'objet" required />
          </label>
          <label class="input input-bordered flex items-center gap-2 mb-2">
            <input type="text" name="description" class="grow" placeholder="Description" required />
          </label>
          <label class="input input-bordered flex items-center gap-2 mb-2">
            <input type="text" name="image" class="grow" placeholder="URL de l'image" required />
          </label>
          <select class="select select-bordered w-full mb-4" name="categoryId" required>
            <option disabled selected>Choisir une catégorie</option>
            <c:forEach var="category" items="${categoryList}">
              <option value="${category.id}">${category.name}</option>
            </c:forEach>
          </select>
          <button class="btn btn-primary" type="submit">Ajouter l'objet</button>
        </div>
      </form>
    </div>
  </dialog>

  <dialog id="modal_product_client" class="modal">
    <div class="modal-box w-11/12 max-w-5xl max-h-[80vh] overflow-y-auto">
      <h3 class="text-lg font-bold mb-4">Mes produits</h3>
      <div class="overflow-x-auto">
        <table class="table">
          <thead>
          <tr>
            <th>Nom</th>
            <th>Description</th>
            <th>Image</th>
            <th>Catégorie</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach var="product" items="${userProductList}">
            <tr>
              <td>${product.name}</td>
              <td>${product.description}</td>
              <td>
                <img src="${product.image}" alt="${product.name}" class="w-16 h-16 object-cover" />
              </td>
              <td>${product.categoryId}</td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
      </div>
      <div class="modal-action">
        <form method="dialog">
          <button class="btn">Fermer</button>
        </form>
      </div>
    </div>
  </dialog>


  <dialog id="modal_connexion" class="modal">
    <div class="modal-box relative">
      <!-- Bouton pour fermer le modal -->
      <button type="button" class="btn btn-sm btn-circle btn-ghost absolute right-2 top-2"
              onclick="document.getElementById('modal_connexion').close()">
        ✕
      </button>
      <!-- Formulaire de connexion fonctionnel -->
      <form action="login" method="post">
        <h1 class="font-semibold text-xl mb-4">Connexion</h1>
        <label class="input input-bordered flex items-center gap-2 mb-4">
          <!-- Exemple d'icône -->
          <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 16 16" fill="currentColor"
               class="h-4 w-4 opacity-70">
            <path d="M2.5 3A1.5 1.5 0 0 0 1 4.5v.793c.026.009.051.02.076.032L7.674 8.51c.206.1.446.1.652 0l6.598-3.185A.755.755 0 0 1 15 5.293V4.5A1.5 1.5 0 0 0 13.5 3h-11Z" />
            <path d="M15 6.954 8.978 9.86a2.25 2.25 0 0 1-1.956 0L1 6.954V11.5A1.5 1.5 0 0 0 2.5 13h11a1.5 1.5 0 0 0 1.5-1.5V6.954Z" />
          </svg>
          <input type="text" name="email" class="grow" placeholder="Email" required />
        </label>

        <label class="input input-bordered flex items-center gap-2 mb-4">
          <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 16 16" fill="currentColor"
               class="h-4 w-4 opacity-70">
            <path fill-rule="evenodd" d="M14 6a4 4 0 0 1-4.899 3.899l-1.955 1.955a.5.5 0 0 1-.353.146H5v1.5a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1-.5-.5v-2.293a.5.5 0 0 1 .146-.353l3.955-3.955A4 4 0 1 1 14 6Zm-4-2a.75.75 0 0 0 0 1.5.5.5 0 0 1 .5.5.75.75 0 0 0 1.5 0 2 2 0 0 0-2-2Z" clip-rule="evenodd" />
          </svg>
          <input type="password" name="password" class="grow" placeholder="Mot de passe" required />
        </label>
        <button class="btn btn-primary" type="submit">Se connecter</button>
      </form>
    </div>
  </dialog>

  <dialog id="modal_inscription" class="modal">
    <div class="modal-box relative">
      <!-- Bouton pour fermer le modal -->
      <button type="button" class="btn btn-sm btn-circle btn-ghost absolute right-2 top-2"
              onclick="document.getElementById('modal_inscription').close()">
        ✕
      </button>
      <form action="register" method="post">
        <h1 class="font-semibold text-xl mb-4">Inscription</h1>
        <label class="input input-bordered flex items-center gap-2 mb-4">
          <svg
                  xmlns="http://www.w3.org/2000/svg"
                  viewBox="0 0 16 16"
                  fill="currentColor"
                  class="h-4 w-4 opacity-70">
            <path
                    d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6ZM12.735 14c.618 0 1.093-.561.872-1.139a6.002 6.002 0 0 0-11.215 0c-.22.578.254 1.139.872 1.139h9.47Z" />
          </svg>
          <input type="text" name="name" class="grow" placeholder="Username" />
        </label>

        <label class="input input-bordered flex items-center gap-2 mb-4">
          <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 16 16" fill="currentColor"
               class="h-4 w-4 opacity-70">
            <path d="M2.5 3A1.5 1.5 0 0 0 1 4.5v.793c.026.009.051.02.076.032L7.674 8.51c.206.1.446.1.652 0l6.598-3.185A.755.755 0 0 1 15 5.293V4.5A1.5 1.5 0 0 0 13.5 3h-11Z" />
            <path d="M15 6.954 8.978 9.86a2.25 2.25 0 0 1-1.956 0L1 6.954V11.5A1.5 1.5 0 0 0 2.5 13h11a1.5 1.5 0 0 0 1.5-1.5V6.954Z" />
          </svg>
          <input type="text" name="email" class="grow" placeholder="Email" required />
        </label>

        <label class="input input-bordered flex items-center gap-2 mb-4">
          <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 16 16" fill="currentColor"
               class="h-4 w-4 opacity-70">
            <path fill-rule="evenodd" d="M14 6a4 4 0 0 1-4.899 3.899l-1.955 1.955a.5.5 0 0 1-.353.146H5v1.5a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1-.5-.5v-2.293a.5.5 0 0 1 .146-.353l3.955-3.955A4 4 0 1 1 14 6Zm-4-2a.75.75 0 0 0 0 1.5.5.5 0 0 1 .5.5.75.75 0 0 0 1.5 0 2 2 0 0 0-2-2Z" clip-rule="evenodd" />
          </svg>
          <input type="password" name="password" class="grow" placeholder="Mot de passe" required />
        </label>
        <button class="btn btn-primary" type="submit">Inscription</button>
      </form>
    </div>
  </dialog>
</div>

<!-- Styles pour les animations -->
<style>
  .toast .alert {
    transition: opacity 0.5s ease-out, transform 0.5s ease-out;
  }

  .toast .alert.hide {
    opacity: 0;
    transform: translateY(20px);
  }
</style>

<!-- Conteneur pour les toasts (en bas à droite) -->
<div class="toast toast-bottom toast-end">
  <!-- Toast de succès -->
  <c:if test="${not empty param.success}">
    <div id="successToast" class="alert alert-success alert-soft">
      <c:choose>
        <c:when test="${param.success == 'registered'}">
          Inscription réussie ! Bienvenue sur ObjectXChange.
        </c:when>
        <c:when test="${param.success == 'loggedin'}">
          Connexion réussie ! Bienvenue, ${sessionScope.user.name}.
        </c:when>
      </c:choose>
    </div>
  </c:if>

  <!-- Toast d'erreur -->
  <c:if test="${not empty param.error}">
    <div id="errorToast" class="alert alert-error">
      <c:choose>
        <c:when test="${param.error == 'exists'}">
          Erreur : Cet email est déjà utilisé.
        </c:when>
        <c:when test="${param.error == 'invalid'}">
          Erreur : Email ou mot de passe incorrect.
        </c:when>
      </c:choose>
    </div>
  </c:if>
</div>

<!-- Script pour masquer les toasts après 3 secondes -->
<script>
  // Masquer le toast de succès après 3 secondes
  const successToast = document.getElementById('successToast');
  if (successToast) {
    setTimeout(() => {
      successToast.classList.add('hide');
      // Supprimer l'élément du DOM après l'animation
      setTimeout(() => successToast.remove(), 500); // 500 ms = durée de l'animation
    }, 3000); // 3000 ms = 3 secondes
  }

  // Masquer le toast d'erreur après 3 secondes
  const errorToast = document.getElementById('errorToast');
  if (errorToast) {
    setTimeout(() => {
      errorToast.classList.add('hide');
      // Supprimer l'élément du DOM après l'animation
      setTimeout(() => errorToast.remove(), 500); // 500 ms = durée de l'animation
    }, 3000); // 5000 ms = 3 secondes
  }
</script>

<script>
  // Vérifier si le paramètre showLoginModal est présent dans l'URL
  const urlParams = new URLSearchParams(window.location.search);
  const showLoginModal = urlParams.get('showLoginModal');

  // Si le paramètre est présent, ouvrir le modal de connexion
  if (showLoginModal === 'true') {
    document.getElementById('modal_connexion').showModal();
  }
</script>
</body>
</html>
