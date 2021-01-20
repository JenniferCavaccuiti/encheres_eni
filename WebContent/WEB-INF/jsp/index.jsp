<%@include file="../templates/startFile.jsp" %>
<div class="container-fluid">
    <h1>bienvenue</h1>
    <div class="row col-10 justify-content-space-around">
        <c:forEach var="item" items="${itemsList}">
            <div class="card col-3">
                <div class="card-header">
                    Featured
                </div>
                <div class="card-body">
                    <h5 class="card-title">${item.itemName}</h5>
                    <p class="card-text">Prix : ${item.currentPrice}</p>
                    <p class="card-text">Fin de l'enchère : ${item.bidsEndDate}</p>
                    <p class="card-text">Vendeur : ${item.idSeller}</p>
                    <a href="#" class="btn btn-primary">Go somewhere</a>
                </div>
            </div>
        </c:forEach>
    </div>
</div>



<%@include file="../templates/footer.jsp" %>