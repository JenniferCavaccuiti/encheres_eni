<%--@elvariable id="message" type=""--%>
<%--@elvariable id="item" type=""--%>
<%--@elvariable id="categoriesList" type=""--%>

<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../templates/startFile.jsp" %>

<c:if test="${empty item}">
    <h1 class="title">Nouvelle vente</h1>
</c:if>
<c:if test="${not empty item}">
    <h1 class="title">Modifier la vente</h1>
</c:if>


<c:if test="${not empty message}">
    <p  class="alert alert-warning text-center">${message}</p>
</c:if>

<div class="row justify-content-center">
    <div class="col-5">
        <form action="${pageContext.request.contextPath}/nouvelle-vente" method="post" novalidate
              class="needs-validation mb-3">
            <input type="hidden" name="idItem" value="${item.idItem}">
            <div class="form-group row">
                <div class="col-md-5 col-xs-12">
                    <label for="itemName">Article : </label>
                </div>
                <div class="col-md-7 col-xs-12">
                    <input type="text" class="form-control" value="${item.itemName}" name="itemName" id="itemName"
                           minlength="4" maxlength="30" required>
                    <div class="invalid-feedback">
                        Le nom de l'article est obligatoire
                    </div>
                </div>
            </div>
            <br>
            <div class="form-group row">
                <div class="col-md-5 col-xs-12">
                    <label for="description" class="col-md-5 col-xs-12">Description : </label>
                </div>
                <div class="col-md-7 col-xs-12">
                <textarea class="form-control" id="description" rows="3" name="description" minlength="10"
                          maxlength="300"
                          required>${item.description}</textarea>
                    <div class="invalid-feedback">
                        Le description est obligatoire
                    </div>
                </div>
            </div>
            <br>
            <div class="form-group row">
                <div class="col-md-5 col-xs-12">
                    <label for="category">Catégorie :</label>
                </div>
                <div class="col-md-7 col-xs-12">
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
            <br>
            <div class="form-group row">
                <div class="col-md-5 col-xs-12">
                    <label for="initialPrice">Mise à prix :</label>
                </div>

                <div class="col-md-7 col-xs-12">
                    <c:if test="${empty item.initialPrice}">
                        <input type="number" class="form-control" id="initialPrice" value="20"
                               name="initialPrice">
                    </c:if>
                    <c:if test="${ not empty item.initialPrice}">
                        <input type="number" class="form-control" id="initialPrice" value="${item.initialPrice}"
                               name="initialPrice">
                    </c:if>
                    <div class="invalid-feedback">
                        Le prix est obligatoire
                    </div>
                </div>
            </div>
            <br>

            <c:if test="${empty item.bidsStartDate}">
                <div class="form-group row">
                    <div class="col-md-5 col-xs-12">
                        <label for="bidsStartDate1">Début de l'enchère :</label>
                    </div>

                    <div class="col-md-4 col-xs-12">
                        <input type="date" class="form-control" id="bidsStartDate1" name="bidsStartDate"
                               min="${f:formatLocalDateTime(f:getDate(), 'yyyy-MM-dd')}"
                               value="${f:formatLocalDateTime(f:getDate(), 'yyyy-MM-dd')}">
                    </div>
                    <div class="invalid-feedback">
                        La date de début est obligatoire et ne peut pas être inférieure à la date du jour
                    </div>
                    <div class="col-md-3 col-xs-12">
                        <input type="time" class="form-control" id="bidsStartTime1" name="bidsStartTime"
                               value="${f:formatLocalDateTime(f:getDate(), 'HH:mm')}">
                    </div>
                </div>
                <br>
                <div class="form-group row">
                    <div class="col-md-5 col-xs-12">
                        <label for="bidsEndDate">Fin de l'enchère :</label>
                    </div>

                    <div class="col-4">
                        <input type="date" class="col-md-4 col-xs-12" id="bidsEndDate" name="bidsEndDate"
                               value="${f:formatLocalDateTime(f:addDaysToDate(1, f:getDate()), 'yyyy-MM-dd')}"
                               min="${f:formatLocalDateTime(f:addDaysToDate(1, f:getDate()), 'yyyy-MM-dd')}">
                        <div class="invalid-feedback">
                            Le date de fin est obligatoire
                        </div>
                    </div>
                    <div class="col-md-3 col-xs-12">
                        <input type="time" class="form-control col-3" id="bidsEndTime" name="bidsEndTime"
                               value="${f:formatLocalDateTime(f:getDate(), 'HH:mm')}">
                    </div>
                </div>
                <br>
            </c:if>
            <c:if test="${not empty item.bidsStartDate}">
                <div class="form-group row">
                    <div class="col-md-5 col-xs-12">
                        <label for="bidsStartDate2">Début de l'enchère :</label>
                    </div>

                    <div class="col-md-4 col-xs-12">
                        <input type="date" class="form-control" id="bidsStartDate2" name="bidsStartDate"
                               min="${f:formatLocalDateTime(f:getDate(), 'yyyy-MM-dd')}"
                               value="${f:formatLocalDateTime(item.bidsStartDate, 'yyyy-MM-dd')}">
                    </div>
                    <div class="invalid-feedback">
                        La date de début est obligatoire et ne peut pas être inférieure à la date du jour
                    </div>
                    <div class="col-md-3 col-xs-12">
                        <input type="time" class="form-control" id="bidsStartTime2" name="bidsStartTime"
                               value="${f:formatLocalDateTime(item.bidsStartDate, 'HH:mm')}">
                    </div>
                </div>
                <br>
                <div class="form-group row">
                    <div class="col-md-5 col-xs-12">
                        <label for="bidsEndDate2">Fin de l'enchère :</label>
                    </div>

                    <div class="col-md-4 col-xs-12">
                        <input type="date" class="form-control col-3" id="bidsEndDate2" name="bidsEndDate"
                               value="${f:formatLocalDateTime(f:addDaysToDate(1, item.bidsEndDate), 'yyyy-MM-dd')}"
                               min="${f:formatLocalDateTime(f:addDaysToDate(1, f:getDate()), 'yyyy-MM-dd')}">
                        <div class="invalid-feedback">
                            Le date de fin est obligatoire
                        </div>
                    </div>
                    <div class="col-md-3 col-xs-12">
                        <input type="time" class="form-control col-3" id="bidsEndTime2" name="bidsEndTime"
                               value="${f:formatLocalDateTime(item.bidsEndDate, 'HH:mm')}">
                    </div>
                </div>
                <br>
            </c:if>

            <p>Retrait</p>
            <br>
            <div class="form-group row">
                <div class="col-md-5 col-xs-12">
                    <label for="street" class="col-md-5 col-xs-12">Rue : </label>
                </div>
                <div class="col-md-7 col-xs-12">
                    <input type="text" class="form-control col-8" name="street" id="street"
                           value="${sessionScope.user.street}" minlength="10" maxlength="100" required>
                    <div class="invalid-feedback">
                        Le date de fin est obligatoire
                    </div>
                </div>
            </div>
            <br>
            <div class="form-group row">
                <div class="col-md-5 col-xs-12">
                    <label for="postalCode" class="col-md-5 col-xs-12">Code postal : </label>
                </div>
                <div class="col-md-7 col-xs-12">
                    <input type="text" class="form-control col-8" name="postalCode" id="postalCode"
                           value="${sessionScope.user.postalCode}" minlength="5" maxlength="5" required>
                    <div class="invalid-feedback">
                        Le date de fin est obligatoire
                    </div>
                </div>
            </div>
            <br>
            <div class="form-group row">
                <div class="col-md-5 col-xs-12">
                    <label for="city" class="col-md-5 col-xs-12">Ville : </label>
                </div>
                <div class="col-md-7 col-xs-12">
                    <input type="text" class="form-control col-8" name="city" id="city"
                           value="${sessionScope.user.city}"
                           minlength="4" maxlength="100" required>
                    <div class="invalid-feedback">
                        Le date de fin est obligatoire
                    </div>
                </div>
            </div>
            <br>
            <c:if test="${not empty item}">
                <button class="normal-button" type="submit" name="submitType" value="update">Valider</button>
                <a class="btn normal-button" href="index" type="button" role="button">Annuler</a>
                <button class="btn normal-button" type="submit" name="submitType" value="delete">Annuler la vente</button>
            </c:if>
            <c:if test="${empty item}">
                <button class="normal-button" type="submit" name="submitType" value="create">Valider</button>
                <button class="normal-button" onclick="callServlet('${pageContext.request.contextPath}/index')" type="button" role="button">Annuler</button>
            </c:if>
        </form>
    </div>
</div>

<%@include file="../templates/footer.jsp" %>
