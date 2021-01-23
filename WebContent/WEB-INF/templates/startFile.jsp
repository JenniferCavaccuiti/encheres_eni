
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://example.com/functions" prefix="f" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Enchères Eni</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
</head>
<body class="container-fluid">
<header>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="/enchere_test/index">
                <%--                Ici, logo du site--%>
                <img src="img/logo1.png" alt="" width="30" height="24" class="d-inline-block align-top">
                Enchères Eni
            </a>
        </div>

        <div class="container-fluid">
            <c:choose>
            <c:when test="${empty sessionScope}">
                <div class="collapse navbar-collapse" id="disconnect">
                    <div class="navbar-nav">
                        <a class="nav-link active" aria-current="page" href="nouveau-profil">Se connecter / s'inscrire</a>
                    </div>
                </div>
            </c:when>
            <c:when test="${not empty sessionScope}">
                <div class="collapse navbar-collapse" id="connect">
                    <div class="navbar-nav">
                        <a class="nav-link active" aria-current="page" href="#">Enchères</a>
                        <a class="nav-link" href="#">Vendre un article</a>
                        <a class="nav-link" href="profil-utilisateur?login=${sessionScope.user.getLogin()}">Mon profil</a>
                        <a class="nav-link" href="deconnexion" tabindex="-1">Déconnexion</a>
                    </div>
                </div>
            </c:when>
            </c:choose>
        </div>
    </nav>
</header>

