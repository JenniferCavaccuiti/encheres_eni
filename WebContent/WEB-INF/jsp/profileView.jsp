<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../templates/startFile.jsp"%>

<div class="container" id="viewProfile">


       <div class="row">
		<div class="col heading">
			<h3 class="text-center">Détails du profil</h3>
		</div>
		</div>

        <div class="row align-items-center ">

            <div class="col">

                <div class="col-auto">
                    <label>Pseudo</label> <input type="text" value="${userProfile.getLogin()}" class="form-control" disabled>
                </div>

                <div class="col-auto">
                    <label>Prénom : </label> <input type="text" value="${userProfile.getFirstname()}" class="form-control" disabled>
                </div>

                <div class="col-auto">
                    <label>Téléphone : </label> <input type="tel" value="${userProfile.getPhoneNumber()}" class="form-control" disabled>

                </div>

                <div class="col-auto">
                    <label>Code postal : </label> <input type="text" name="postalCode" value="${userProfile.getPostalCode()}" class="form-control" disabled>

                </div>
            </div> 

            <div class="col">

                <div class="col-auto">
                    <label>Nom : </label> <input type="text" name="lastname" value="${userProfile.getLastname()}" class="form-control" disabled>
                </div>

                <div class="col-auto">
                    <label>Email : </label> <input type="email" name="email" value="${userProfile.getEmail()}" class="form-control" disabled>
                </div>

                <div class="col-auto">
                    <label>Rue : </label> <input type="text" name="street" value="${userProfile.getStreet()}" class="form-control" disabled>
                </div>

                <div class="col-auto">
                    <label>Ville : </label> <input type="text" value="${userProfile.getCity()}" class="form-control" disabled>
                </div>
            </div>
        </div>


<c:if test="${userProfile.getLogin() == sessionScope.user.getLogin()}">
	<div class="row align-items-center updateForm">
            <div class="col">  
                <div class="col-auto">
                <label>Crédit</label> <input type="text" value="${user.getCredits()}" class="form-control" disabled>
            </div>
            </div>
            <div class="col"></div> 
        </div>
</c:if>	

<c:if test="${userProfile.getLogin() != sessionScope.user.getLogin()}">
	<div class="row align-items-center updateForm">
            <div class="col">  
                <div class="col-auto">
            </div>
            </div>
            <div class="col"></div> 
        </div>
</c:if>	


<c:choose>
	<c:when test="${userProfile.getLogin() == sessionScope.user.getLogin()}">
		
		 <div class="row">

            <div class="col-md-3 offset-md-3 text-center updateProfile">
            <button class="buttonProfile">
            	<a class="lienProfile" href="modifier-profil">Modifier mon profil</a>
            	 </button>
            </div>
            
            <div class="col-md-3 text-center updateProfile">
                <button class="buttonProfile">
                    	<a class="lienProfile" href="index">Retour</a>
                </button>
            </div>
          
          </div>
		
	</c:when>
	
	<c:otherwise>
	
		 <div class="row">

            <div class="text-center updateProfile ">
                <button class="buttonProfile">
                    	<a class="lienProfile" href="index">Retour</a>
                </button>
            </div>
          
          </div>
	
	</c:otherwise>


</c:choose>


       

       



</div>





<%@include file="../templates/footer.jsp"%>