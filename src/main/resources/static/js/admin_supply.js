$(document).ready(function () {

    $('#supplyTable').dataTable();

    resetErrorSupply();

    $('#submitBtn').click(function () {
        let supply = getSupplyForm();
        $.ajax({
            type: "POST",
            dataType: 'JSON',
            contentType: 'application/json',
            url: '/supply',
            data: JSON.stringify(supply),
            cache: false,
            timeout: 60000,
            success: function (data) {
                if (data.status === 200) {
                    setEmptySupply();
                    $('#supplyModal').modal('hide');
                    window.location.href = window.location.origin + "/admin/supply";
                } else {
                    let arrError = data.errors;
                    for (let i = 0; i < arrError.length; i++) {
                        showError(arrError[i].field, arrError[i].message);
                    }
                }
            }

        });
    });
});

function changeActive(id) {
    let active = $('#activeCheck').prop('checked') === true ? 1 : 0;
    $.ajax({
        type:'PUT',
        url:"/supply/"+id+"/"+active
    });
}

function editSupply(id) {
    $.ajax({
        type: 'GET',
        url: '/supply/' + id,
        cache: false,
        timeout: 60000,
        success: function (data) {
            setDataSupply(data);
            $('#supplyModal').modal("show");
        }
    });
}

function deleteSupply(id) {
    $('#deleteItemModal').modal('show');
    $('#deleteButton').click(function () {
        $.ajax({
            type: 'DELETE',
            url: "/supply/" + id,
            cache: false,
            timeout: 60000,
            success: function (data) {
                if (data.status === 101) {
                    console.log("error");
                } else {
                    $('#deleteItemModal').modal('hide');
                    $('#' + id).remove();
                }
            }
        })
    });
}

function getSupplyForm() {
    let supply = {};
    let name = $('#name').val();
    let phone = $('#phone').val();
    let email = $('#email').val();
    let address = $('#address').val();
    let detail = $('#detail').val();
    let id = $('#id').val();
    let active = $('#active').val() == null ? 0 : 1;
    let nameOld = $('#nameOld').val();
    let deleted = $('#deleted').val();
    supply['id'] = id;
    supply['name'] = name;
    supply['phone'] = phone;
    supply['email'] = email;
    supply['address'] = address;
    supply['detail'] = detail;
    supply['nameOld'] = nameOld;
    supply['deleted'] = deleted;
    return supply;
}

function setEmptySupply() {
    $('#name').val('');
    $('#phone').val('');
    $('#email').val('');
    $('#address').val('');
    $('#detail').val('');
    $('#id').val('');
    $('#active').val(false);
    $('#nameOld').val('');
    $('#deleted').val('')
}

function setDataSupply(supply) {
    $('#name').val(supply['name']);
    $('#phone').val(supply['phone']);
    $('#email').val(supply['email']);
    $('#address').val(supply['address']);
    $('#detail').val(supply['detail']);
    $('#id').val(supply['id']);
    $('#active').val(supply['active'] === 1);
    $('#nameOld').val(supply['name']);
    $('#deleted').val(supply["deleted"])
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

