<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../templates/startFile.jsp"%>

<div class="container">
<form action="administration-actions" method="post">

<c:if test="${supprimer != null }">

	<div class="row">
		<div class="col heading">
			<h3 class="text-center">Supprimer le profil utilisateur</h3>
		</div>
	</div>

<div class="row">
<p  class="alert alert-warning text-center">Êtes-vous sûr de vouloir supprimer le profil de ${simpleUser} ?</p>
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
	<input type="hidden" name="action" value="delete">
</c:if>

<c:if test="${desactiver != null }">


	<div class="row">
		<div class="col heading">
			<h3 class="text-center">Désactiver le profil utilisateur</h3>
		</div>
	</div>

<div class="row">
<p  class="alert alert-warning text-center">Êtes-vous sûr de vouloir désactiver le profil de ${simpleUser} ?</p>
</div>



	<c:if test="${liste != null }">
		<div class="alert alert-danger" role="alert">
		<p>La désactivation du profil a échoué</p>
		<ul>
			<c:forEach var="erreur" items="${liste}">
				<li>${MessagesReader.getErrorMessage(erreur)}</li>
			</c:forEach>
		</ul>
		</div>
	</c:if>
	<input type="hidden" name="action" value="deactivate">
</c:if>

 <div class="row">

            <div class="col-md-3 offset-md-3 text-center updateProfile deleteButton">
            <input type="hidden" name="login" value="${simpleUser}">
            <button class="buttonProfile" type="submit">Confirmer</button>
            </div>

            <div class="col-md-3 text-center updateProfile deleteButton">
                <button class="buttonProfile">
                    	<a class="lienProfile" href="administration-accueil">Annuler</a>
                </button>
            </div>
 </div>

</form>

</div>

<%@include file="../templates/footer.jsp"%>