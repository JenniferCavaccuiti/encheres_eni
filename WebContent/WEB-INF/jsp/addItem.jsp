<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../templates/startFile.jsp" %>
<h1>Nouvelle vente</h1>

<h5 class="message">${message}</h5>

<div class="col-5 justify-content-center">
    <form action="${pageContext.request.contextPath}/nouvelle-vente" method="post" novalidate class="needs-validation">
        <div class="form-group row">
            <div class="col-4">
                <label for="itemName" class="col-4">Article : </label>
            </div>
            <div class="col-8">
                <input type="text" class="form-control" value="${item.itemName}" name="itemName" id="itemName" required>
                <div class="invalid-feedback">
                    Le nom de l'article est obligatoire
                </div>
            </div>
        </div>
        </br>
        <div class="form-group row">
            <div class="col-4">
                <label for="description" class="col-4">Description : </label>
            </div>
            <div class="col-8">
                <textarea class="form-control" id="description" rows="3" name="description"
                          required>${item.description}</textarea>
                <div class="invalid-feedback">
                    Le description est obligatoire
                </div>
            </div>
        </div>
        </br>
        <div class="form-group row">
            <div class="col-4">
                <label for="category" class="col-4">Catégories</label>
            </div>
            <div class="col-8">
                <select class="form-control" id="category" name="category" required>
                    <option value="${item.idCategory}">${item.categoryName}</option>
                    // Pas de récupération de la catégorie ?
                    <c:forEach var="category" items="${categoriesList}">
                        <option value="${category.idCategory}">${category.wording}</option>
                    </c:forEach>
                </select>
                <div class="invalid-feedback">
                    La catégorie est obligatoire
                </div>
            </div>
        </div>
        </br>
        <div class="form-group row">
            <div class="col-4">
                <label for="initialPrice" class="col-4">Mise à prix : </label>
            </div>
            <div class="col-8">
                <input type="number" class="form-control" id="initialPrice" value="${item.initialPrice}"
                       name="initialPrice">
                <div class="invalid-feedback">
                    Le prix est obligatoire
                </div>
            </div>
        </div>
        </br>


        <c:if test="${empty item.bidsStartDate}">
            <div class="form-group row">
                <label for="bidsStartDate1" class="col-4">Début de l'enchère</label>
                <div class="col-4">
                    <input type="date" class="form-control" id="bidsStartDate1" name="bidsStartDate"
                           min="${f:formatLocalDateTime(f:getDate(), 'yyyy-MM-dd')}"
                           value="${f:formatLocalDateTime(f:getDate(), 'yyyy-MM-dd')}">
                </div>
                <div class="invalid-feedback">
                    La date de début est obligatoire et ne peut pas être inférieure à la date du jour
                </div>
                <div class="col-4">
                    <input type="time" class="form-control" id="bidsStartTime1" name="bidsStartTime"
                           value="${f:formatLocalDateTime(f:getDate(), 'HH:mm')}">
                </div>
            </div>
            </br>
            <div class="form-group row">
                <label for="bidsEndDate" class="col-4">Fin de l'enchère</label>
                <div class="col-4">
                    <input type="date" class="form-control col-3" id="bidsEndDate" name="bidsEndDate"
                           value="${f:formatLocalDateTime(f:addDaysToDate(1, f:getDate()), 'yyyy-MM-dd')}"
                           min="${f:formatLocalDateTime(f:addDaysToDate(1, f:getDate()), 'yyyy-MM-dd')}">
                    <div class="invalid-feedback">
                        Le date de fin est obligatoire
                    </div>
                </div>
                <div class="col-4">
                    <input type="time" class="form-control col-3" id="bidsEndTime" name="bidsEndTime"
                           value="${f:formatLocalDateTime(f:getDate(), 'HH:mm')}">
                </div>
            </div>
            </br>
        </c:if>
        <c:if test="${not empty item.bidsStartDate}">
            <div class="form-group row">
                <label for="bidsStartDate2" class="col-4">Début de l'enchère</label>
                <div class="col-4">
                    <input type="date" class="form-control" id="bidsStartDate2" name="bidsStartDate"
                           min="${f:formatLocalDateTime(f:getDate(), 'yyyy-MM-dd')}"
                           value="${f:formatLocalDateTime(item.bidsStartDate, 'yyyy-MM-dd')}">
                </div>
                <div class="invalid-feedback">
                    La date de début est obligatoire et ne peut pas être inférieure à la date du jour
                </div>
                <div class="col-4">
                    <input type="time" class="form-control" id="bidsStartTime2" name="bidsStartTime"
                           value="${f:formatLocalDateTime(item.bidsStartDate, 'HH:mm')}">
                </div>
            </div>
            </br>
            <div class="form-group row">
                <label for="bidsEndDate2" class="col-4">Fin de l'enchère</label>
                <div class="col-4">
                    <input type="date" class="form-control col-3" id="bidsEndDate2" name="bidsEndDate"
                           value="${f:formatLocalDateTime(f:addDaysToDate(1, item.bidsEndDate), 'yyyy-MM-dd')}"
                           min="${f:formatLocalDateTime(f:addDaysToDate(1, f:getDate()), 'yyyy-MM-dd')}">
                    <div class="invalid-feedback">
                        Le date de fin est obligatoire
                    </div>
                </div>
                <div class="col-4">
                    <input type="time" class="form-control col-3" id="bidsEndTime2" name="bidsEndTime"
                           value="${f:formatLocalDateTime(item.bidsEndDate, 'HH:mm')}">
                </div>
            </div>
            </br>
        </c:if>

        <p>Retrait</p>
        </br>
        <div class="form-group row">
            <div class="col-4">
                <label for="street">Rue : </label>
            </div>
            <div class="col-8">
                <input type="text" class="form-control col-8" name="street" id="street"
                       value="${sessionScope.user.street}" required>
                <div class="invalid-feedback">
                    Le date de fin est obligatoire
                </div>
            </div>
        </div>
        </br>
        <div class="form-group row">
            <div class="col-4">
                <label for="postalCode" class="col-4">Code postal : </label>
            </div>
            <div class="col-8">
                <input type="text" class="form-control col-8" name="postalCode" id="postalCode"
                       value="${sessionScope.user.postalCode}" required>
                <div class="invalid-feedback">
                    Le date de fin est obligatoire
                </div>
            </div>
        </div>
        </br>
        <div class="form-group row">
            <div class="col-4">
                <label for="city" class="col-4">Ville : </label>
            </div>
            <div class="col-8">
                <input type="text" class="form-control col-8" name="city" id="city" value="${sessionScope.user.city}"
                       required>
                <div class="invalid-feedback">
                    Le date de fin est obligatoire
                </div>
            </div>
        </div>
        </br>
        <a class="btn btn-default" href="index" role="button">Modifier</a>
        <button type="submit">Enregistrer</button>
    </form>
</div>


<%@include file="../templates/footer.jsp" %>
