<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../templates/startFile.jsp" %>
<%@page import="fr.eni.encheres.messages.MessagesReader"%>

<div class="container">

<div class="row">
		<div class="col heading">
			<h3 class="text-center">Supprimer mon profil</h3>
		</div>
	</div>

<div class="row">
<p  class="alert alert-warning text-center">Êtes-vous sûr de vouloir supprimer votre profil ?</p>
</div>

<c:if test="${liste != null }">
<div class="alert alert-danger" role="alert">
	<p>La suppression du profil a échoué</p>
	<ul>
	<c:forEach var="erreur" items="${liste}">
		<li>${MessagesReader.getErrorMessage(erreur)}</li>
	</c:forEach>
	</ul>
</div>
</c:if>

<form action="supprimer-profil" method="post">

        <div class="row">

            <div class="col-md-3 offset-md-3 text-center updateProfile deleteButton">
            <button class="buttonProfile" type="submit">Confirmer</button>
            </div>

            <div class="col-md-3 text-center updateProfile deleteButton">
                <button class="buttonProfile">
                    	<a class="lienProfile" href="modifier-profil">Annuler</a>
                </button>
            </div>
        </div>
	
</form>

</div>

<%@include file="../templates/footer.jsp" %>
