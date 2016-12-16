function alertMessage(content, status) {
    var alertDiv = $("#alertDiv");
    alertDiv.empty();
    alertDiv.append("" +
        "<div class='alert alert-{0}' role='alert'>".f(status) +
        "<button type='button' class='close' data-dismiss='alert' aria-label='Close'>" +
        "<span aria-hidden='true'>&times;</span>" +
        "</button>" + content +
        "</div>"
    )
}

function alertResponse(data) {
    var alertDiv = $("#alertDiv");
    alertDiv.empty();
    for (var i = 0; i < data.length; i++) {
        alertDiv.append("" +
            "<div class='alert alert-danger' role='alert'>" +
            "<button type='button' class='close' data-dismiss='alert' aria-label='Close'>" +
            "<span aria-hidden='true'>&times;</span>" +
            "</button><strong>Error! </strong>" + data[i].msg +
            "</div>")
    }
}