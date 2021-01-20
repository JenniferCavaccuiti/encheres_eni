
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
                    Featured
                </div>
                <div class="card-body">
                    <h5 class="card-title">${item.itemName}</h5>
                    <p class="card-text">Prix : ${item.currentPrice}</p>
                    <p>Fin de l'enchère : ${f:formatLocalDateTime(item.bidsEndDate, 'EEEE dd MMMM yyyy HH:mm')}</p>
                    <p class="card-text">Vendeur : ${item.idSeller}</p>
                    <a href="#" class="btn btn-primary">Go somewhere</a>
                </div>
            </div>
        </c:forEach>
    </div>
</div>



<%@include file="../templates/footer.jsp" %>