$(document).ready(function () {
    $('#orderHistoryTable').dataTable({
        "pageLength": 5,
        "lengthChange": false,
        "order":[[ 0, "desc" ]]
    });
});

function DeleteOrder(id) {
    let deleteModal = $('#deleteOrderModal');
    deleteModal.modal("show");
    $('#deleteButton').click(function () {
        $.ajax({
            type: 'DELETE',
            url: '/order/' + id,
            success: function (data) {
                if (data.status == 200) {
                    deleteModal.modal('hide');
                    window.location.href = window.origin + '/order/history';
                } else {
                    console.log("error");
                }
            }
        });
    });
}