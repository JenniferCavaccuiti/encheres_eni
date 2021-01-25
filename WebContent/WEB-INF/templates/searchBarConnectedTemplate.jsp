<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<form method="POST" action="${pageContext.request.contextPath}/index"
      class="form-inline row my-2 my-lg-0  justify-content-evenly">

    <div class="col-5">
        <div class="col">
            <div class="col-auto my-1">
                <input name="keyword" class="form-control mr-sm-2" type="search"
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
                <div id="buyChoice">
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="checkbox" id="openedBuy" name="filter"
                               value="openedBuy">
                        <label class="form-check-label" for="openedBuy">enchères en cours</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="checkbox" id="onGoingBuy" name="filter"
                               value="onGoingBuy">
                        <label class="form-check-label" for="onGoingBuy">mes enchères en cours</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="checkbox" id="wonBuy" name="filter" value="wonBuy">
                        <label class="form-check-label" for="wonBuy">mes enchères remportées</label>
                    </div>
                </div>
            </div>


            <div class="form-check col-6">
                <input class="form-check-input" type="radio" onclick="radio();" name="type" id="sell" value="sell">
                <label class="form-check-label" for="buy">
                    Mes ventes
                </label>
                <div id="sellChoice">
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="checkbox" id="nonOpenedSell" name="filter"
                               value="nonOpenedSell" disabled>
                        <label class="form-check-label" for="nonOpenedSell">Ventes non commencées</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="checkbox" id="onGoingSell" name="filter"
                               value="onGoingSell" disabled>
                        <label class="form-check-label" for="onGoingSell">Ventes en cours</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="checkbox" id="finishedSell" name="filter"
                               value="finishedSell" disabled>
                        <label class="form-check-label" for="finishedSell">Ventes terminées</label>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <button class="col-5 btn btn-outline-success btn-lg my-2 my-sm-0 block" type="submit" onclick="filter();">Rechercher</button>
</form>