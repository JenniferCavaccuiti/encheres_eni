<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../templates/startFile.jsp"%>
<%@page import="java.util.List"%>
<%@page import="fr.eni.encheres.messages.MessagesReader"%>

<h1>Mon profil</h1>



<c:if test="${liste != null }">

	<p>La création du profil a échoué</p>
	<c:forEach var="erreur" items="${liste}">
		<li>${MessagesReader.getErrorMessage(erreur)}</li>
	</c:forEach>

</c:if>
<ul>

</ul>





<form method="post" action="nouveauProfil">
	<section>
		<label>Pseudo : </label><input type="text" name="login"
			required="required"> <label>Prénom : </label> <input
			type="text" name="firstname" required="required"> <label>Téléphone
			: </label> <input type="tel" name="phoneNumber" required="required">
		<label>Code postal : </label><input type="text" name="postalCode"
			required="required"> <label>Mot de passe : </label><input
			type="password" name="password" required="required">
	</section>
	<section>
		<label>Nom : </label><input type="text" name="lastname"
			required="required"> <label>Email : </label> <input
			type="email" name="email" required="required"> <label>Rue
			: </label> <input type="text" name="street" required="required"> <label>Ville
			: </label><input type="text" name="city" required="required"> <label>Confirmation
			: </label><input type="text" name="passwordConfirm" required="required">
	</section>
	<input type="submit" value="Créer">
	<button>
		<a href="<%=response.encodeURL(request.getContextPath() + "/index")%>">Annuler</a>
	</button>

</form>

<%@include file="../templates/footer.jsp"%>
