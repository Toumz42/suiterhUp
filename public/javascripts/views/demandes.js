/**
 * Created by Jonathan on 02/06/2016.
 */
$(function()
{
    var d = new Date();
    $.post("/SuiteRHCtrl/getAbsence",{
        "mois": 5,
        "annee": 2016,
        "niveau": 1,
        "mesAbsences": false
    },function(res)
    {
        $.each(res, function (index,value) {
            $("#liste").append(
                "<div class='absence' id='"+ value.id+"'>" +
                    "<details class='nom'>" +
                        "<summary>" + value.salarieAbsenceNom+" "+value.salarieAbsencePrenom+"</summary>"+
                        "<fieldset class='comm'>"+
                            "<p class='commentaire'>"+ value.commentaires +"</p>"+
                        "</fieldset>"+
                    "</details>" +
                    "<div class='date'>" +
                        "<div class='dateDebut'>Du " + value.dateDebut +"</div>" +
                        "<div class='dateFin'>Au " + value.dateFin +"</div>" +
                    "</div>" +
                    "<div class='etat'>"+
                        "<div class='"+couleur(value.etatAbsence)+"'>" +
                            "<input  class='n1' type='button'>&nbsp" +
                        "</div>" +
                        "<div class=' button "+couleur(value.etatAbsenceN2)+" n2'>" +
                            "<input class='n2' type='button'>&nbsp" +
                        "</div>" +
                    "</div>"+
                    "<div class='bouttons' >"+
                        "<input type='button' class='vert'>&nbsp"+
                        "<input type='button' class='rouge'>&nbsp"+
                        "<input type='button' class='jaune'>&nbsp"+
                    "</div>"+
                "</div>"
            );

            $(".bouttons").hide();
        })
    });

    $(document).ready(function() {
        $(".n1").live("click", function () {
            $(".bouttons").show();

            var blip= $("n1").parent().parent().parent().id;
            alert(blip);

        });
    });

});

function couleur(etat)
{
    str="";
    if (etat != null) {
        if (etat != "1") {
            if (etat == "2") {
                str=('vert');
            }
            else {
                str=('rouge');
            }
        }
        else {
            str=('jaune');
        }
    }
    else
    {
        str=('gris');
    }
    return str;

}