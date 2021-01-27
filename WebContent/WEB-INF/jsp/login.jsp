<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="fr.eni.encheres.messages.MessagesReader"%>
<%@include file="../templates/startFile.jsp"%>


<div class="container">
<div class="bloc-form">
	<form method="POST" action="login">
		<fieldset>
				<legend class="form-title">Connexion</legend>

			<div class="row">
				<div class="col-lg-6">
					<h6>
						<label for="login">Identifiant<span class="required">: </span></label>
					</h6>
				</div>
				<div class="col-lg-6">
					<input type="text" id="login" name="login" placeholder="login ou email" required/>
					<span class="error">${form.errors['login']}</span>
				</div>
			</div>
			<br/>
			<div class="row">
				<div class="col-lg-6">
					<h6>
						<label for="password">Mot de passe<span class="required">: </span></label>
					</h6>
				</div>
				<div class="col-lg-6">
					<input type="password" id="password" name="password" placeholder="mot de passe" required/>
					<span class="error">${form.errors['password']}</span>
				</div>
			</div>
			<br/>
			<div class="row">
				<div class="col-lg-6">
					<input class="btn btn-primary btn-lg btn-block" type="submit" value="Connexion" />
				</div>
				<div class="col-lg-6">
					<input type="checkbox" id="remember" name="remember" value=""/>
					<label for="remember">Se souvenir de moi</label>
					<br/>
					<a href="">Mot de passe oublié</a>
				</div>
			</div>
			<br/>
	<div class="col-lg-12">
	<div class="row">
			<input type="button" class="btn btn-info btn-xl btn-block" value="Créer un compte"  
			onClick="changePage('${pageContext.request.contextPath}/nouveau-profil');"/>
		</div>
	</div>
	</fieldset>
	</form>
</div>
</div>

<c:if test="${listeError != null }">
	<c:forEach var="erreur" items="${listeError}">
	<li>${MessagesReader.getErrorMessage(erreur)}</li>
	</c:forEach>
</c:if>

<%@include file="../templates/footer.jsp"%>