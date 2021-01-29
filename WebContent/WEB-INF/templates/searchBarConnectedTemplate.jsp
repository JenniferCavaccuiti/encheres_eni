<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<form method="POST" action="${pageContext.request.contextPath}/index"
      class="form-inline row justify-content-around">

    <div class="col-md-6 col-xs-12">
        <div class="col-12">
            <div class="col-md-9 col-xs-12">
                <input name="keyword" class="form-control" type="search"
                       placeholder="Le nom de l'article contient"
                       aria-label="Search">
            </div>
            <div class="col-auto my-2">
                <label class="mr-sm-4" for="category">Catégorie :</label>
                <select name="searchedCategory" class="custom-select mr-sm-4" id="category">
                    <option value="0">Toutes</option>
                    <c:forEach var="category" items="${categoriesList}">
                        <option value="${category.idCategory}">${category.wording}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="row margin-left-10">
            <div class="form-check col-md-5 col-xs-12">
                <input class="form-check-input" type="radio" onclick="radio();" name="type" id="buy" value="buy"
                       checked>
                <label class="form-check-label" for="buy">
                    Achats
                </label>
                <div id="buyChoice">
                    <div class="form-check form-check-inline col-12">
                        <input class="form-check-input" type="checkbox" id="openedBuy" name="filter"
                               value="openedBuy">
                        <label class="font-size form-check-label" for="openedBuy">enchères en cours</label>
                    </div>
                    <div class="form-check form-check-inline col-12">
                        <input class="form-check-input" type="checkbox" id="onGoingBuy" name="filter"
                               value="onGoingBuy">
                        <label class="font-size form-check-label" for="onGoingBuy">mes enchères en cours</label>
                    </div>
                    <div class="form-check form-check-inline col-12">
                        <input class="form-check-input" type="checkbox" id="wonBuy" name="filter" value="wonBuy">
                        <label class="font-size form-check-label" for="wonBuy">mes enchères remportées</label>
                    </div>
                </div>
            </div>


            <div class="form-check col-md-5 col-xs-12">
                <input class="form-check-input" type="radio" onclick="radio();" name="type" id="sell" value="sell">
                <label class="form-check-label" for="buy">
                    Mes ventes
                </label>
                <div id="sellChoice">
                    <div class="form-check form-check-inline col-12">
                        <input class="form-check-input" type="checkbox" id="nonOpenedSell" name="filter"
                               value="nonOpenedSell" disabled>
                        <label class="font-size form-check-label" for="nonOpenedSell">Ventes non commencées</label>
                    </div>
                    <div class="form-check form-check-inline col-12">
                        <input class="form-check-input" type="checkbox" id="onGoingSell" name="filter"
                               value="onGoingSell" disabled>
                        <label class="font-size form-check-label" for="onGoingSell">Ventes en cours</label>
                    </div>
                    <div class="form-check form-check-inline col-12">
                        <input class="form-check-input" type="checkbox" id="finishedSell" name="filter"
                               value="finishedSell" disabled>
                        <label class="font-size form-check-label" for="finishedSell">Ventes terminées</label>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-3 col-xs-12 d-flex justify-content-center align-items-center">
        <button class="col-xs-12" type="submit" onclick="filter();">Rechercher</button>
    </div>

</form>