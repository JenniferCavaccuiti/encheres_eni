<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://example.com/functions" prefix="f" %>
<%@include file="../templates/startFile.jsp" %>
<div class="col-11 center-block">
    <h1>bienvenue</h1>
    <form method="get" action="${pageContext.request.contextPath}/index" class="form-inline col-3 my-2 my-lg-0">
        <input name="searchedWord" class="form-control mr-sm-2" type="search" placeholder="Le nom de l'article contient" aria-label="Search">
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>


    <div class="row justify-content-center">
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