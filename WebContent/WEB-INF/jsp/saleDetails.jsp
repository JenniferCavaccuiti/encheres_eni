<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../templates/startFile.jsp"%>
<%@page import="fr.eni.encheres.messages.MessagesReader"%>

<div class="container">


	<!--------------------------- Entête -------------------------------------->

	<div class="row">
		<div class="col heading">
			<c:choose>
				<c:when test="${!beforeEnd && login == sessionScope.user.login}">
					<h3 class="text-center">Vous avez remporté la vente</h3>
				</c:when>
				<c:when test="${!beforeEnd && login == null}">
					<h3 class="text-center">Détails de la vente</h3>
				</c:when>
				<c:when test="${!beforeEnd && login != sessionScope.user.login}">
					<h3 class="text-center">${login}a remporté la vente</h3>
				</c:when>
				<c:otherwise>
					<h3 class="text-center">Détails de la vente</h3>
				</c:otherwise>

			</c:choose>
		</div>
	</div>


	<c:if test="${liste != null }">
		<div class="alert alert-danger" role="alert">
			<p>Votre enchère n'a pas été prise en compte</p>
			<ul>
				<c:forEach var="erreur" items="${liste}">
					<li>${MessagesReader.getErrorMessage(erreur)}</li>
				</c:forEach>
			</ul>
		</div>
	</c:if>


	<div class="row align-items-center updateForm">
		
		<c:if test="${!beforeEnd}">
		<div class="alert alert-warning" role="alert">
			<p>Cette enchère est terminée</p>
		</div>
		</c:if>

		<c:if test="${!afterStart}">
		<div class="alert alert-warning" role="alert">
			<p>Cette enchère n'est pas encore commencée</p>
		</div>	
		</c:if>
		</div>
	



	<div class="row">
		<c:if test="${sessionScope.user.administrator == null}">
			<p>Vous ne pouvez pas enchérir votre profil est désactivé</p>
		</c:if>
	</div>


	<!---------------------------------------------------------------------------->


	<div class="row align-items-center updateForm">

		<div class="col">

			<div class="col-auto">
				<label>Nom de l'article</label> <input type="text"
					value="${item.itemName}" class="form-control" disabled>
			</div>

			<div class="col-auto">
				<label>Description : </label> <input type="text"
					value="${item.description}" class="form-control" disabled>
			</div>

			<div class="col-auto">
				<label>Vendeur : </label> <input type="text" name="postalCode"
					value="${item.sellerName}" class="form-control" disabled>
			</div>

		</div>

		<div class="col">

			<div class="col-auto">
				<label>Catégorie : </label> <input type="text" name="lastname"
					value="${item.categoryName}" class="form-control" disabled>
			</div>

			<div class="col-auto">
				<label>Fin de l'enchère : </label> <input type="email" name="email"
					value="${f:formatLocalDateTime(item.bidsEndDate, 'EEEE dd MMMM yyyy HH:mm')}"
					class="form-control" disabled>
			</div>


			<div class="col-auto">
				<label>Adresse de retrait : </label> <input type="text"
					value="${item.street} ${item.postalCode} ${item.city}"
					class="form-control" disabled>
			</div>

			<div class="col-auto"></div>

		</div>
	</div>

	<!---------------------------------------------------------------------------------------------------------------------------->

	<div class="row">

		<div class="col">

			<div class="col-auto">
				<label>Mise à prix : </label> <input type="text" name="street"
					value="${item.initialPrice}" class="form-control" disabled>
			</div>

		</div>
		
		<div class="col">

		<div class=" col alert alert-dark justify-content-center" role="alert">
			<p>
				<label>Meilleure offre : </label> ${item.currentPrice} par <a
					href="profil-utilisateur?login=${login}">${login}</a>
			</p>
		</div>
		
		</div>

	</div>


	<div class="row ">
		<div class="text-center updateProfile" id="modifVente">
		<c:if test="${!afterStart && sessionScope.user.getIdUser() == item.idSeller}">
		<button class="buttonProfile text-center">
		<a onClick="changePage('${item.idItem}', '${pageContext.request.contextPath}/nouvelle-vente');"
			type="submit" role="button">Modifier ma vente</a>
	</button>
</c:if>
</div>
</div>

		<form method="post" action="details-vente">
			<c:if
				test="${afterStart && beforeEnd && sessionScope.user.getIdUser() != item.idSeller && sessionScope.user.administrator != null}">
				<div class="row">
				<div class="col">
					<div class="col-auto updateProfile ">
						<label class="">Ma proposition : </label> <input class="form-control text-center" type="number"
							required="required" min="${item.currentPrice + 1}"
							name="bidderPrice"> <input type="hidden" name="idItem"
							value="${item.idItem}"> <input type="hidden" name="login"
							value="${login}">
						
					</div>

				</div>

				<div class="col">
					<div class="col-auto updateProfile buttonEnchere">
					<button type="submit" class="buttonProfile">Enchérir</button>
					
					</div>

				</div>
				</div>
			</c:if>
		</form>
		
	
		
	</div>

	<div class="row">
		<div class="text-center updateProfile" id="returnEnchere">
			<button class="buttonProfile">
				<a class="lienProfile" href="index">Retour</a>
			</button>
		</div>
	</div>



















<%@include file="../templates/footer.jsp"%>