function changePage(newPage) {
    window.location.pathname = newPage;
}

function radio() {
    if (document.getElementById('buy').checked) {
        document.getElementById('openedBuy').disabled = false;
        document.getElementById('onGoingBuy').disabled = false;
        document.getElementById('wonBuy').disabled = false;

        document.getElementById('openedSell').disabled = true;
        document.getElementById('onGoingSell').disabled = true;
        document.getElementById('wonSell').disabled = true;
    } else {
        document.getElementById('openedSell').disabled = false;
        document.getElementById('onGoingSell').disabled = false;
        document.getElementById('wonSell').disabled = false;

        document.getElementById('openedBuy').disabled = true;
        document.getElementById('onGoingBuy').disabled = true;
        document.getElementById('wonBuy').disabled = true;
    }
}

// function filter() {
//     // je récupère tous els boutons radio
//     var filter = document.getElementsByName('filter');
//
//     var filterType;
//
//     // je récupère le bouton radio coché
//     for (var i = 0; i < filter.length; i++) {
//         if (filter[i].checked) {
//             filterType = filter[i].value;
//         }
//     }
//
//     console.log(filterType);
