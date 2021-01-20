<%@include file="../templates/startFile.jsp" %>

<h1>Mon profil</h1>

<form method="post" action="nouveauProfil">
<section>
<label>Pseudo : </label><input type="text" name="login">
<label>Prénom : </label> <input type="text" name="firstname">
<label>Téléphone : </label> <input type="tel" name="phoneNumer">
<label>Code postal : </label><input type="text" name="postalCode">
<label>Mot de passe : </label><input type="password" name="password">
</section>
<section>
<label>Nom : </label><input type="text" name="lastname">
<label>Email : </label> <input type="email" name="email">
<label>Rue : </label> <input type="text" name="street">
<label>Ville : </label><input type="text" name="city">
<label>Confirmation : </label><input type="text" name="passwordConfirm">
</section>

<input type="submit" value="Créer"><button><a href="<%=response.encodeURL(request.getContextPath() + "/index")%>">Annuler</a></button>

</form>

<%@include file="../templates/footer.jsp" %>
