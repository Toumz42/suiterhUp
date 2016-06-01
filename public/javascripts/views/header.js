$(function () {
    $.post("/Application/getCurrentUser", function (ret) {
        if (ret.isError) {
            window.location = '/login';
        }
        else {
            /*alert(ret.messageRetour);*/
            $("#hello").text(ret.messageRetour);
        }
    });

});