<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../templates/startFile.jsp"%>

<form action="administration-actions" method="post">

<c:if test="${supprimer != null }">

	<h1>Supprimer le profil utilisateur</h1>

	<p>Êtes-vous sûr de vouloir supprimer le profil de ${simpleUser} ?</p>

	<c:if test="${liste != null }">
		<p>La suppression du profil a échoué</p>
		<ul>
			<c:forEach var="erreur" items="${liste}">
				<li>${MessagesReader.getErrorMessage(erreur)}</li>
			</c:forEach>
		</ul>
	</c:if>
	<input type="hidden" name="action" value="delete">
</c:if>

<c:if test="${desactiver != null }">


	<h1>Désactiver le profil utilisateur</h1>

	<p>Êtes-vous sûr de vouloir désactiver ce profil de ${simpleUser} ?</p>


	<c:if test="${liste != null }">

		<p>La désactivation du profil a échoué</p>
		<ul>
			<c:forEach var="erreur" items="${liste}">
				<li>${MessagesReader.getErrorMessage(erreur)}</li>
			</c:forEach>
		</ul>
	</c:if>
	<input type="hidden" name="action" value="deactivate">
</c:if>



	<div class="container">
		<div class="row">
			<div class="col-auto">
				<input type="hidden" name="login" value="${simpleUser}">
				<button type="submit" class="btn btn-primary mb-2">Confirmer</button>
			</div>
			<div class="col-auto">
				<button class="btn  btn-secondary mb-2">
					<a href="administration-accueil">Annuler</a>
				</button>
			</div>
		</div>
	</div>
</form>



<%@include file="../templates/footer.jsp"%>