$(document).ready(function () {
    $('input[type="checkbox"]').on('change', function () {
        $('input[type="checkbox"]').not(this).prop('checked', false);
    });
});

function pay() {
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        dataType: 'JSON',
        url: '/cart/pay',
        cache: false,
        timeout: 60000,
        success: function (data) {
            if (data.status === 200) {
                $('#PayModal').modal('show');
                $('#BtnOk').click(function () {
                    $('#PayModal').modal('hide');
                    window.location.href = window.location.origin + '/home';

                });
            } else {
                window.location.href = window.location.origin + '/login';
            }
        }

    });

}