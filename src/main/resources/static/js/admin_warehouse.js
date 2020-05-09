$(document).ready(function () {
    $('#warehouseTable').dataTable();
    $('#submitBtn').click(function () {
        addWarehouse();
    });

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

