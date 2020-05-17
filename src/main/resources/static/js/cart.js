$(document).ready(function () {

});

function changeQuantity(item_id, type) {
    let quantity_input = $('#' + item_id + '_quantity');
    let quantityOld = quantity_input.val();
    let quantity = getQuantity(item_id, type);
    if (quantity <= 0) {
        alert("So luong khong hop le");
        quantity_input.val(quantityOld);
    } else {
        $.ajax({
            type: 'PUT',
            url: 'cart/' + item_id + '?quantity=' + quantity,
            cache: false,
            timeout: 60000,
            success: function (data) {
                if (data.status === 200) {
                    quantity_input.val(quantity);
                } else if (data.status === 500) {
                    alert("So luong hang đặt chưa đủ hàng");
                } else {
                    window.location.href = window.location.origin + '/login';
                }
            }

        });
    }


}

function orderCart() {
    window.location.href = window.origin + '/checkout';
}

function getQuantity(item_id, type) {
    let quantity_input = $('#' + item_id + '_quantity');
    let quantity = quantity_input.val();
    if (type === 1) {
        quantity = parseInt(quantity) + 1;
    } else if (type === 2) {
        quantity = parseInt(quantity) - 1;
    } else if (type === 0) {
        quantity = parseInt(quantity_input.val());
    }
    return quantity;
}