<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@page import="fr.eni.encheres.messages.MessagesReader" %>
<%@include file="../templates/startFile.jsp" %>

<!-- test pété -->
<div class="bloc-form">
    <div class="card">
        <form method="POST" action="login">
            <fieldset>
                <div class="card-header header-color">Connexion</div>
                <div class="card-body">

                    <!-- Identifiant -->
                    <div class="row">
                        <div class="col-sm-5">
                            <h6>
                                <label for="login">Identifiant<span class="required">:</span></label>
                            </h6>
                        </div>
                        <div class="col-sm-4">
                            <input type="text" id="login" name="login" placeholder="login ou email" required/>
                            <span class="error">${form.errors['login']}</span>
                        </div>
                    </div>
                    <br/>

                    <!-- Mot de passe -->
                    <div class="row">
                        <div class="col-sm-5">
                            <h6>
                                <label for="password">Mot de passe<span class="required">:</span></label>
                            </h6>
                        </div>
                        <div class="col-sm-4">
                            <input type="password" id="password" name="password" placeholder="mot de passe" required/>
                            <span class="error">${form.errors['password']}</span>
                        </div>
                    </div>
                    <br/>

                    <!-- Connexion++ -->
                    <div class="row">
                        <div class="col-sm-5">
                            <input type="submit" class="btn btn-secondary" value="Connexion"/>
                        </div>
                        <div class="col-sm-7">
                            <input type="checkbox" id="remember" name="remember" value=""/>
                            <label for="remember">Se souvenir de moi</label>
                            <br/>
                            <a class="dark-blue-color" href="">Mot de passe oublié</a>
                        </div>
                    </div>
                    <br/>

                    <!-- Bouton créer un compte -->
                    <div class="row">
                        <button type="submit" class="button" onclick="callServlet('${pageContext.request.contextPath}/nouveau-profil')">
                            Créer un profil
                        </button>
                    </div>
                </div>
            </fieldset>
        </form>
    </div>
</div>


<c:if test="${listeError != null }">
    <c:forEach var="erreur" items="${listeError}">
        <li>${MessagesReader.getErrorMessage(erreur)}</li>
    </c:forEach>
</c:if>

<%@include file="../templates/footer.jsp" %>