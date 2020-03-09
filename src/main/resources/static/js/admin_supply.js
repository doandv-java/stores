$(document).ready(function () {
    $('#supplyTable').dataTable();

});

function getSupplyForm() {
    let supply = {};
    let name = $('#name').val();
    let phone = $('#phone').val();
    let email = $('#email').val();
    let address = $('#address').val();
    let detail = $('#detail').val();
    let id = $('#id').val();
    let nameOld = $('#nameOld').val();
    supply['id'] = id;
    supply['name'] = name;
    supply['phone'] = phone;
    supply['email'] = email;
    supply['address'] = address;
    supply['detail'] = detail;
    supply['nameOld'] = nameOld;
    return supply;
}

function setDataSupply(supply) {
    $('#name').val(supply['name']);
    $('#phone').val(supply['phone']);
    $('#email').val(supply['email']);
    $('#address').val(supply['address']);
    $('#detail').val(supply['detail']);
    $('#id').val(supply['id']);
    $('#nameOld').val(supply['nameOld']);
}

function hideElementError(id) {
    $('#' + id).keyup(function () {
        hideError(id);
    });
    $('#' + id).change(function () {
        hideError(id);
    });
    $('#' + id).focusin(function () {
        hideError(id);
    });
}

function resetErrorSupply() {
    hideElementError('id');
    hideElementError('name');
    hideElementError('phone');
    hideElementError('email');
    hideElementError('address');
    hideElementError('detail');
    hideElementError('nameOld');
}

