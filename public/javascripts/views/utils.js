$(function()
{
    /*Permet de gérer l'entête des liste (affiche et masque)*/
    /* Copier le template ci-dessous
       <div class="afficheEiList">
          <span class="eyeIcone AfficheBarre"><img src='/public/images/eye32.png' title='Masquer le dashboard'> </span>
          <span class="eiListtitre AfficheBarre">&{'lib.dashboard'} </span>
//          Vous pouvez ajouter des éléments dans la barre
      </div>
      <div id="Content">
//          Vous mettez votre contenu ici
      </div>
                  */
    $(".AfficheBarre").click(function()
    {
        var content = $(this).parent().parent().children().eq(1);
        var eye = $(this).parent().find(".eyeIcone").children();
        if ($(content).css("display")!="none")
             {
                 $(content).hide();
                 $(eye).attr("src","/public/images/eye-hidden32.png");
             }
             else
             {
                 $(content).show();
                 $(eye).attr("src","/public/images/eye32.png");
             }
    });

});
function frenchDate(dateValeur)
{
    dateOut = new Date(dateValeur);
    var jour = dateOut.getDate();
    var mois = dateOut.getMonth()+1;
    var annee = dateOut.getFullYear();
    if (jour < 10 ) jour = "0" + jour;
    if (mois < 10 ) mois = "0" + mois;
    return jour + '/' + mois + "/" + annee;

}

function javaDate(jsDate)
{
    var t = jsDate.substring(0).split('/');
    var javaDate = t[2] + "-" + t[1] + "-" + t[0];
    return javaDate;

    /*var dateOut = dateValeur.substring(6,10) + "-" +  dateValeur.substring(3,5) + "-" + dateValeur.substring(0,2);
    return dateOut;*/
}
function extractUrlParams(){
    var t = location.search.substring(1).split('&');
    var f = [];
    for (var i=0; i<t.length; i++){
        var x = t[ i ].split('=');
        f[x[0]]=x[1];
    }
    return f;
}
