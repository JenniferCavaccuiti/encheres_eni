<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="fr.eni.encheres.messages.MessagesReader"%>
<%@include file="../templates/startFile.jsp"%>

	<form method="POST" action="login">
		<fieldset>
			<legend>Connexion</legend>
			<label for="login">Identifiant utilisateur<span class="required">*</span></label>
			<input type="text" id="login" name="login" size="20" maxlength="60"/>
			<span class="error">${form.errors['login']}</span>
			<br/>
			<label for="password">Mot de passe<span class="required">*</span></label>
			<input type="password" id="password" name="password" size="20" maxlength="20" />
			<span class="error">${form.errors['password']}</span>
			<br/>
			<input type="submit" value="Log in" class="noLabel"/>
			<br/>
		</fieldset>
	</form>
<c:if test="${listeError != null }">
	<c:forEach var="erreur" items="${listeError}">
	<li>${MessagesReader.getErrorMessage(erreur)}</li>
	</c:forEach>
</c:if>

<%@include file="../templates/footer.jsp"%>