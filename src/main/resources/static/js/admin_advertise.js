$(document).ready(function () {

    $('#advertiseTable').dataTable();

    $('#image').change(function() {
        loadImage(this, 'image_view');
    });
});
