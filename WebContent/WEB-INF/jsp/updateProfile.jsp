<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../templates/startFile.jsp"%>
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

<form method="post" action="modifier-profil" novalidate
	class="needs-validation">

	<div class="container">
		<div class="row">

			<div class="col-auto">
				<label>Pseudo</label> <input type="text" name="login"
					value="${user.getLogin()}" class="form-control"
					pattern="[a-zA-Z0-9]+" required="required"  maxlength="30">
				<div class="invalid-feedback">Le pseudo est obligatoire et
					doit contenir uniquement des caractères alphanumériques</div>
			</div>

			<div class="col-auto">
				<label>Nom : </label> <input type="text" name="lastname"
					value="${user.getLastname()}" class="form-control"
					required="required" maxlength="30">
				<div class="invalid-feedback">Le nom est obligatoire</div>
			</div>

		</div>
	</div>

	<div class="container">
		<div class="row">
			<div class="col-auto">
				<label>Prénom : </label> <input type="text" name="firstname"
					value="${user.getFirstname()}" class="form-control"
					required="required" maxlength="30">
				<div class="invalid-feedback">Le prénom est obligatoire</div>
			</div>
			<div class="col-auto">
				<label>Email : </label> <input type="email" name="email"
					value="${user.getEmail()}" class="form-control" required="required" maxlength="50">
				<div class="invalid-feedback">L'email est obligatoire</div>
			</div>
		</div>
	</div>

	<div class="container">
		<div class="row">
			<div class="col-auto">
				<label>Téléphone : </label> <input type="tel" name="phoneNumber"
					value="${user.getPhoneNumber()}" class="form-control"
					pattern="(0|\\+33|0033)[1-9][0-9]{8}" required="required" minlength="10" maxlength="10">
				<div class="invalid-feedback">Le numéro de téléphone est
					obligatoire</div>
			</div>

			<div class="col-auto">
				<label>Rue : </label> <input type="text" name="street"
					value="${user.getStreet()}" class="form-control" maxlength="150"
					required="required">
				<div class="invalid-feedback">Le nom de rue est obligatoire</div>
			</div>
		</div>
	</div>

	<div class="container">
		<div class="row">
			<div class="col-auto">
				<label>Code postal : </label> <input type="text" name="postalCode"
					value="${user.getPostalCode()}" maxlength="5"
					pattern="^(([0-8][0-9])|(9[0-5]))[0-9]{3}$" required="required"
					class="form-control">
				<div class="invalid-feedback">Le code postal est obligatoire
					et doit être au format, ex : 56000</div>
			</div>
			<div class="col-auto">
				<label>Ville : </label> <input type="text" name="city"
					value="${user.getCity()}" required="required" class="form-control" maxlength="100">
			</div>
		</div>
	</div>

	<div class="container">
		<div class="row">
			<div class="col-auto">
				<label>Mot de passe actuel : </label> <input type="password"
					name="oldPassword" required="required" class="form-control"  maxlength="30">
				<div class="invalid-feedback">Le mot de passe est obligatoire
				</div>
			</div>
		</div>
	</div>

	<div class="container">
		<div class="row">
			<div class="col-auto">
				<label>Nouveau mot de passe : </label> <input type="password"
					name="newPassword" class="form-control"  maxlength="30">
			</div>
			<div class="col-auto">
				<label>Confirmation : </label> <input type="password"
					name="passwordConfirm" class="form-control"  maxlength="30">
			</div>
		</div>
	</div>

	<div class="container">
		<div class="row">
			<div class="col-auto">
				<label>Crédit</label> <input type="text" class="form-control"
					value="${user.getCredits()}" disabled>
			</div>
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
<%@include file="../templates/footer.jsp"%>