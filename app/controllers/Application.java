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

    public static void menu() { render(); }

    public static  void demandes() { render(); }


    public static void identifyUser(String login, String pswd) {
        ErrorUtils retour = null;
        Utilisateurs p = null;

        try {
            p = Utilisateurs.find("Select p " +
                    "from Utilisateurs p " +
                    "Where (p.login = ? " +
                    ") And p.motDePasse = ?" +
                    "",  login, pswd).first();
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

    public static void getCurrentUser()
    {
        ErrorUtils ret = null;

        String id = session.get( "userId" );
        if (id != null)
        {
            long l = Long.parseLong( id );
            Utilisateurs currentUser = Utilisateurs.findById( l );

            if ( currentUser != null )
            {
                ret = ErrorUtils.createError( false, "Bienvenue " + currentUser.prenom + " " + currentUser.nom, "info");
            }
            else
            {
                ret = ErrorUtils.createError( true, "Utilisateur non reconnu", "erreur" );
            }
        }
        else
        {
            ret = ErrorUtils.createError( true, "Session inactive", "erreur" );
        }

        renderJSON( ret );

    }

    public static void deconnect()
    {
        session.clear();
        renderJSON(ErrorUtils.createError(false,"Ok","info"));
    }

    public static Utilisateurs getCurrentUserObject()
    {
        try
        {
            String id = session.get( "userId" );
            if (id != null)

            {
                long l = Long.parseLong( id );

                Utilisateurs currentUser = Utilisateurs.findById( l );

                if ( currentUser != null )
                {
                    return currentUser;
                }
                else
                {
                    return null;
                }
            }
            else
            {
                return null;
            }
        }
        catch ( Exception e )
        {
            e.printStackTrace();
            return null;
        }
    }

}
