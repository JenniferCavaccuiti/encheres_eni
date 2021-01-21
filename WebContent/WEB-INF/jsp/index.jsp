<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://example.com/functions" prefix="f" %>
<%@include file="../templates/startFile.jsp" %>
<div class="col-11 center-block">
    <h1>bienvenue</h1>

    <%--    barre de recherche--%>
    <form method="get" action="${pageContext.request.contextPath}/index"
          class="form-inline row my-2 my-lg-0  justify-content-evenly">
        <div class="col-4">
            <div class="col-auto my-1">
                <input name="searchedWord" class="form-control mr-sm-2" type="search"
                       placeholder="Le nom de l'article contient"
                       aria-label="Search">
            </div>


            <div class="col-auto my-1">
                <label class="mr-sm-2" for="category">Catégorie :</label>
                <select name="searchedCategory" class="custom-select mr-sm-2" id="category">
                    <option value="0">Toutes</option>
                    <c:forEach var="category" items="${categoriesList}">
                        <option value="${category.idCategory}">${category.wording}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
<div class="row">
    <div class="form-check col">
        <%--            <div class="col">--%>

        <input class="form-check-input" type="radio" name="type" id="buy" value="buy" checked>
        <label class="form-check-label" for="buy">
            Achats
        </label>

        <div class="form-check form-check-inline">
            <input class="form-check-input" type="checkbox" id="openedBids" value="option1">
            <label class="form-check-label" for="openedBids">enchères en cours</label>
        </div>
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="checkbox" id="onGoingBids" value="option1">
            <label class="form-check-label" for="onGoingBids">mes enchères en cours</label>
        </div>
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="checkbox" id="bidsWon" value="option1">
            <label class="form-check-label" for="bidsWon">mes enchères remportées</label>
        </div>
        <%--            </div>--%>

    </div>

    <div class="form-check col">
        <input class="form-check-input" type="radio" name="type" id="sell" value="sell" checked>
        <label class="form-check-label" for="buy">
            Mes ventes
        </label>
    </div>
</div>
        <%--        Partie de la recherche qui n'apparait que lorsque connecté--%>


        <button class="col-3 btn btn-outline-success btn-lg my-2 my-sm-0 block" type="submit">Rechercher</button>

    </form>

    <%-- Card pour les enchères--%>
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