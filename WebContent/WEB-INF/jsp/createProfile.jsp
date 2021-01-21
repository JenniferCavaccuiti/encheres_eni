<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../templates/startFile.jsp"%>
<%@page import="java.util.List"%>
<%@page import="fr.eni.encheres.messages.MessagesReader"%>

<h1>Mon profil</h1>



<c:if test="${liste != null }">

	<p>La création du profil a échoué</p>
	<ul>
	<c:forEach var="erreur" items="${liste}">
		<li>${MessagesReader.getErrorMessage(erreur)}</li>
	</c:forEach>
	</ul>
</c:if>


<form method="post" action="nouveauProfil">

<div class="container">

<div class="row">
  
    <div class="col-auto">
      <label>Pseudo</label>
      <input type="text" name ="login" required="required">
    </div>
    
    <div class="col-auto">
     	<label>Nom : </label>
		<input type="text" name="lastname" required="required"> 
    </div>
    
    <div class="col-auto">
        <label>Prénom : </label> 
		<input type="text" name="firstname" required="required"> 
    </div>
 
 </div>

</div>
  
<div class="container">
    
  <div class="row">
   
    <div class="col-auto">
        <label>Téléphone : </label> 
		<input type="tel" name="phoneNumber" required="required"> 
    </div>
    
    <div class="col-auto">
       <label>Code postal : </label>
		<input type="text" name="postalCode" required="required"> 
    </div>
    
    <div class="col-auto">
       <label>Mot de passe : </label>
		<input type="password" name="password" required="required"> 
    </div>
   
  </div>
</div>
 <div class="container">
  <div class="row">
      <div class="col-auto">
         <label>Email : </label> 
		<input type="email" name="email" required="required">  
     </div>
     
      <div class="col-auto">
         <label>Rue : </label> 
		<input type="text" name="street" required="required">  
     </div> 
     
      <div class="col-auto">
      <label>Ville : </label>
		<input type="text" name="city" required="required"> 
      </div>
 	</div>
 </div>
 <div class="container">
  <div class="row">
      <div class="col-auto">
      <label>Confirmation	: </label>
		<input type="password" name="passwordConfirm" required="required">
      </div>
     
    <div class="col-auto">
      <button type="submit" class="btn btn-primary mb-2">Créer</button>
    </div>
    <div class="col-auto">
    <button  class="btn  btn-secondary mb-2"><a href="<%=response.encodeURL(request.getContextPath() + "/index")%>">Annuler</a></button>
    </div>
</div>
</div>
</form>

<%@include file="../templates/footer.jsp"%>
