package controllers;

import models.Absences;
import models.Utilisateurs;
import models.utils.ErrorUtils;
import models.utils.UrlExecute;
import play.Play;
import play.mvc.Controller;

/**
 * Created by Jonathan on 06/06/2016.
 */
public class SuiteRHCtrl extends Controller {

    private static String SUITE_RH_URL = Play.configuration.get("application.suiteRH").toString();
    public static void getAbsence(Integer mois, Integer annee, String niveau, Boolean mesAbsences)
    {
        Utilisateurs u = Application.getCurrentUserObject();
        String url = SUITE_RH_URL + "/AbsencesCtrl/getAllByNiveauMobile?" +
                "annee=" +annee+"&"+
                "mois=" +mois+"&"+
                "niveau=" +niveau+"&"+
                "mesAbsences=" +mesAbsences+"&"+
                "userId=" +u.id;
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
}
