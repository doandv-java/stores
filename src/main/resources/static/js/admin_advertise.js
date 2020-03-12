$(document).ready(function () {

    $('#advertiseTable').dataTable();

    $('#image').change(function () {
        loadImage(this, 'image_view');
    });
});

function changeActive(id) {
    let active = $('#' + id + 'active').prop('checked') === true ? 1 : 0;
    $.ajax({
        type: 'PUT',
        url: "/advertise/" + id + "/" + active
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
    let active = $('#active').val() == null ? 0 : $('#active').val();
    let nameOld = $('#nameOld').val();
    let deleted = $('#deleted').val();
    supply['id'] = id;
    supply['name'] = name;
    supply['phone'] = phone;
    supply['email'] = email;
    supply['address'] = address;
    supply['detail'] = detail;
    supply['nameOld'] = nameOld;
    supply['active'] = active;
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
    $('#active').val('');
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
    $('#active').val(supply['active']);
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

