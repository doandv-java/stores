$(document).ready(function () {
    $('#quantity').onchange(function () {
        let quantity = $('#quantity').val();
        if (quantity <= 0) {
            $('#quantity').val(1);
        }
    });
});

function Cart(id) {
    $.ajax({
        type: 'POST',
        url: '/cart/' + id,
        cache: false,
        timeout: 60000,
        success: function (data) {
            if (data.status === 200) {
                $('#quantity').val(1);
                $('#alertTitle').text('Thêm giỏ hàng thành công');
                $('#addCartModal').modal('show');
            } else if (data.status === 500) {
                $('#alertTitle').text('Thêm giỏ hàng không thành công!! Hết hàng!!!')
                $('#addCartModal').modal('show');
            } else {
                window.location.href = window.location.origin + '/login';
            }
        }

    });
}

function addCart(id) {
    let quantity = $('#quantity').val();
    if (quantity <= 0) {
        $('#quantity').val(1);
    } else {
        $.ajax({
            type: 'POST',
            url: '/cart/' + id + '/' + quantity,
            cache: false,
            timeout: 60000,
            success: function (data) {
                if (data.status === 200) {
                    $('#quantity').val(1);
                    $('#alertTitle').text('Thêm giỏ hàng thành công');
                    $('#addCartModal').modal('show');
                } else if (data.status === 500) {
                    $('#alertTitle').text('Thêm giỏ hàng không thành công!! Hết hàng!!!')
                    $('#addCartModal').modal('show');
                } else {
                    window.location.href = window.location.origin + '/login';
                }
            }

        });
    }

}

