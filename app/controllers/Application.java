package controllers;

import models.utils.ErrorUtils;
import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

    public static void index() {
        render();
    }

    public static void login() {
        render();
    }


    public static void identifyUser(String login, String pswd) {
        ErrorUtils retour = null;
        Utilisateurs p = null;

        try {
            p = Utilisateurs.find("Select p " +
                    "from Utilisateurs p " +
                    "Where (p.login = ? " +
                    "Or p.matricule = ? " +
                    "Or p.email = ? " +
                    ") And p.motDePasse = ?" +
                    "", login, login, login, pswd).first();
        } catch (Exception e) {
            e.printStackTrace();
            retour = ErrorUtils.createError(true, e.getMessage(), "erreur");
        }

        if (p == null) {
            retour = ErrorUtils.createError(false, "Erreur dans la saisie de l'identifiant ou du mot de passe", "warn");
        } else {
            session.put("userId", p.id);
        }

        renderJSON(retour);
    }
}
