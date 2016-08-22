package controllers;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.org.apache.xpath.internal.operations.Bool;
import models.Absences;
import models.Utilisateurs;
import models.utils.ErrorUtils;
import models.utils.UrlExecute;
import play.Play;
import play.mvc.Controller;
import sun.net.www.http.HttpClient;
import sun.net.www.protocol.http.HttpURLConnection;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Jonathan on 06/06/2016.
 */
public class SuiteRHCtrl extends Controller {

    private static String SUITE_RH_URL = Play.configuration.get("application.suiteRH").toString();
    //    public static void getAbsence(Integer mois, Integer annee, String niveau, Boolean mesAbsences)
//    {
//        Utilisateurs u = Application.getCurrentUserObject();
//        String url = SUITE_RH_URL + "/AbsencesCtrl/getAllByNiveauMobile?" +
//                "annee=" +annee+"&"+
//                "mois=" +mois+"&"+
//                "niveau=" +niveau+"&"+
//                "mesAbsences=" +mesAbsences+"&"+
//                "userId=" +u.id;
//        String data = UrlExecute.getJSON(url,60000);
//
//        if(data!=null && !data.equals(""))
//        {
//            renderJSON(data);
//        }
//        else
//        {
//            renderJSON(ErrorUtils.createError(true,"Nope","error"));
//        }
//
//    }
    public static void getAbsence(Integer mois, Integer annee, String niveau, Boolean mesAbsences)
    {
        Utilisateurs u = Application.getCurrentUserObject();

        Map<String,Object> urlParameters = new LinkedHashMap<String, Object>();
        urlParameters.put("annee",annee);
        urlParameters.put("mois",mois);
        urlParameters.put("niveau",niveau);
        urlParameters.put("mesAbsences",mesAbsences);
        urlParameters.put("userId",u.id);

//        String urlParameters  =     "annee=" + annee +"&"+
//                                    "mois=" +mois+"&"+
//                                    "niveau=" +niveau+"&"+
//                                    "mesAbsences=" +mesAbsences+"&"+
//                                    "userId=" +u.id;
        String data = "";
        String request  = SUITE_RH_URL + "/AbsencesCtrl/getAllByNiveauMobile?" ;


        data = UrlExecute.getJSONbyPost(request,urlParameters);

        if(data!=null && !data.equals(""))
        {
            renderJSON(data);
        }
        else
        {
            renderJSON(ErrorUtils.createError(true,"Nope","error"));
        }
    }
    public static void identifyUser(String login, String password)
    {
        Utilisateurs u = Application.getCurrentUserObject();
        Map<String,Object> urlParameters = new LinkedHashMap<String, Object>();
        urlParameters.put("login",login);
        urlParameters.put("pswd",password);

        String data = "";
        String request  = SUITE_RH_URL + "/Application/identifyUser?" ;


        data = UrlExecute.getJSONbyPost(request,urlParameters);

        if(data!=null && !data.equals(""))
        {

            JsonParser parser = new JsonParser();
            JsonElement tradeElement = parser.parse("["+data+"]");
            JsonArray trade = tradeElement.getAsJsonArray();
            JsonObject o = (JsonObject) trade.get(0);
            if(o.get("id") != null) {
                String id = o.get("id").toString();
                session.put("userId", id);
                renderJSON(id);
            }

        }
        else
        {
            renderJSON(ErrorUtils.createError(true,"Erreur dans la saisie des identifiants","error"));
        }
    }


    public static void getNbrJours(Long id)
    {
        String url = SUITE_RH_URL + "/AbsencesCtrl/getJrMobile?" + "id=" +id;
        String data = UrlExecute.getJSON(url,60000);

        if(data!=null && !data.equals(""))
        {
            renderJSON(data);
        }
        else
        {
            renderJSON(ErrorUtils.createError(true,"Nope","error"));
        }
    }

    public static void checkAccess(Long id)
    {

        Map<String,Object> urlParameters = new LinkedHashMap<String, Object>();
        urlParameters.put("id",id);
        String data = "";
        String message = "Probleme de connexion";
        String request  = SUITE_RH_URL + "/UtilisateursCtrl/checkAccess?" ;

        data = UrlExecute.getJSONbyPost(request,urlParameters);
        Boolean isGood = false;

        if(data!=null && !data.equals(""))
        {

            JsonParser parser = new JsonParser();
            JsonElement tradeElement = parser.parse("["+data+"]");
            JsonArray trade = tradeElement.getAsJsonArray();
            if (!trade.get(0).isJsonPrimitive()) {
                JsonObject o = (JsonObject) trade.get(0);
                if (o.has("isError")) {
                    message = o.get("messageRetour").toString();
                }
            }
            else {
                isGood = trade.get(0).getAsBoolean();
            }


            if (isGood) {
                renderJSON(isGood);
            } else {
                renderJSON(ErrorUtils.createError(true,message,"error"));
            }
        }
        else
        {
            renderJSON(ErrorUtils.createError(true,"Nop","error"));
        }


    }

    public static void checkPass(String newPswd)
    {
        Utilisateurs u = Application.getCurrentUserObject();
        Long id = u.id;

        Map<String,Object> urlParameters = new LinkedHashMap<String, Object>();
        urlParameters.put("id",id);
        urlParameters.put("newPswd",newPswd);

//        String urlParameters  =  "id=" + id + "&newPswd=" + newPswd;

        String request  = SUITE_RH_URL + "/UtilisateursCtrl/checkPass?" ;
        String data = UrlExecute.getJSONbyPost(request,urlParameters);

        if(data != null && !data.equals(""))
        {
            renderJSON(data);
        }

    }

/*    public static void modificationPswd(String newPswd)
    {
        Utilisateurs u = Application.getCurrentUserObject();
        Long id = u.id;
        String urlParameters  =  "id=" + id + "&newPswd=" + newPswd;
        String request  = SUITE_RH_URL + "/UtilisateursCtrl/modificationPswd?" ;
        String data = UrlExecute.getJSONbyPost(request,urlParameters);

        renderJSON(data);

    }*/

    public static void changePswd(String oldPswd, String newPswd, String reNewPswd)
    {
        Utilisateurs u = Application.getCurrentUserObject();
        Long id = u.id;
        /*Vérifie si les champs sont bien remplis sinon retourne l'erreur*/

//        String urlParameters = "id=" + u.id + "&oldPswd=" + oldPswd + "&newPswd=" + newPswd + "&reNewPswd=" + reNewPswd;
        Map<String,Object> urlParameters = new LinkedHashMap<String, Object>();
        urlParameters.put("id",u.id);
        urlParameters.put("newPswd", newPswd );
        urlParameters.put("oldPswd", oldPswd);
        urlParameters.put("reNewPswd", reNewPswd);

        String request = SUITE_RH_URL + "/UtilisateursCtrl/changePswd?";
        String data = UrlExecute.getJSONbyPost(request, urlParameters);

        if(data == null || data.equals(""))
        {
            /*Vérifie si le nouveau mot de passe est conforme sinon retourne l'erreur*/

//            String urlParametersCheck  =  "id=" + id + "&newPswd=" + newPswd;
            Map<String,Object> urlParametersCheck = new LinkedHashMap<String, Object>();
            urlParametersCheck.put("id",id);
            urlParametersCheck.put("newPswd",newPswd);
            String requestCheck  = SUITE_RH_URL + "/UtilisateursCtrl/checkPass?" ;
            String dataCheck = UrlExecute.getJSONbyPost(requestCheck,urlParametersCheck);


            if(dataCheck == null ||dataCheck.equals(""))
            {
                /*S'execute uniquement si tout est ok et modifie le mot de passe en DB*/

//                String urlParametersMod  =  "id=" + id + "&newPswd=" + newPswd;
                Map<String,Object> urlParametersMod = new LinkedHashMap<String, Object>();
                urlParametersMod.put("id",id);
                urlParametersMod.put("newPswd",newPswd);
                String requestMod  = SUITE_RH_URL + "/UtilisateursCtrl/modificationPswd?" ;
                String dataMod = UrlExecute.getJSONbyPost(requestMod,urlParametersMod);
                renderJSON(dataMod);
            }

            else
            {
                /*Erreur de non conformité du mot de passe*/
                renderJSON(dataCheck);
            }

        }

        else
        {
            /*Erreur de saisie de champs*/
            renderJSON(data);
        }
    }

    public static void checkExpiration()
    {
        Utilisateurs u = Application.getCurrentUserObject();
        if(u != null)
        {
            Long id = u.id;
//          String urlParameters = "id=" + u.id;
            Map<String,Object> urlParameters = new LinkedHashMap<String, Object>();
            urlParameters.put("id",u.id);

            String request = SUITE_RH_URL + "/UtilisateursCtrl/checkExpiration?";
            String data = UrlExecute.getJSONbyPost(request, urlParameters);

            renderJSON(data);

        }

        else
        {
            ErrorUtils data = ErrorUtils.createError(true, "hfdsjfhsdjkfnhs", "Error");
            renderJSON(data);
        }
    }

    public static void changeEtatAbs(Long absId, String code, Integer niveau)
    {
        Utilisateurs u = Application.getCurrentUserObject();
        String data;
        if(u != null)
        {
            if (absId != null && code != null && niveau != null)
            {
//                String urlParameters = "userId=" + u.id +
//                        "&absId=" + absId +
//                        "&code=" + code +
//                        "&niveau=" + niveau;

                Map<String,Object> urlParameters = new LinkedHashMap<String, Object>();
                urlParameters.put("userId",u.id);
                urlParameters.put("absId",absId);
                urlParameters.put("code",code);
                urlParameters.put("niveau",niveau);

                String request = SUITE_RH_URL + "/AbsencesCtrl/changeEtatAbsenceMobile?";
                data = UrlExecute.getJSONbyPost(request, urlParameters);
            }
            else
            {
                data = "Un des paramètres est null";
                renderJSON(ErrorUtils.createError(true, data, "Error"));
            }
        }
        else
        {
            data = "Utilisateur introuvable";
            renderJSON(ErrorUtils.createError(true, data, "Error"));
        }
        //Retourne null si tout va bien sinon l'erreur

        renderJSON(ErrorUtils.createError(false,"ok",""));


    }

}
