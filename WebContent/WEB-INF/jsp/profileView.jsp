<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../templates/startFile.jsp" %>

<div class="container">

<label for="login">Pseudo : </label><p>${userProfile.getLogin()}</p>
<label for="lastname">Nom : </label><p>${userProfile.getLastname()}</p>
<label for="firstname">Prénom : </label><p> ${userProfile.getFirstname()}</p>
<label for="email">Email : </label><p>${userProfile.getEmail()}</p>
<label for="phoneNumber">Téléphone : </label><p>${userProfile.getPhoneNumber()}</p>
<label for="street">Rue : </label><p>${userProfile.getStreet()}</p>
<label for="postalCode">Code postal :</label><p>${userProfile.getPostalCode()}</p>
<label for="city">Ville : </label><p>${userProfile.getCity()}</p>
</div>

<c:if test="${not empty sessionScope} && ${String.valueOf(userProfile.getIdUser()).equals(session.getId())}">

	 <div class="col-auto">
      <button type="submit" class="btn btn-primary mb-2">Modifier</button>
    </div>

</c:if>
<div class="container">
<div class="col-auto">
	 <button class="btn btn-secondary mb-2"><a href="index">Retour</a></button>
</div>
</div>


<%@include file="../templates/footer.jsp" %>