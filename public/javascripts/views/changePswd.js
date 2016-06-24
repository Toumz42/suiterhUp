$(function ()
{
    $.post("/Application/getCurrentUser", function (ret) {
        if (ret.isError) {
            alert("Veuillez vous connecter")
            window.location = '/login';
        }
        else {
            /*alert(ret.messageRetour);*/
            $("#hello").text(ret.messageRetour);
        }
    });

    $('#btn_changePswd').bind("click",function(){

        var oldPswd = $('#oldPswd').val();
        var newPswd = $('#newPswd').val();
        var reNewPswd = $('#reNewPswd').val();
        var isNew = true;
        $.post('/SuiteRHCtrl/changePswd', {
            "oldPswd": oldPswd,
            "newPswd": newPswd,
            "reNewPswd": reNewPswd
        }, function(data)
        {
            if(data != null)
            {
                alert(data.messageRetour);
            }
            else
            {
                alert("Changement de mot de passe réussi");
                location.assign("http://localhost:9800/menu");
            }

        });
    });
    //Permet de confirmer avec la touche Entrée
    $(document).keypress(function(e) {
        if(e.which == 13) {
            $('#btn_changePswd').click()
        }
    });
});