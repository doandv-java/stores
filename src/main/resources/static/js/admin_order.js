$(document).ready(function () {
    $('#orderTable').dataTable({
        "pageLength": 5,
        "lengthChange": false,
        "order":[[ 0, "desc" ]]
    });
});

function changeStatus(orderId) {
    let status = $('#' + orderId + '_status').val();
    let changeSuccess = $('#updateStatus');
    $.ajax({
        type: 'PUT',
        url: '/admin/order/' + orderId + '?status=' + status,
        success: function (data) {
            if (data.status === 200) {
                changeSuccess.modal('show');
            } else {
                console.log("error");
                console.log(data);
            }
        }
    });
}