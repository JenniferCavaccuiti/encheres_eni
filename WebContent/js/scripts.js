function changePage(newPage) {
    window.location.pathname = newPage;
}

function radio() {
    if (document.getElementById('buy').checked) {
        document.getElementById('openedBuy').disabled = false;
        document.getElementById('onGoingBuy').disabled = false;
        document.getElementById('wonBuy').disabled = false;

        document.getElementById('nonOpenedSell').disabled = true;
        document.getElementById('onGoingSell').disabled = true;
        document.getElementById('finishedSell').disabled = true;
    } else if (document.getElementById('sell').checked) {
        document.getElementById('nonOpenedSell').disabled = false;
        document.getElementById('onGoingSell').disabled = false;
        document.getElementById('finishedSell').disabled = false;

        document.getElementById('openedBuy').disabled = true;
        document.getElementById('onGoingBuy').disabled = true;
        document.getElementById('wonBuy').disabled = true;
    }
}