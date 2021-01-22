<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://example.com/functions" prefix="f" %>
<%@include file="../templates/startFile.jsp" %>
<div class="col-11 center-block">
    <h1>bienvenue</h1>

    <h5>Filtre : </h5>

    <%--    barre de recherche--%>
    <c:choose>
    <c:when test="${empty sessionScope}">
        <%@include file="../templates/searchBarNonConnectedTemplate.jsp" %>
    </c:when>
    <c:when test="${not empty sessionScope}">
        <%@include file="../templates/searchBarConnectedTemplate.jsp" %>
    </c:when>
    </c:choose>


    <div class="row justify-content-center">
        <c:forEach var="item" items="${itemsList}">
        <c:if test="${not empty sessionScope}">--%>
        <div class="card col-4" id="item-${item.idItem}"
             onClick="changePage('${pageContext.request.contextPath}/nouveau-profil');">
            </c:if>
            <c:if test="${empty sessionScope}">--%>
            <div class="card col-4" id="item-${item.idItem}">
                </c:if>
                <div class="card-header">
                        ${item.itemName}
                </div>
                <div class="card-body">
                    <h5 class="card-title">Prix : ${item.currentPrice}</h5>
                    <p>Fin de l'enchère : ${f:formatLocalDateTime(item.bidsEndDate, 'EEEE dd MMMM yyyy HH:mm')}</p>
                    <p class="card-text">Vendeur : <a
                            href="profil-utilisateur?login=${f:getUserLogin(item.idSeller, usersList)}">${f:getUserLogin(item.idSeller, usersList)}</a>
                    </p>
                </div>
            </div>
            </c:forEach>
        </div>
    </div>

    <script type="text/javascript">

        function changePage(newPage) {


            window.location.pathname = newPage;
        }

        function radio() {
            if (document.getElementById('buy').checked) {
                document.getElementById('openedBuy').disabled = false;
                document.getElementById('onGoingBuy').disabled = false;
                document.getElementById('wonBuy').disabled = false;

                document.getElementById('openedSell').disabled = true;
                document.getElementById('onGoingSell').disabled = true;
                document.getElementById('wonSell').disabled = true;
            } else {
                document.getElementById('openedSell').disabled = false;
                document.getElementById('onGoingSell').disabled = false;
                document.getElementById('wonSell').disabled = false;

                document.getElementById('openedBuy').disabled = true;
                document.getElementById('onGoingBuy').disabled = true;
                document.getElementById('wonBuy').disabled = true;
            }
        }

        function filter() {
            // je récupère tous els boutons radio
            var filter = document.getElementsByName('filter');

            var filterType;

            // je récupère le bouton radio coché
            for (var i = 0; i < filter.length; i++) {
                if (filter[i].checked) {
                    filterType = filter[i].value;
                }
            }
            var itemsList = document.getElementById('item-${item.idItem}');


        }
    </script>

<%@include file="../templates/footer.jsp" %>