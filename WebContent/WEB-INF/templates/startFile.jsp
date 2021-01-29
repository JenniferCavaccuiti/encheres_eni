<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://example.com/functions" prefix="f" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Enchères Eni</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
          integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
</head>
<body class="fond-body">
<header>
    <nav class="navbar navbar-expand-lg d-flex justify-content-around">
        <a class="navbar-brand white-color ml-4" href="index">
            <img src="img/logo1.png" alt="" width="30" height="24" class="d-inline-block align-top">
            Enchères Eni
        </a>
        <button class="navbar-toggler" id="toggle-button" type="button" data-toggle="collapse"
                data-target="#navbarNavAltMarkup"
                aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse justify-content-end" id="navbarNavAltMarkup">
            <div class="navbar-nav ">
                <c:choose>
                    <c:when test="${empty sessionScope}">
                        <div class="collapse navbar-collapse" id="disconnect">
                            <div class=" navbar-nav">
                                <a class="nav-item nav-link white-color" aria-current="page" href="login">
                                    <span>Se connecter / s'inscrire</span>
                                </a>
                            </div>
                        </div>
                    </c:when>
                    <c:when test="${not empty sessionScope}">
                        <div class="d-flex navbar-nav">
                            <div class="collapse navbar-collapse" id="connect">
                                <div class="navbar-nav">
                                    <c:if test="${not empty sessionScope && sessionScope.user.administrator == '1'}">
                                        <a class="nav-item nav-link white-color"
                                           href="administration-accueil">Administration</a>
                                    </c:if>
                                    <a class="nav-item nav-link active white-color" aria-current="page"
                                       href="index">Enchères</a>
                                    <c:if test="${not empty sessionScope && sessionScope.user.administrator != null}">
                                        <a class="nav-item nav-link white-color" href="nouvelle-vente">Vendre un
                                            article</a>
                                    </c:if>
                                    <a class="nav-item nav-link white-color"
                                       href="profil-utilisateur?login=${sessionScope.user.getLogin()}">Mon
                                        profil</a>
                                    <a class="nav-item nav-link white-color" href="deconnexion" tabindex="-1">Déconnexion</a>
                                </div>
                            </div>
                        </div>
                    </c:when>
                </c:choose>
            </div>
        </div>
    </nav>
</header>
<main class="container-fluid">

