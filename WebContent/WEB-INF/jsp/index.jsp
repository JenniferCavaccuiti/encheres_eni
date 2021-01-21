<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://example.com/functions" prefix="f" %>
<%@include file="../templates/startFile.jsp" %>
<div class="col-11 center-block">
    <h1>bienvenue</h1>

    <h5>Filtre : </h5>

    <%--    barre de recherche--%>
    <form method="POST" action="${pageContext.request.contextPath}/index"
          class="form-inline row my-2 my-lg-0  justify-content-evenly">

        <div class="col-5">
            <div class="col">
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
                <div class="form-check col-6">
                    <input class="form-check-input" type="radio" onclick="radio();" name="type" id="buy" value="buy" checked>
                    <label class="form-check-label" for="buy">
                        Achats
                    </label>
                    <div class="">
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="openedBuy" name="filter" value="openedBuy">
                            <label class="form-check-label" for="openedBuy">enchères en cours</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="onGoingBuy" name="filter" value="onGoingBuy">
                            <label class="form-check-label" for="onGoingBuy">mes enchères en cours</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="wonBuy" name="filter" value="wonBuy">
                            <label class="form-check-label" for="wonBuy">mes enchères remportées</label>
                        </div>
                    </div>
                </div>


                <div class="form-check col-6">
                    <input class="form-check-input" type="radio" name="type" id="sell" value="sell" onclick="radio();">
                    <label class="form-check-label" for="buy">
                        Mes ventes
                    </label>
                    <div class="">
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="openedSell" value="option1" disabled>
                            <label class="form-check-label" for="openedSell">enchères en cours</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="onGoingSell" value="option1" disabled>
                            <label class="form-check-label" for="onGoingSell">mes enchères en cours</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="wonSell" value="option1" disabled>
                            <label class="form-check-label" for="wonSell">mes enchères remportées</label>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <button class="col-5 btn btn-outline-success btn-lg my-2 my-sm-0 block" type="submit">Rechercher</button>

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

<script type="text/javascript">
    function radio(){
        if(document.getElementById('buy').checked) {
            document.getElementById('openedBuy').disabled = false;
            document.getElementById('onGoingBuy').disabled = false;
            document.getElementById('wonBuy').disabled = false;

            document.getElementById('openedSell').disabled = true;
            document.getElementById('onGoingSell').disabled = true;
            document.getElementById('wonSell').disabled = true;
        }
        else{
            document.getElementById('openedSell').disabled = false;
            document.getElementById('onGoingSell').disabled = false;
            document.getElementById('wonSell').disabled = false;

            document.getElementById('openedBuy').disabled = true;
            document.getElementById('onGoingBuy').disabled = true;
            document.getElementById('wonBuy').disabled = true;
        }
    }
</script>

<%@include file="../templates/footer.jsp" %>