<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../templates/startFile.jsp" %>
<%@page import="fr.eni.encheres.messages.MessagesReader"%>

<h1>Modifier mon profil</h1>

<c:if test="${liste != null }">

	<p>La création du profil a échoué</p>
	<ul>
	<c:forEach var="erreur" items="${liste}">
		<li>${MessagesReader.getErrorMessage(erreur)}</li>
	</c:forEach>
	</ul>
</c:if>

	<form method="post" action="modifier-profil">

		<div class="container">
			<div class="row">
			
				<div class="col-auto">
					<label>Pseudo</label> <input type="text" name="login" placeholder="${user.getLogin()}">
				</div>

				<div class="col-auto">
					<label>Nom : </label> <input type="text" name="lastname" placeholder="${user.getLastname()}">
				</div>

			</div>
		</div>

		<div class="container">
			<div class="row">
					<div class="col-auto">
						<label>Prénom : </label> <input type="text" name="firstname" placeholder="${user.getFirstname()}">
					</div>
					<div class="col-auto">
						<label>Email : </label> <input type="email" name="email" placeholder="${user.getEmail()}">
					</div>
			</div>
		</div>

		<div class="container">
			<div class="row">
					<div class="col-auto">
						<label>Téléphone : </label> <input type="tel" name="phoneNumber" placeholder="${user.getPhoneNumber()}">
					</div>

					<div class="col-auto">
						<label>Rue : </label> <input type="text" name="street" placeholder="${user.getStreet()}">
					</div>
			</div>
		</div>
			
		<div class="container">
			<div class="row">
					<div class="col-auto">
						<label>Code postal : </label> <input type="text" name="postalCode" placeholder="${user.getPostalCode()}">
					</div>
					<div class="col-auto">
						<label>Ville : </label> <input type="text" name="city" placeholder="${user.getCity()}">
					</div>
			</div>
		</div>
		
		<div class="container">
			<div class="row">
				<div class="col-auto">
						<label>Mot de passe actuel : </label> <input type="password"
							name="oldPassword" required="required">
				</div>
			</div>
		</div>
		
		<div class="container">
			<div class="row">
				<div class="col-auto">
						<label>Nouveau mot de passe : </label> <input type="password"
							name="newPassword">
				</div>			
				<div class="col-auto">
						<label>Confirmation : </label> <input type="password"
							name="passwordConfirm">
				</div>
			</div>
		</div>
		
		<div class="container">
			<div class="row">
			<label>Crédit</label><p>${user.getCredits()}</p>  <!-- TODO Récupérer le crédit de l'utilisateur !! -->
			</div>
		</div>
		
		<div class="container">
			<div class="row">
					<div class="col-auto">
						<button type="submit" class="btn btn-primary mb-2">Enregistrer</button>
					</div>
					<div class="col-auto">
						<button class="btn  btn-secondary mb-2">
							<a href="#">Supprimer mon compte</a>
						</button>
					</div>
					<div class="col-auto">
						<button class="btn  btn-secondary mb-2">
							<a href="profil-utilisateur?login=${sessionScope.user.getLogin()}">Retour</a>
						</button>
					</div>
			</div>
		</div>
	</form>
<%@include file="../templates/footer.jsp" %>