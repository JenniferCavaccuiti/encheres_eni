<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../templates/startFile.jsp"%>
<%@page import="java.util.List"%>
<%@page import="fr.eni.encheres.messages.MessagesReader"%>

<h1>Créer un nouveau profil</h1>



<c:if test="${liste != null }">

	<p>La création du profil a échoué</p>
	<ul>
		<c:forEach var="erreur" items="${liste}">
			<li>${MessagesReader.getErrorMessage(erreur)}</li>
		</c:forEach>
	</ul>
</c:if>


<form method="post" action="nouveau-profil" novalidate
	class="needs-validation">

	<div class="container">

<<<<<<< HEAD
<table class="table">
 	<tr>
    	<th scope="col" class="col-auto">
      		<label>Pseudo</label>
     		<input type="text" name ="login" required="required" pattern="[a-zA-Z0-9]+">
    	</th>
   		<th scope="col" class="col-auto">
     		<label>Nom : </label>
			<input type="text" name="lastname" required="required"> 
    	</th>
    	<th scope="col" class="col-auto">
        	<label>Prénom : </label> 
			<input type="text" name="firstname" required="required"> 
    	</th>
  	</tr>
</table>
=======
		<div class="row">
>>>>>>> refs/heads/master

<<<<<<< HEAD
</div>
  
<div class="container">
    
  <table class="table">
   <tr>
   
    <th class="col-auto">
        <label>Téléphone : </label> 
		<input type="tel" name="phoneNumber" required="required" pattern="(0|\\+33|0033)[1-9][0-9]{8}" > 
    </th>
    
    <th class="col-auto">
       <label>Code postal : </label>
		<input type="text" name="postalCode" required="required" maxlength="5" pattern="^(([0-8][0-9])|(9[0-5]))[0-9]{3}$"> 
    </th>
    
    <th class="col-auto">
       <label>Mot de passe : </label>
		<input type="password" name="password" required="required"> 
    </th>
   </tr>
  </table>
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
=======
			<div class="col-auto">
				<label>Pseudo</label> <input type="text" name="login"
					required="required" pattern="[a-zA-Z0-9]+" class="form-control" maxlength="30">
				<div class="invalid-feedback">Le pseudo est obligatoire et
					doit contenir uniquement des caractères alphanumériques</div>
			</div>

			<div class="col-auto">
				<label>Nom : </label> <input type="text" name="lastname"
					required="required" class="form-control"  maxlength="30">
				<div class="invalid-feedback">Le nom est obligatoire</div>
			</div>

			<div class="col-auto">
				<label>Prénom : </label> <input type="text" name="firstname"
					required="required" class="form-control"  maxlength="30">
				<div class="invalid-feedback">Le prénom est obligatoire</div>
			</div>

		</div>

	</div>

	<div class="container">

		<div class="row">

			<div class="col-auto">
				<label>Téléphone : </label> <input type="tel" name="phoneNumber"
					required="required" class="form-control" minlength="10" maxlength="10">
				<div class="invalid-feedback">Le numéro de téléphone est
					obligatoire</div>
			</div>

			<div class="col-auto">
				<label>Code postal : </label> <input type="text" name="postalCode"
					required="required" maxlength="5"
					pattern="^(([0-8][0-9])|(9[0-5]))[0-9]{3}$" class="form-control">
				<div class="invalid-feedback">Le code postal est obligatoire
					et doit être au format, ex : 56000</div>
			</div>

			<div class="col-auto">
				<label>Mot de passe : </label> <input type="password"
					name="password" required="required" class="form-control"  maxlength="30">
				<div class="invalid-feedback">Le mot de passe est obligatoire
				</div>
			</div>

		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-auto">
				<label>Email : </label> <input type="email" name="email"
					required="required" class="form-control" maxlength="50">
				<div class="invalid-feedback">L'email est obligatoire</div>
			</div>

			<div class="col-auto">
				<label>Rue : </label> <input type="text" name="street"
					required="required" class="form-control"  maxlength="150">
				<div class="invalid-feedback">Le nom de rue est obligatoire</div>
			</div>

			<div class="col-auto">
				<label>Ville : </label> <input type="text" name="city"
					required="required" class="form-control"  maxlength="100">
				<div class="invalid-feedback">Le nom de ville est obligatoire
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-auto">
				<label>Confirmation : </label> <input type="password"
					name="passwordConfirm" required="required" class="form-control" maxlength="30">
				<div class="invalid-feedback">Le confirmation de mot de passe
					est obligatoire</div>
			</div>

			<div class="col-auto">
				<button type="submit" class="btn btn-primary mb-2">Créer</button>
			</div>
			<div class="col-auto">
				<button class="btn  btn-secondary mb-2">
					<a
						href="<%=response.encodeURL(request.getContextPath() + "/index")%>">Annuler</a>
				</button>
			</div>
		</div>
	</div>
>>>>>>> refs/heads/master
</form>

<%@include file="../templates/footer.jsp"%>
