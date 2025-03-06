<%--
  Created by IntelliJ IDEA.
  User: romai
  Date: 27/02/2025
  Time: 17:27
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
            <div class="font-semibold text-xl">Mes échanges</div>
<%--            <select class="ml-4 mr-4 select select-bordered w-full max-w-xs">--%>
<%--                <option selected>Plus récent</option>--%>
<%--                <option>Plus ancien</option>--%>
<%--            </select>--%>
        </div>

        <div class="overflow-x-auto">
            <table class="table table-zebra">
                <!-- head -->
                <thead>
                <tr>
                    <th></th>
                    <th>Name</th>
                    <th>Job</th>
                    <th>Favorite Color</th>
                </tr>
                </thead>
                <tbody>
                <!-- row 1 -->
                <tr>
                    <th>1</th>
                    <td>Cy Ganderton</td>
                    <td>Quality Control Specialist</td>
                    <td>Blue</td>
                </tr>
                <!-- row 2 -->
                <tr>
                    <th>2</th>
                    <td>Hart Hagerty</td>
                    <td>Desktop Support Technician</td>
                    <td>Purple</td>
                </tr>
                <!-- row 3 -->
                <tr>
                    <th>3</th>
                    <td>Brice Swyre</td>
                    <td>Tax Accountant</td>
                    <td>Red</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
        </div>
</body>
</html>
