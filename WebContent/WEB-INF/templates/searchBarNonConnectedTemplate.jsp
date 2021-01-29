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
            <div class="col-auto my-3">
                <label class="mr-sm-4" for="category">Catégorie :</label>
                <select name="searchedCategory" class="custom-select mr-sm-4  border-radius" id="category">
                    <option value="0">Toutes</option>
                    <c:forEach var="category" items="${categoriesList}">
                        <option value="${category.idCategory}">${category.wording}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
    </div>
    <div class="col-md-3 col-xs-12 d-flex justify-content-center align-items-center">
    <button class="col-xs-12 uggly-button"  type="submit">Rechercher</button>
    </div>
</form>