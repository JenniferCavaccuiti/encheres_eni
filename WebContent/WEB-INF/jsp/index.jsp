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
                    <p class="card-text">Vendeur : <a href="ViewProfile?login=${f:getUserLogin(item.idSeller, usersList)}">${f:getUserLogin(item.idSeller, usersList)}</a></p>
                    <c:if test="${not empty sessionScope}">
                        <a href="#" class="stretched-link">Afficher le détail</a>
                    </c:if>
                </div>
            </div>
        </c:forEach>
    </div>
</div>


<%@include file="../templates/footer.jsp" %>