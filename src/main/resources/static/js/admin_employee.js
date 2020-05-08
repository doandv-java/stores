$(document).ready(function () {
    $('#employeeTable').dataTable({
        "pageLength": 5,
        "lengthChange": false,
    });

    $('#submitBtn').click(function () {
        $('.error').text('');
        let profile = getDataProfile();
        $.ajax({
            type: 'POST',
            url: '/admin/profile',
            contentType: false,
            processData: false,
            data: profile,
            success: function (result) {
                if (result.status === 200) {
                    window.location.href = window.location.origin + "/admin/employee";
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
        window.location.href = window.location.origin + "/admin/employee";
    });

    resetErrorProfile();
});
function  deleteEmployee(id) {
    $('#deleteItemModal').modal('show');
    $('#deleteButton').click(function () {
        $.ajax({
            type: 'DELETE',
            url: "/employee/" + id,
            cache: false,
            timeout: 60000,
            success: function (data) {
                if (data.status === 101) {
                    console.log("error");
                } else {
                    $('#deleteItemModal').modal('hide');
                    $('#' + id).remove();
                    window.location.href=window.location.origin+'/admin/employee'
                }
            }
        })
    });
}
function getDataProfile() {
    let emptyFile = new File([], "test.png", {type: 'image/jpg'});
    let name = $('#name');
    let id = $('#id');
    let userName = $('#userName');
    let birthDay = $('#birthDay');
    let phone = $('#phone');
    let address = $('#address');
    let role = $('#role');
    let imageLink = $('#imageLink');
    let state = $('#state');
    let deleted = $('#deleted');
    let userNameOld = $('#userNameOld');
    let profile = new FormData();
    profile.set('id', id.val());
    profile.set('userName', userName.val());
    profile.set('role', role.val());
    profile.set('name', name.val());
    profile.set('birthDay', birthDay.val());
    profile.set('gender', $('#gender option:selected').val());
    profile.set('address', address.val());
    profile.set('phone', phone.val());
    profile.set('imageLink', imageLink.val());
    profile.set('image', $('#image').prop('files')[0] == null ? emptyFile : $('#image').prop('files')[0]);
    profile.set('state', state.val());
    profile.set('deleted', deleted.val());
    profile.set('userNameOld', userNameOld.val());
    return profile;
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

function resetErrorProfile() {
    hideElementError('name');
    hideElementError('userName');
    hideElementError('birthDay');
    hideElementError('phone');
    hideElementError('address');
    hideElementError('role');
    hideElementError('imageLink');
    hideElementError('state');
    hideElementError('deleted');
    hideElementError('userNameOld');
}
