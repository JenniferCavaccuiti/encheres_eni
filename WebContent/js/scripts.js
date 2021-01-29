function changePage(parameter, newPage) {
    newPage = newPage + '?itemId=' + parameter;
    window.location.href = newPage;
}

function callServlet(newPage) {
    window.location.href = newPage;
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

(function () {
    'use strict'

    // Fetch all the forms we want to apply custom Bootstrap validation styles to
    var forms = document.querySelectorAll('.needs-validation')

    // Loop over them and prevent submission
    Array.prototype.slice.call(forms)
        .forEach(function (form) {
            form.addEventListener('submit', function (event) {
                if (!form.checkValidity()) {
                    event.preventDefault()
                    event.stopPropagation()
                }

                form.classList.add('was-validated')
            }, false)
        })
})()

