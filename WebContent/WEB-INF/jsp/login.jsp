<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../templates/startFile.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 
Author: Maxime B.
Date: 19/01/2021
 -->
<title>Login section</title>
</head>
<body>
	<form method="POST" action="login">
		<fieldset>
			<legend>Connexion</legend>
			<label for="login">Identifiant utilisateur<span class="required">*</span></label>
			<input type="text" id="login" name="login" value="<c:out value="${user.login}"/>" size="20" maxlength="60"/>
			<span class="error">${form.errors['login']}</span>
			<br/>
			<label for="password">Mot de passe<span class="required">*</span></label>
			<input type="password" id="password" name="password" value="" size="20" maxlength="20" />
			<span class="error">${form.errors['password']}</span>
			<br/>
			<input type="submit" value="Log in" class="noLabel"/>
			<br/>
			<!--  In case of unknown ID or password, display error or result -->
			<p class="${empty form.errors ? 'success' : 'error'}">${form.result}</p>
			<!--  Vérif de la présence d'une session existente -->
			<c:if test="${!empty sessionScope.sessionUser}">
            	<%-- Si l'utilisateur existe en session, alors on affiche son login --%>
            	<p class="success">Bienvenue ${sessionScope.sessionUser.login}</p>
            </c:if>
		</fieldset>
	</form>
</body>
<footer>
	<%@include file="../templates/footer.jsp"%>
</footer>
</html>