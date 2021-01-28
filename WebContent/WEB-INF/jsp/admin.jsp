<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../templates/startFile.jsp" %>

<div class="table-responsive">
<form action="administration-accueil" method="post">
    <table class="table table-striped">
  
  <thead>
    <tr>
      <th scope="col">ID Utilisateur</th>
      <th scope="col">Pseudo</th>
      <th scope="col">Nom</th>
      <th scope="col">Prénom</th>
      <th scope="col">Email</th>
      <th scope="col">Numéro de téléphone</th>
      <th scope="col">Rue</th>
      <th scope="col">Code postal</th>
      <th scope="col">Ville</th>
      <th scope="col">Crédits</th>
      <th scope="col">Administrateur</th>
      <th scope="col"></th>
      <th scope="col"></th>
    </tr>
  </thead>
  
  <tbody>
  <c:forEach var="simpleUser" items="${users}">
    <tr>
      		<td scope="row">${simpleUser.idUser}</td>
      		<td scope="row">${simpleUser.login}</td>
      		<td scope="row">${simpleUser.lastname}</td>
      		<td scope="row">${simpleUser.firstname}</td>
      		<td scope="row">${simpleUser.email}</td>
      		<td scope="row">${simpleUser.phoneNumber}</td>
      		<td scope="row">${simpleUser.street}</td>
      		<td scope="row">${simpleUser.postalCode}</td>
      		<td scope="row">${simpleUser.city}</td>
      		<td scope="row">${simpleUser.credits}</td>
      		<td scope="row">${simpleUser.administrator}</td>
      		<td scope="row"><button type="submit" name="supprimer" value="${simpleUser.login}" class="btn btn-outline-danger">Supprimer</button></td>
      		<td scope="row"><button type="submit" name="desactiver" value="${simpleUser.login}" class="btn btn-outline-danger">Désactiver</button></td>
      		
    </tr>
   </c:forEach>
  </tbody>
</table>
</form>
</div>

<%@include file="../templates/footer.jsp" %>