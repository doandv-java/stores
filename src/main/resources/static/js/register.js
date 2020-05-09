$(document).ready(function () {

    //change error when key up select change
    changeError("name");
    changeError('userName');
    changeError('password');

    //register
    $('#btnRegister').click(function () {
        let name = $('#name').val();
        let email = $('#userName').val();
        let password = $('#password').val();

        let register = {
            name: name,
            userName: email,
            password: password
        };
        $.ajax({
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(register),
            dataType: 'JSON',
            url: "/register",
            timeout: 6000,
            cache: false,
            success: function (data) {
                console.log(data);
                if (data.result) {
                    //remove value
                    $('#name').val('');
                    $('#userName').val('');
                    $('#password').val('');
                    //show alert
                    $('#alertSuccess').removeClass('hide');
                } else {
                    //show error
                    let validator = data.errorValidators;
                    let service = data.errorServices;
                    viewError(validator);
                    viewError(service);
                }
            }
        });
    });
});

function viewError(errors) {
    if (errors != null) {
        for (i = 0; i < errors.length; i++) {
            let fieldId = errors[i].field;
            $("#" + fieldId + "_errors").text(errors[i].message);
        }
    }
}

function changeError(id) {
    let element = $('#' + id);
    let error = $('#' + id + '_errors');
    element.keyup(function () {
        error.text('');
    });
    element.focusin(function () {
        error.text('');
    });
    element.change(function () {
        error.text('');
    });
}
