<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../templates/startFile.jsp"%>

<div class="container">

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
		<button class="btn btn-secondary mb-2"><a>Modifier ma vente</a></button>
	</c:if>

	<c:if test="${afterStart && beforeEnd && sessionScope.user.getIdUser() != item.idSeller}">
		<div>
			<label>Ma proposition : </label>
			<input type="number" required="required" min="0">
			<button type="submit" class="btn btn-primary mb-2">Enchérir</button>
		</div>
	</c:if>
</div>

<div class="container">
	<div class="col-auto">
		<button class="btn btn-secondary mb-2">
			<a href="index">Retour</a>
		</button>
	</div>
</div>



<%@include file="../templates/footer.jsp"%>