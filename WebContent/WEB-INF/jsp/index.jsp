<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://example.com/functions" prefix="f" %>
<%@include file="../templates/startFile.jsp" %>
<div class="col-11 center-block">
    <h1 class="title">Bienvenue</h1>

    <c:if test="${not empty message}">
    <p class="alert alert-warning text-center">${message}</p>
    </c:if>


    <h5>Filtres :</h5>

    <%--    barre de recherche --%>
    <c:choose>
    <c:when test="${empty sessionScope}">
        <%@include file="../templates/searchBarNonConnectedTemplate.jsp" %>
    </c:when>
    <c:when test="${not empty sessionScope}">
        <%@include file="../templates/searchBarConnectedTemplate.jsp" %>
    </c:when>
    </c:choose>

    <%--    Affichage items--%>
    <div class="row justify-content-evenly">
        <c:forEach var="item" items="${itemsList}">
        <c:if test="${not empty sessionScope}">
        <div class="card col-xs-12 col-md-5" id="item-${item.idItem}"
             onClick="changePage('${item.idItem}', '${pageContext.request.contextPath}/details-vente');">
            </c:if>
            <c:if test="${empty sessionScope}">
            <div class="card  col-xs-12 col-md-5" id="item-${item.idItem}">
                </c:if>
                <div class="card-header header-color">
                        ${item.itemName}
                </div>
                <div class="card-body">
                    <h5 class="card-title">Prix : ${item.currentPrice}</h5>
                    <p>Fin de l'enchère : ${f:formatLocalDateTime(item.bidsEndDate, 'EEEE dd MMMM yyyy HH:mm')}</p>
                    <p class="card-text">Vendeur :
                        <a class="dark-blue-color"
                           href="profil-utilisateur?login=${item.sellerName}">${item.sellerName}</a>
                    </p>
                </div>
            </div>
            </c:forEach>
        </div>
    </div>

<%@include file="../templates/footer.jsp" %>