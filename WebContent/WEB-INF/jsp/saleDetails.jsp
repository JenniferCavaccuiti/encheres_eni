<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../templates/startFile.jsp"%>
<%@page import="fr.eni.encheres.messages.MessagesReader"%>

<div class="container">
<c:choose>
	<c:when test="${!beforeEnd && login == sessionScope.user.login}">
		<h3>Vous avez remporté la vente</h3>
	</c:when>
	<c:when test="${!beforeEnd && login == null}">
		<h3>Détails de la vente</h3>
	</c:when>
	<c:when test="${!beforeEnd && login != sessionScope.user.login}">
		<h3>${login} a remporté la vente</h3>
	</c:when>
	<c:otherwise>
		<h3>Détails de la vente</h3>
	</c:otherwise>

</c:choose>
</br>

	<div>
		<h6>${item.itemName}</h6>
	</div>

	<div>
		<label>Description : </label>
		<p>${item.description}</p>
	</div>

	<div>
		<label>Catégorie : </label>
		<p>${item.categoryName}</p>
	</div>

	<div>
		<label>Meilleure offre : </label>
		<p>${item.currentPrice} par <a href="profil-utilisateur?login=${login}">${login}</a></p>
	</div>

	<div>
		<label>Mise à prix : </label>
		<p>${item.initialPrice}</p>
	</div>

	<div>
		<label>Fin de l'enchère</label>
		<p>${f:formatLocalDateTime(item.bidsEndDate, 'EEEE dd MMMM yyyy HH:mm')}</p>
	</div>

	<div>
		<label>Adresse de retrait : </label>
		<p>${item.street} ${item.postalCode} ${item.city}</p>
	</div>

	<div>
		<label>Vendeur : </label>
		<p>${item.sellerName}</p>
	</div>



	<c:if test="${!beforeEnd}">
		<p>Cette enchère est terminée</p>
	</c:if>

	<c:if test="${!afterStart}">
		<p>Cette enchère n'est pas encore commencée</p>
	</c:if>
	
	<c:if test="${!afterStart && sessionScope.user.getIdUser() == item.idSeller}">
			<a onClick="changePage('${item.idItem}', '${pageContext.request.contextPath}/nouvelle-vente');" class="btn btn-default" type="submit" role="button">Modifier ma vente</a>
	</c:if>

<c:if test="${liste != null }">

	<p>Votre enchère n'a pas été prise en compte</p>
	<ul>
	<c:forEach var="erreur" items="${liste}">
		<li>${MessagesReader.getErrorMessage(erreur)}</li>
	</c:forEach>
	</ul>
</c:if>


<form method="post" action="details-vente">
	<c:if test="${afterStart && beforeEnd && sessionScope.user.getIdUser() != item.idSeller}">

		<div>
			<label>Ma proposition : </label>
			<input type="number" required="required" min="${item.currentPrice + 1}" name="bidderPrice">
			<input type="hidden" name="idItem" value="${item.idItem}">
			<input type="hidden" name="login" value = "${login}">
			<button type="submit" class="btn btn-primary mb-2">Enchérir</button>
		</div>
	</c:if>
</div>
</form>

<div class="container">
	<div class="col-auto">
		<button class="btn btn-secondary mb-2">
			<a href="index">Retour</a>
		</button>
	</div>
</div>



<%@include file="../templates/footer.jsp"%>