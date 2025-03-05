<%--
  Created by IntelliJ IDEA.
  User: riboubenjamin
  Date: 05/03/2025
  Time: 19:41
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html data-theme="emerald">
<head>
  <title>Mes échanges</title>
  <link href="https://cdn.jsdelivr.net/npm/daisyui@4.12.24/dist/full.min.css" rel="stylesheet" type="text/css" />
  <script src="https://cdn.tailwindcss.com"></script>
</head>
<body>

<div class="navbar bg-base-100 mb-8">
  <div class="flex-1">
    <a class="ml-4" href="/">
      <img src="images/logo.png" alt="Logo ObjectXChange" class="h-10">
    </a>
  </div>
  <div class="flex-none">
    <ul class="menu menu-horizontal px-1">
      <c:choose>
        <c:when test="${not empty sessionScope.user}">
          <div class="flex items-center">
            <div class="dropdown">
              <div tabindex="0" role="button" class="btn btn-ghost m-1">Bienvenue, ${sessionScope.user.name}</div>
              <ul tabindex="0" class="dropdown-content menu bg-base-100 rounded-box z-1 w-52 p-2 shadow-sm">
                <li><a href="/products/user">Mes produits</a></li>
                <li><a href="/exchange.jsp">Mes échanges</a></li>
              </ul>
            </div>
            <div>
              <li><a class="ml-2 btn btn-outline btn-error" href="/logout">Déconnexion</a></li>
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
  <div class="mb-6 flex items-center">
    <div class="font-semibold text-xl">Mes produits</div>
  </div>

  <div class="grid grid-cols-1 md:grid-cols-3 gap-4 mb-4">
    <c:forEach var="product" items="${productList}">
      <div class="card card-compact bg-base-100 shadow-xl">
        <figure>
          <img src="${product.image}" alt="${product.name}" class="w-full h-52 object-cover"/>
        </figure>
        <div class="card-body">
          <h2 class="card-title">
              ${product.name}
            <div class="badge">Catégorie ID: ${product.categoryId}</div>
          </h2>
          <p>${product.description}</p>
        </div>
      </div>
    </c:forEach>
  </div>
</div>

</body>
</html>
