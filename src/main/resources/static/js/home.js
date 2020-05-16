$(document).ready(function () {

});

function Cart(id) {
    $.ajax({
        type: 'POST',
        // contentType: 'application/json',
        // dataType: 'JSON',
        url: 'cart/' + id,
        cache: false,
        timeout: 60000,
        success: function (data) {
            if (data.status === 200) {
                $('#addCartModal').modal('show');

            } else {
                window.location.href = window.location.origin + '/login';
            }
        }

    });
}

