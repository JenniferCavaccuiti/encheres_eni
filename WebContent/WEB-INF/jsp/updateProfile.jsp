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
					<label>Pseudo</label> <input type="text" name="login" value="${user.getLogin()}" pattern="[a-zA-Z0-9]+" required="required">
				</div>

				<div class="col-auto">
					<label>Nom : </label> <input type="text" name="lastname" value="${user.getLastname()}" required="required">
				</div>

			</div>
		</div>

		<div class="container">
			<div class="row">
					<div class="col-auto">
						<label>Prénom : </label> <input type="text" name="firstname" value="${user.getFirstname()}" required="required">
					</div>
					<div class="col-auto">
						<label>Email : </label> <input type="email" name="email" value="${user.getEmail()}" required="required">
					</div>
			</div>
		</div>

		<div class="container">
			<div class="row">
					<div class="col-auto">
						<label>Téléphone : </label> <input type="tel" name="phoneNumber" value="${user.getPhoneNumber()}" pattern="(0|\\+33|0033)[1-9][0-9]{8}" required="required">
					</div>

					<div class="col-auto">
						<label>Rue : </label> <input type="text" name="street" value="${user.getStreet()}" required="required">
					</div>
			</div>
		</div>
			
		<div class="container">
			<div class="row">
					<div class="col-auto">
						<label>Code postal : </label> <input type="text" name="postalCode" value="${user.getPostalCode()}" maxlength="5" pattern="^(([0-8][0-9])|(9[0-5]))[0-9]{3}$" required="required">
					</div>
					<div class="col-auto">
						<label>Ville : </label> <input type="text" name="city" value="${user.getCity()}" required="required">
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
							<a href="supprimer-profil">Supprimer mon compte</a>
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