$(document).ready(function () {
    $('#warehouseTable').dataTable({
        "pageLength": 5,
        "lengthChange": false,
        "order": [[0, "desc"]]
    });
    $('#submitBtn').click(function () {
        addWarehouse();
    });
    $('#cancelBtn').click(function () {
        window.location.href = window.location.origin + "/admin/warehouse";
    });
    validateWarehouse();
});

function updateWarehouse(id, productId) {
    let model = $('#WarehouseItemModal');
    model.modal('show');
    $('#ConfirmButton').click(function () {
        let warehouse = {};
        warehouse["id"] = id;
        warehouse["productId"] = productId;
        warehouse['quantity'] = $('#quantity').val();
        $.ajax({
            type: "POST",
            dataType: 'JSON',
            contentType: 'application/json',
            url: '/admin/warehouse',
            data: JSON.stringify(warehouse),
            cache: false,
            timeout: 60000,
            success: function (data) {
                if (data.status === 200) {
                    model.modal('hide');
                    window.location.href = window.location.origin + "/admin/warehouse";
                } else {
                    console.log('Error');
                    let arrError = data.errors;
                    for (let i = 0; i < arrError.length; i++) {
                        showError(arrError[i].field, arrError[i].message);
                    }
                }

            }
        });
    });

}

function addWarehouse() {
    let warehouse = {};
    warehouse["id"] = '';
    warehouse['productId'] = $('#productSelect option:selected').val();
    warehouse['quantity'] = $('#quantity').val();
    $.ajax({
        type: "POST",
        dataType: 'JSON',
        contentType: 'application/json',
        url: '/admin/warehouse',
        data: JSON.stringify(warehouse),
        cache: false,
        timeout: 60000,
        success: function (data) {
            if (data.status === 200) {
                window.location.href = window.location.origin + "/admin/warehouse";
            } else {
                console.log('Error');
                let arrError = data.errors;
                for (let i = 0; i < arrError.length; i++) {
                    showError(arrError[i].field, arrError[i].message);
                }
            }

        }
    });
}

function showError(id, message) {
    let element = $('#' + id);
    let error = $('#' + id + "_errors");
    element.addClass("input_error");
    error.text(message);
}

function hideError(id) {
    let element = $('#' + id);
    element.removeClass('input_error');
    let error = $('#' + id + "_errors");
    error.text('');
}

function validateWarehouse() {

    $('#quantity').keyup(function () {
        hideError('quantity');
    });
}
