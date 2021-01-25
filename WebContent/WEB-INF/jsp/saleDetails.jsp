<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../templates/startFile.jsp" %>

<div class="container">

<div>
<h6>${item.itemName}</h6>
</div>

<div>
<label>Description : </label><p>${item.description}</p>
</div>

<div>
<label>Catégorie : </label><p>${item.categoryName}</p>
</div>

<div>
<label>Meilleure offre : </label><p>${item.currentPrice}</p>
</div>

<div>
<label>Mise à prix : </label><p>${item.initialPrice}</p>
</div>

<div>
<label>Fin de l'enchère</label><p> ${f:formatLocalDateTime(item.bidsEndDate, 'EEEE dd MMMM yyyy HH:mm')}</p>
</div>

<div>
<label>Adresse de retrait : </label><p>${item.street} ${item.postalCode} ${item.city}</p>
</div>

<div>
<label>Vendeur : </label><p>${item.sellerName}</p>
</div>

</div>

<div class="container">
	<div class="col-auto">
		<button class="btn btn-secondary mb-2">
			<a href="index">Retour</a>
		</button>
	</div>
</div>

<%@include file="../templates/footer.jsp" %>