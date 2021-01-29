<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<form method="POST" action="${pageContext.request.contextPath}/index"
      class="form-inline justify-content-around">
    <div class="col-md-4 col-xs-12">
        <div class="col-12">
            <input name="keyword" class="form-control" type="search"
                   placeholder="Le nom de l'article contient"
                   aria-label="Search">
        </div>

        <div class="col-md-4 col-xs-12">
            <label for="category">Catégorie :</label>
            <select name="searchedCategory" class="custom-select" id="category">
                <option value="0">Toutes</option>
                <c:forEach var="category" items="${categoriesList}">
                    <option value="${category.idCategory}">${category.wording}</option>
                </c:forEach>
            </select>
        </div>
    </div>

    <button class="col-md-3 col-xs-12" type="submit">Rechercher</button>

</form>