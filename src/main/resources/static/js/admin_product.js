$(document).ready(function () {
    $('#productTable').dataTable({
        "pageLength": 5,
        "lengthChange": false,
    });

    resetErrorProduct()

    $('#submitBtn').click(function () {
        $('.error').text('');
        let product = getDataProduct();
        $.ajax({
            type: 'POST',
            url: '/admin/product',
            contentType: false,
            processData: false,
            data: product,
            success: function (result) {
                if (result.status === 200) {
                    window.location.href = window.location.origin + "/admin/product";
                } else {
                    let arrError = result.errors;
                    for (let i = 0; i < arrError.length; i++) {
                        showError(arrError[i].field, arrError[i].message);
                    }
                }
            }
        });
    });


    $('#cancelBtn').click(function () {
        window.location.href = window.location.origin + "/admin/product";
    });
});

function deleteProduct(id) {
    $('#deleteItemModal').modal('show');
    $('#deleteButton').click(function () {
        $.ajax({
            type: 'DELETE',
            url: "/product/" + id,
            cache: false,
            timeout: 60000,
            success: function (data) {
                if (data.status === 101) {
                    console.log("error");
                } else {
                    $('#deleteItemModal').modal('hide');
                    $('#' + id).remove();
                    window.location.href = window.location.origin + '/admin/product'
                }
            }
        })
    });
}

function getDataProduct() {
    let emptyFile = new File([], "test.png", {type: 'image/jpg'});
    let name = $('#name');
    let id = $('#id');
    let producer = $('#producer option:selected').val();
    let categoryId = $('#categoryId option:selected').val();
    let os = $('#os');
    let cpu = $('#cpu');
    let gpu = $('#gpu');
    let ram = $('#ram');
    let detail = $('#detail');
    let imageLink = $('#imageLink');
    let price = $('#price');
    let weight = $('#weight');
    let storage = $('#storage');
    let screen = $('#screen');
    let releaseYear = $('#releaseYear');
    let deleted = $('#deleted');
    let nameOld = $('#nameOld');
    let product = new FormData();
    product.set('Id', id.val());
    product.set('name', name.val());
    product.set('producer', producer);
    product.set('price', price.val() == null ? 0 : (price.val()));
    product.set('categoryId', categoryId == null ? null :(categoryId));
    product.set('detail', detail.val());
    product.set('cpu', cpu.val());
    product.set('os', os.val());
    product.set('imageLink', imageLink.val());
    product.set('image', $('#image').prop('files')[0] == null ? emptyFile : $('#image').prop('files')[0]);
    product.set('ram', ram.val());
    product.set('gpu', gpu.val());
    product.set('screen', screen.val());
    product.set('storage', storage.val());
    product.set('weight', parseFloat(weight.val()));
    product.set('storage', storage.val());
    product.set('releaseYear', releaseYear.val());
    product.set('deleted', deleted.val());
    product.set('nameOld', nameOld.val());
    return product;
}

function hideElementError(id) {
    let element = $('#' + id);
    element.keyup(function () {
        hideError(id);
    });
    element.change(function () {
        hideError(id);
    });
    element.focusin(function () {
        hideError(id);
    });
}

function resetErrorProduct() {
    hideElementError('name');
    hideElementError('producer');
    hideElementError('categoryId');
    hideElementError('os');
    hideElementError('cpu');
    hideElementError('gpu');
    hideElementError('ram');
    hideElementError('price');
    hideElementError('weight');
    hideElementError('storage');
    hideElementError('screen');
    hideElementError('detail');
    hideElementError('releaseYear');
    hideElementError('nameOld');
    hideElementError('deleted');
    hideElementError('imageLink');
}