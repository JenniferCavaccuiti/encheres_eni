<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../templates/startFile.jsp" %>

<h1>Supprimer mon profil</h1>

<p>Êtes-vous sûr de vouloir supprimer votre profil ?</p>

<c:if test="${liste != null }">

	<p>La suppression du profil a échoué</p>
	<ul>
	<c:forEach var="erreur" items="${liste}">
		<li>${MessagesReader.getErrorMessage(erreur)}</li>
	</c:forEach>
	</ul>
</c:if>

<form action="supprimer-profil" method="post">

<div class="container">
			<div class="row">
					<div class="col-auto">
						<button type="submit" class="btn btn-primary mb-2">Confirmer</button>
					</div>
					<div class="col-auto">
						<button class="btn  btn-secondary mb-2">
							<a href="modifier-profil">Annuler</a>
						</button>
					</div>
			</div>
		</div>
</form>

<%@include file="../templates/footer.jsp" %>
