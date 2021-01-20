<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://example.com/functions" prefix="f" %>
<%@include file="../templates/startFile.jsp" %>
<div class="cole-10">
    <h1>bienvenue</h1>
    <div class="row col-10 center-block">
        <c:forEach var="item" items="${itemsList}">


                <div class="card col-4">
                    <div class="card-header">
                            ${item.itemName}
                    </div>
                    <div class="card-body">
                        <h5 class="card-title">Prix : ${item.currentPrice}</h5>
                        <p>Fin de l'enchère : ${f:formatLocalDateTime(item.bidsEndDate, 'EEEE dd MMMM yyyy HH:mm')}</p>
                        <p class="card-text">Vendeur : ${f:getUserLogin(item.idSeller, usersList)}</p>
                        <c:if test="${not empty sessionScope}">
                            <a href="#" class="stretched-link">Afficher le détail</a>
                        </c:if>
                    </div>
                </div>


        </c:forEach>
    </div>
</div>


<%@include file="../templates/footer.jsp" %>