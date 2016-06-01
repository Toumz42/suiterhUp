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

        $.post('/application/identifyUser', {'login': id, 'pswd':password}, function(retour)
        {

            if (retour)
            {
                alert(retour.messageRetour);
                $('#login').select();
            }
            else
            {
                window.location = '/menu';
            }
        });
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