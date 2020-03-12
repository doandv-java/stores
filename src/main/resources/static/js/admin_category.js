$(document).ready(function () {

    $('#categoryTable').dataTable();

    resetErrorCategory();

    $('#submitBtn').click(function () {
        let category = getCategoryForm();
        $.ajax({
            type: "POST",
            dataType: 'JSON',
            contentType: 'application/json',
            url: '/category',
            data: JSON.stringify(category),
            cache: false,
            timeout: 60000,
            success: function (data) {
                if (data.status === 200) {
                    setEmptyCategory();
                    $('#categoryModal').modal('hide');
                    window.location.href = window.location.origin + "/admin/category";
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
    let active = $('#' + id + 'active').prop('checked') === true ? 1 : 0;
    $.ajax({
        type: 'PUT',
        url: "/category/" + id + "/" + active
    });
}

function editCategory(id) {
    $.ajax({
        type: 'GET',
        url: '/category/' + id,
        cache: false,
        timeout: 60000,
        success: function (data) {
            setDataCategory(data);
            $('#categoryModal').modal("show");
        }
    });
}

function deleteCategory(id) {
    $('#deleteItemModal').modal('show');
    $('#deleteButton').click(function () {
        $.ajax({
            type: 'DELETE',
            url: "/category/" + id,
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

function getCategoryForm() {
    let category = {};
    let name = $('#name').val();
    let detail = $('#detail').val();
    let id = $('#id').val();
    let active = $('#active').val() == null ? 0 : $('#active').val();
    let nameOld = $('#nameOld').val();
    category['id'] = id;
    category['name'] = name;
    category['detail'] = detail;
    category['nameOld'] = nameOld;
    category['active'] = active;
    return category;
}

function setEmptyCategory() {
    $('#name').val('');
    $('#detail').val('');
    $('#id').val('');
    $('#active').val('');
    $('#nameOld').val('');
}

function setDataCategory(category) {
    $('#name').val(category['name']);
    $('#detail').val(category['detail']);
    $('#id').val(category['id']);
    $('#active').val(category['active']);
    $('#nameOld').val(category['name']);
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

function resetErrorCategory() {
    hideElementError('id');
    hideElementError('name');
    hideElementError('detail');
    hideElementError('nameOld');
}



