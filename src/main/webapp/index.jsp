<%--
  Created by IntelliJ IDEA.
  User: romai
  Date: 27/02/2025
  Time: 15:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Bienvenue sur ObjectXChange</title>
</head>
<body>
<h1>ObjectXChange</h1>
<a href="register.jsp">Inscription</a> | <a href="login.jsp">Connexion</a>
<br>
<br>

<div>Liste objets</div>

<select>
  <option>category 1</option>
  <option>category 2</option>
</select>

<button popovertarget="my-popover222">Proposer un object à l'échange</button>

<input placeholder="search"><button>🔍</button></input>

<ul>
  <li>
    toto 1
    <button popovertarget="my-popover">Echanger</button>
  </li>
  <li>
      toto 2
    <button popovertarget="my-popover">Echanger</button>
  </li>
</ul>

<div popover id="my-popover">popup (liste déroulante affichant la liste des produits de l'utilisateur)</div>

<div popover id="my-popover222">popup (ajout d'objet)</div>

</body>
</html>
