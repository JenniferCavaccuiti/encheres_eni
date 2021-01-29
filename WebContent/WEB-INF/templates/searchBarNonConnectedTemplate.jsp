<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<form method="POST" action="${pageContext.request.contextPath}/index"
      class="form-inline row my-2 my-lg-0  justify-content-around">
    <div class="col-md-4 col-xs-12">
        <div class="col-auto my-1">
            <input name="keyword" class="form-control mr-sm-2" type="search"
                   placeholder="Le nom de l'article contient"
                   aria-label="Search">
        </div>

        <div class="col-md-auto col-xs-12 my-1">
            <label class="mr-sm-3" for="category">Catégorie :</label>
            <select name="searchedCategory" class="custom-select mr-sm-3" id="category">
                <option value="0">Toutes</option>
                <c:forEach var="category" items="${categoriesList}">
                    <option value="${category.idCategory}">${category.wording}</option>
                </c:forEach>
            </select>
        </div>
    </div>

    <button class="col-md-3 col-xs-12" type="submit">Rechercher</button>

</form>