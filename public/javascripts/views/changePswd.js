/**
 * Created by Jonathan on 15/06/2016.
 */
$(function()
{
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

    $(document).keypress(function(e) {
        if(e.which == 13) {
            $('#btn_changePswd').click()
        }
    });
});