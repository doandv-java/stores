$(document).ready(function () {
    //change
    changeError('userName');
    //send data
    $('#btnSend').click(function () {
        let forgotModal = $('#forgotPasswordModal');
        let userName = $('#userName').val();
        let user = {};
        user['userName'] = userName;
        $.ajax({
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(user),
            dataType: "JSON",
            url: "/forgotPassword",
            timeout: 6000,
            cache: false,
            success: function (data) {
                console.log(data);
                if (data.result) {
                    $('#userName').val('');
                    forgotModal.modal('hide');
                } else {
                    let validator = data.errorValidators;
                    let service = data.errorServices;
                    viewError(validator);
                    viewError(service);
                }
            }
        });
    });

    $('#forgotBtn').click(function () {
        $('#userName').val('');
        $('.error').text('');
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
