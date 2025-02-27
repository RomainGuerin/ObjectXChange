<%--
  Created by IntelliJ IDEA.
  User: romai
  Date: 27/02/2025
  Time: 17:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="index.jsp">Retour à l'accueil</a>
<form action="register" method="post">
    <input type="text" name="name" placeholder="Nom" required>
    <input type="email" name="email" placeholder="Email" required>
    <input type="password" name="password" placeholder="Mot de passe" required>
    <button type="submit">S'inscrire</button>
    <a href="login.jsp">Connexion</a>
</form>

</body>
</html>
