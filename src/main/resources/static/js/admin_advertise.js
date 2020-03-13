$(document).ready(function () {

    $('#advertiseTable').dataTable();

    $('#image').change(function () {
        loadImage(this, 'image_view');
    });

    resetErrorAdvetise();

    $('#submitBtn').click(function () {
        $('.error').text('');
        let advertise = getAdvertiseForm();
        $.ajax({
            type: 'POST',
            url: '/advertise',
            contentType: false,
            processData: false,
            data: advertise,
            success: function (result) {
                if (result.status === 200) {
                    setEmptyAdvertise();
                    $('#advertiseModal').modal('hide');
                    window.location.href = window.location.origin + "/admin/advertise";
                } else {
                    let arrError = result.errors;
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
    $('#active').val(active);
    $.ajax({
        type: 'PUT',
        url: "/advertise/" + id + "/" + active
    });
}

function editAdvertise(id) {
    $.ajax({
        type: 'GET',
        url: '/advertise/' + id,
        cache: false,
        timeout: 60000,
        success: function (data) {
            setDataAdvertise(data);
            $('#advertiseModal').modal("show");
        }
    });
}

function deleteAdvertise(id) {
    $('#deleteItemModal').modal('show');
    $('#deleteButton').click(function () {
        $.ajax({
            type: 'DELETE',
            url: "/advertise/" + id,
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

function getAdvertiseForm() {
    let emptyFile = new File([], "test.png", {type: 'image/jpg'});
    let advertise = new FormData();
    let content = $('#content').val();
    let image = $('#image').prop('files')[0];
    let imageLink = $('#imageLink').val();
    let id = $('#id').val();
    let active = $('#active').val() == '' ? 0 : $('#active').val();
    advertise.set("id", id);
    advertise.set("image", image == null ? emptyFile : image);
    advertise.set("imageLink", imageLink);
    advertise.set("content", content);
    advertise.set("active", active);
    return advertise;
}

function setEmptyAdvertise() {
    $('#id').val('');
    $('#content').val('');
    $('#imageLink').val('');
}

function setDataAdvertise(data) {
    $('#id').val(data['id']);
    $('#content').val(data['content']);
    $('#imageLink').val(data['imageLink']);
    $('#active').val(data['active']);
    $('#image_view').attr('src', data['imageLink']);
}

function resetErrorAdvetise() {
    hideElementError('id');
    hideElementError('content');
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

