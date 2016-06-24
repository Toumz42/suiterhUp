/**
 * Created by Jonathan on 27/05/2016.
 */
$(function ()
{

    $('#login').val('');
    var password = $('#password').val('');

    $('#btn_connexion').bind("click",function()
    {
        var id = $('#login').val();
        var password = $('#password').val();
        var locat='login';

        $.post('/SuiteRHCtrl/identifyUser', {'login': id, 'password':password}, function(retour)
        {
            var locat;
            var isNew = 0;
            if (retour.isError)
            {
                alert(retour.messageRetour);
                $('#login').select();
            }
            else
            {
                var currentUserId = retour ;
                $.ajaxSetup({async : false});
                $.post('/SuiteRHCtrl/checkAccess', {'id': currentUserId}, function(dataAccess)
                {
                    if (!dataAccess.isError)
                    {
                        $.ajaxSetup({async : false});
                        $.post('/SuiteRHCtrl/checkPass', {"newPswd" : password}, function(dataPass)
                        {
                            if(!dataPass.isError)
                            {
                                $.ajaxSetup({async : false});
                                $.post('/SuiteRHCtrl/checkExpiration',  function(dataExpiration)
                                {
                                    if(dataExpiration == null)
                                    {
                                        /* window.location.href="http://localhost:9800/menu";*/
                                        location.assign("/menu");
                                    }
                                    else
                                    {
                                        alert(dataExpiration.messageRetour);
                                        location.assign("/changePswd");
                                    }
                                });
                            }
                            else
                            {
                                alert("Mot de passe non conforme, vous allez être redirigé");
                                location.assign("/changePswd");
                            }
                        });
                    }
                    else
                    {
                        alert(dataAccess.messageRetour);
                        $.post("/Application/deconnect",function(){})
                    }
                });

            }
        });

    });

    $(document).keypress(function(e) {
        if(e.which == 13) {
            $('#btn_connexion').click()
        }
    });

    $('#p_lost_pswd').bind("click", function()
    {
        var id = $('#login').val();
        if (id == "")
        {
            alert("Veuillez saisir votre identifiant et cliquez de nouveau sur J'ai perdu mon mot de passe");
        }
        else
        {
            if (confirm("Un mail vous sera envoyé à votre adresse avec vos identifiants de connections"))
            {
                $.post('/application/envoyerMdp', {'login': id}, function(retour)
                {
                    if (retour)
                    {
                        alert(retour.messageRetour);
                    }
                });
            }
        }

    });


});