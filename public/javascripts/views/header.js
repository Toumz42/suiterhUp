$(function () {
    $.post("/Application/getCurrentUser", function (ret) {
        if (ret.isError) {
            window.location = '/login';
        }
        else {
            /*alert(ret.messageRetour);*/
            $.post("/SuiteRHCtrl/checkExpiration", function(data)
            {
                if(data != null)
                {
                    alert(data.messageRetour);
                    window.location = '/changePswd';
                }
            });
            $("#hello").text(ret.messageRetour);
        }
    });



    $("#btn_deconnexion").click(function()
    {
        $.post("/Application/deconnect",function()
        {
            window.location = '/login';
        })
    })

});