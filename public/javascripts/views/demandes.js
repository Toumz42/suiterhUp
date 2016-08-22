/**
 * Created by Jonathan on 02/06/2016.
 */
$(function()
{
    var d = new Date();
    $.post("/SuiteRHCtrl/getAbsence",{
        "mois": $('#filtre_mois').val(),
        "annee": 2016,
        "niveau": $('#filtre_niveau').val(),
        "mesAbsences": false
    },function(res)
    {
        $.post("/UtilisateursCtrl/getMyNiveau",{

        },function(retNiv)
        {
            $("#liste").empty();
            $.each(res, function (index,value) {
                if (retNiv > 1 || $('#filtre_niveau').val() > 1) {
                    divNiveau1 = "<div class='"+couleur(value.etatAbsence)+"'>" +
                        "<input class='n1' type='button'>&nbsp" +
                        "</div>";
                    divNiveau2 = "<div class=' button "+couleur(value.etatAbsenceN2)+"'>" +
                        "<input class='n2' type='button'>&nbsp" +
                        "</div>";
                } else {
                    divNiveau1 = "<div class='"+couleur(value.etatAbsence)+"' style='float: right'>" +
                    "<input class='n1' type='button'>&nbsp" +
                    "</div>";
                    divNiveau2 = "";
                }
                $("#liste").append(
                    "<div class='absence' idAbs='"+ value.id +"'>" +
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
                    divNiveau1 +
                    divNiveau2 +
                    "</div>"+
                    "<div class='bouttons'>"+
                    "<input type='button' class='vertAffec'>&nbsp"+
                    "<input type='button' class='rougeAffec'>&nbsp"+
                    "<input type='button' class='jauneAffec'>&nbsp"+
                    "<input type='hidden' class='idSelected' value='"+value.id+"'>"+
                    "<input type='hidden' class='niveauSelected'>"+
                    "</div>"+
                    "</div>"

                );

                $(".bouttons").hide();
            })
        });
    });

    //Permet d'afficher/cacher la div .bouttons correspodant à l'absence en question ainsi que prendre
    //les données relatives à l'absence (l'id dans #idSelected et le niveau dans #niveauSelected )
    $(".n1").live("click",function () {
        monClick = $(this).parent();
        divBoutons = $(this).parent().parent().parent().find(".bouttons");
        if (divBoutons.is(":visible") ) {
            if (divBoutons.find(".niveauSelected").val() == 1){
                divBoutons.toggle();
            }
        } else {
                divBoutons.toggle();
        }
        divBoutons.find(".niveauSelected").val(1);
        // $("#idSelected").val($(this).parent().parent().parent().attr('idAbs'));


    });

    $(".n2").live("click",function () {
        monClick = $(this).parent();
        divBoutons = $(this).parent().parent().parent().find(".bouttons");
        if (divBoutons.is(":visible") ) {
            if (divBoutons.find(".niveauSelected").val() == 2){
                divBoutons.toggle();
            }
        } else {
            divBoutons.toggle();
        }
        divBoutons.find(".niveauSelected").val(2);
        // $("#idSelected").val($(this).parent().parent().parent().attr('idAbs'));

    });


    $(".vertAffec").live("click",function () {
        divBoutons = $(this).parent().parent().parent().find(".bouttons");
        idSelected = divBoutons.find(".idSelected").val();
        niveauSelected = divBoutons.find(".niveauSelected").val();

        $.post("/SuiteRHCtrl/changeEtatAbs", {
            "absId": idSelected,
            "code": "2",
            "niveau": niveauSelected
        },function(data)
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
        divBoutons = $(this).parent().parent().parent().find(".bouttons");
        idSelected = divBoutons.find(".idSelected").val();
        niveauSelected = divBoutons.find(".niveauSelected").val();

        $.post("/SuiteRHCtrl/changeEtatAbs",{
            "absId": idSelected,
            "code": "3",
            "niveau": niveauSelected
            }
            ,function(data)
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
            "absId": idSelected,
            "code": "1",
            "niveau": niveauSelected
        },function(data)
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

    $('#filtre_mois').change(function () {
        $.post("/SuiteRHCtrl/getAbsence",{
            "mois": $('#filtre_mois').val(),
            "annee": 2016,
            "niveau": $('#filtre_niveau').val(),
            "mesAbsences": false
        },function(res)
        {
            $.post("/UtilisateursCtrl/getMyNiveau",{

            },function(retNiv)
            {
                $("#liste").empty();
                $.each(res, function (index,value) {
                    if (retNiv > 1 || $('#filtre_niveau').val() > 1) {
                        divNiveau1 = "<div class='"+couleur(value.etatAbsence)+"'>" +
                            "<input class='n1' type='button'>&nbsp" +
                            "</div>";
                        divNiveau2 = "<div class=' button "+couleur(value.etatAbsenceN2)+"'>" +
                            "<input class='n2' type='button'>&nbsp" +
                            "</div>";
                    } else {
                        divNiveau1 = "<div class='"+couleur(value.etatAbsence)+"' style='float: right'>" +
                            "<input class='n1' type='button'>&nbsp" +
                            "</div>";
                        divNiveau2 = "";
                    }
                    $("#liste").append(
                        "<div class='absence' idAbs='"+ value.id +"'>" +
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
                        divNiveau1 +
                        divNiveau2 +
                        "</div>"+
                        "<div class='bouttons'>"+
                        "<input type='button' class='vertAffec'>&nbsp"+
                        "<input type='button' class='rougeAffec'>&nbsp"+
                        "<input type='button' class='jauneAffec'>&nbsp"+
                        "<input type='hidden' class='idSelected' value='"+value.id+"'>"+
                        "<input type='hidden' class='niveauSelected'>"+
                        "</div>"+
                        "</div>"

                    );

                    $(".bouttons").hide();
                })
            });
        });
    });
    $('#filtre_niveau').change(function () {
        $.post("/SuiteRHCtrl/getAbsence",{
            "mois": $('#filtre_mois').val(),
            "annee": 2016,
            "niveau": $('#filtre_niveau').val(),
            "mesAbsences": false
        },function(res)
        {
            $.post("/UtilisateursCtrl/getMyNiveau",{

            },function(retNiv)
            {
                $("#liste").empty();
                $.each(res, function (index,value) {
                    if (retNiv > 1 || $('#filtre_niveau').val() > 1) {
                        divNiveau1 = "<div class='"+couleur(value.etatAbsence)+"'>" +
                            "<input class='n1' type='button'>&nbsp" +
                            "</div>";
                        divNiveau2 = "<div class=' button "+couleur(value.etatAbsenceN2)+"'>" +
                            "<input class='n2' type='button'>&nbsp" +
                            "</div>";
                    } else {
                        divNiveau1 = "<div class='"+couleur(value.etatAbsence)+"' style='float: right'>" +
                            "<input class='n1' type='button'>&nbsp" +
                            "</div>";
                        divNiveau2 = "";
                    }
                    $("#liste").append(
                        "<div class='absence' idAbs='"+ value.id +"'>" +
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
                        divNiveau1 +
                        divNiveau2 +
                        "</div>"+
                        "<div class='bouttons'>"+
                        "<input type='button' class='vertAffec'>&nbsp"+
                        "<input type='button' class='rougeAffec'>&nbsp"+
                        "<input type='button' class='jauneAffec'>&nbsp"+
                        "<input type='hidden' class='idSelected' value='"+value.id+"'>"+
                        "<input type='hidden' class='niveauSelected'>"+
                        "</div>"+
                        "</div>"

                    );

                    $(".bouttons").hide();
                })
            });
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