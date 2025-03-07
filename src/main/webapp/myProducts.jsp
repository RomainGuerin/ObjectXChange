<%--
  Created by IntelliJ IDEA.
  User: riboubenjamin
  Date: 05/03/2025
  Time: 19:41
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html data-theme="emerald" xml:lang="fr" lang="fr">

<head>
  <title>Mes échanges</title>
  <link href="https://cdn.jsdelivr.net/npm/daisyui@4.12.24/dist/full.min.css" rel="stylesheet" />
  <script src="https://cdn.tailwindcss.com"></script>
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
              <button class="btn btn-ghost m-1">
                Bienvenue, ${sessionScope.user.name}
              </button>
              <ul class="dropdown-content menu bg-base-100 rounded-box z-1 w-52 p-2 shadow-sm">
                <li><a href="${pageContext.request.contextPath}/products/user">Mes produits</a></li>
                <li><a href="${pageContext.request.contextPath}/exchange">Mes échanges</a></li>
              </ul>
            </div>
            <li><a class="ml-2 btn btn-outline btn-error" href="${pageContext.request.contextPath}/logout">Déconnexion</a></li>
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
  <div class="mb-6 flex items-center">
    <div class="font-semibold text-xl">Mes produits</div>
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
        </div>
      </div>
    </c:forEach>
  </div>
</div>

</body>
</html>