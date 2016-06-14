package controllers;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
        String urlParameters  =     "annee=" + annee +"&"+
                                    "mois=" +mois+"&"+
                                    "niveau=" +niveau+"&"+
                                    "mesAbsences=" +mesAbsences+"&"+
                                    "userId=" +u.id;
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
        String urlParameters  =     "login=" + login +"&"+
                                    "pswd=" +password;
        String data = "";
        String request  = SUITE_RH_URL + "/Application/identifyUser?" ;


        data = UrlExecute.getJSONbyPost(request,urlParameters);

        if(data!=null && !data.equals(""))
        {

            JsonParser parser = new JsonParser();
            JsonElement tradeElement = parser.parse("["+data+"]");
            JsonArray trade = tradeElement.getAsJsonArray();
            JsonObject o = (JsonObject) trade.get(0);
            String id = o.get("id").toString();
            session.put("userId", id);

            renderJSON(id);
        }
        else
        {
            renderJSON(ErrorUtils.createError(true,"Nope","error"));
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

        String urlParameters  =  "id=" + id ;
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
            JsonObject o = (JsonObject) trade.get(0);
            if (!o.has("isError")) {
                isGood = trade.get(0).getAsBoolean();
            } else {
                message = o.get("messageRetour").toString();
            }


            if (isGood) {
                renderJSON(isGood);
            } else {
                renderJSON(ErrorUtils.createError(true,message,"error"));
            }
        }
        else
        {
            renderJSON(ErrorUtils.createError(true,"Nope","error"));
        }


    }
}
