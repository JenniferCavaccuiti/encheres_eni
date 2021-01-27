<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="fr.eni.encheres.messages.MessagesReader"%>
<%@include file="../templates/startFile.jsp"%>

	<form method="POST" action="login">
		<fieldset>
			<legend>Connexion</legend>
			<label for="login">Identifiant<span class="required">*</span></label>
			<input type="text" id="login" name="login" size="20" maxlength="60" required/>
			<span class="error">${form.errors['login']}</span>
			<br/>
			<label for="password">Mot de passe<span class="required">*</span></label>
			<input type="password" id="password" name="password" size="20" maxlength="20" required/>
			<span class="error">${form.errors['password']}</span>
			<br/>
			<input type="submit" value="Log in" class="noLabel"/>
			<input type="checkbox" id="remember" name="remember" value=""/>
			<label for="remember">Se souvenir de moi</label>
			<br/>
			<a href="">Mot de passe oublié</a>
		</fieldset>
	</form>
	<button type="submit" class="btn btn-secondary mb-3"><a href="nouveau-profil">Créer un compte</a></button>
	
	
<c:if test="${listeError != null }">
	<c:forEach var="erreur" items="${listeError}">
	<li>${MessagesReader.getErrorMessage(erreur)}</li>
	</c:forEach>
</c:if>

<%@include file="../templates/footer.jsp"%>