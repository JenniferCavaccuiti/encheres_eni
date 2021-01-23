<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<form method="POST" action="${pageContext.request.contextPath}/index"
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