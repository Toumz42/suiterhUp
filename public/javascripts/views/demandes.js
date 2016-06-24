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
                "<div class='absence' idAbs='"+ value.id+"'>" +
                    "<details class='nom'>" +
                        "<summary>" + value.salarieAbsenceNom+" "+value.salarieAbsencePrenom+"</summary>"+
                        "<fieldset class='comm'>"+
                            "<p class='commentaire'>"+ value.commentaires +"</p>"+
                        "</fieldset>"+
                    "</details>" +
                    "<div class='date'>" +
                        "<div class='duree'>Du " + value.dateDebut +" Au " + value.dateFin +"</div>" +
                        "<div class='type'>" + value.typeLibelle + "</div>" +
                    "</div>" +
                    "<div class='etat'>"+
                        "<div class='"+couleur(value.etatAbsence)+"'>" +
                            "<input class='n1' type='button'>&nbsp" +
                        "</div>" +
                        "<div class=' button "+couleur(value.etatAbsenceN2)+"'>" +
                            "<input class='n2' type='button'>&nbsp" +
                        "</div>" +
                    "</div>"+
                    "<div class='bouttons'>"+
                        "<input type='button' class='vertAffec'>&nbsp"+
                        "<input type='button' class='rougeAffec'>&nbsp"+
                        "<input type='button' class='jauneAffec'>&nbsp"+
                    "</div>"+
                "</div>"

            );

            $(".bouttons").hide();
        })
    });

    //Permet d'afficher/cacher la div .bouttons correspodant à l'absence en question ainsi que prendre
    //les données relatives à l'absence (l'id dans #idSelected et le niveau dans #niveauSelected )
    $(".n1").live("click",function () {
        monClick = $(this).parent();
        if($(this).parent().parent().parent().find(".bouttons").is(":hidden"))
        {
            $(".bouttons").hide();
            $(this).parent().parent().parent().find(".bouttons").show();
            $("#idSelected").val($(this).parent().parent().parent().attr('idAbs'));
            $("#niveauSelected").val(1);
        }
        else
        {
            $(".bouttons").hide();
        }
    });

    $(".n2").live("click",function () {
        monClick = $(this).parent();
        if($(this).parent().parent().parent().find(".bouttons").is(":hidden"))
        {
            $(".bouttons").hide();
            $(this).parent().parent().parent().find(".bouttons").show();
            $("#idSelected").val($(this).parent().parent().parent().attr('idAbs'));
            $("#niveauSelected").val(2);


        }
        else
        {
            $(".bouttons").hide();
        }
    });


    $(".vertAffec").live("click",function () {
        $.post("/SuiteRHCtrl/changeEtatAbs",{
            "absId": $("#idSelected").val(),
            "code": "2",
            "niveau": $("#niveauSelected").val()},function(data)
        {
            if(!data.isError)
            {

                $(monClick).removeClass("rouge vert jaune");
                $(monClick).addClass("vert");
                $(".bouttons").hide()
            }
            else
            {
                alert(data.messageRetour);
            }
        });
    });


    $(".rougeAffec").live("click",function () {
        $.post("/SuiteRHCtrl/changeEtatAbs",{
            "absId": $("#idSelected").val(),
            "code": "3",
            "niveau": $("#niveauSelected").val()},function(data)
        {
            if(!data.isError)
            {

                $(monClick).removeClass("rouge vert jaune");
                $(monClick).addClass("rouge");
                $(".bouttons").hide()
            }
            else
            {
                alert(data.messageRetour);
            }
        });
    });


    $(".jauneAffec").live("click",function () {
        $.post("/SuiteRHCtrl/changeEtatAbs",{
            "absId": $("#idSelected").val(),
            "code": "1",
            "niveau": $("#niveauSelected").val()},function(data)
        {
            if(!data.isError)
            {

                $(monClick).removeClass("rouge vert jaune");
                $(monClick).addClass("jaune");
                $(".bouttons").hide()
            }
            else
            {
                alert(data.messageRetour);
            }
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