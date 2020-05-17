function loadImage(input, image_view) {
    if (input.files && input.files[0]) {
        let reader = new FileReader();
        reader.onload = function (e) {
            $('#' + image_view).attr('src', e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
    }
}

$(document).ready(function () {
    validateChangePasswordForm();
    $('#image').change(function () {
        loadImage(this, 'image_view');
    });

    resetErrorProfile();
    $('#submitBtn').click(function () {
        $('.error').text('');
        let profile = getDataProfile();
        $.ajax({
            type: 'POST',
            url: '/profile',
            contentType: false,
            processData: false,
            data: profile,
            success: function (result) {
                if (result.status === 200) {
                    window.location.href = window.location.origin + "/profile";
                } else {
                    let arrError = result.errors;
                    for (let i = 0; i < arrError.length; i++) {
                        showError(arrError[i].field, arrError[i].message);
                    }
                }
            }
        });
    });


    $('#btnChangePass').click(function () {
        let data = {};
        $('.error').text('');
        let passwordOld = $('#passwordOld');
        let passwordNew = $('#passwordNew');
        let confirmPassword = $('#confirmPassword');
        data['passwordOld'] = passwordOld.val();
        data['passwordNew'] = passwordNew.val();
        data['confirmPassword'] = confirmPassword.val();
        $.ajax({
            type: 'POST',
            contentType: 'application/json',
            dataType: 'JSON',
            url: '/changePassword',
            data: JSON.stringify(data),
            cache: false,
            timeout: 60000,
            success: function (data) {
                if (data.status === 200) {
                    passwordOld.val('');
                    passwordNew.val('');
                    confirmPassword.val('');
                    $('#alertSuccess').removeClass('hide')
                } else {
                    let arrError = data.errors;
                    for (let i = 0; i < arrError.length; i++) {
                        showError(arrError[i].field, arrError[i].message);
                    }
                }
            }
        });
    });

    $('#cancelBtn').click(function () {
        window.location.href = window.location.origin + '/home';
    });


});

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

// function changePassword() {
//     $('#passwordOld').val('');
//     $('#passwordNew').val('');
//     $('#confirmPassword').val('');
//
//
// }

function validateChangePasswordForm() {

    $('#passwordOld').keyup(function () {
        hideError('passwordOld');
    });
    $('#passwordNew').keyup(function () {
        hideError('passwordNew');
    });
    $('#confirmPassword').keyup(function () {
        hideError('confirmPassword');
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