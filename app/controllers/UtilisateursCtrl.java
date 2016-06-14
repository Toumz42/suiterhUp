package controllers;


import models.Utilisateurs;
import models.utils.DateUtils;
import models.utils.ErrorUtils;
import play.mvc.Controller;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: obal
 * Date: 01/06/14
 * Time: 22:37
 * To change this template use File | Settings | File Templates.*/

public class UtilisateursCtrl extends Controller {


    public static void saveUtilisateurParam(Long id,
                                            Boolean ngeN1,
                                            Boolean ngeN2) {

        Utilisateurs u = null;
        Utilisateurs currentUser = Application.getCurrentUserObject();
        ErrorUtils retour = null;
        if (id == null) {
            u = new Utilisateurs();
            u.creePar = currentUser;
            u.creeLe = new Date();
            u.save();
        } else {
            u = Utilisateurs.findById(id);
            u.modifiePar = currentUser;
            u.modifieLe = new Date();
        }
        if (u != null) {
            if (ngeN1 != null) {
                if (ngeN1) {
                    u.niveauGestionEquipe = "1";
                }
            }
            if (ngeN2 != null) {
                if (ngeN2) {
                    u.niveauGestionEquipe = "2";
                }
            }
            u.save();
        }


    }

    public static void getById(Long id) {
        Utilisateurs u = Utilisateurs.findById(id);
        renderJSON(u);
    }


    public static Boolean isNotSousmisRtt(Long id) {
        Boolean retour = false;
        if (id == null) {
            Utilisateurs currentUser = Application.getCurrentUserObject();
            id = currentUser.id;
        }
        if (id == -1) {
            Utilisateurs currentUser = Application.getCurrentUserObject();
            id = currentUser.id;
        }
        if (id != null) {
            Utilisateurs u = Utilisateurs.findById(id);
            if (u != null) {
                if (u.nonSoumisRtt) {
                    retour = true;
                }
            }
        }
        return retour;
    }


    public static void getManagerOf(Long id) {
        ErrorUtils retour = null;
        Utilisateurs manager = null;
        try {
            if (id != null) {
                Utilisateurs u = Utilisateurs.findById(id);
                if (u != null) {
                    manager = u.manager;
                    if (manager != null) {
                        renderJSON(manager.id);
                    } else {
                        renderJSON("-1");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            retour = ErrorUtils.createError(true, e.getMessage(), "erreur");
        }
    }

    public static void isManagerOf(Long id) {
        Boolean retour = false;
        Utilisateurs currentUser = Application.getCurrentUserObject();
        if (id != null) {
            Utilisateurs u = Utilisateurs.findById(id);
            if (u != null) {
                if (u.listmanager != null) {
                    if (u.listmanager.contains(currentUser)) {
                        retour = true;
                    }
                }
            }
        }
        renderJSON(retour);
    }


    public static void getMyNiveau() {
        /*Renvoi le nombre de niveau de l'utilisateur courant*/
        String retour = "1";
        Utilisateurs currentUser = Application.getCurrentUserObject();
        if (currentUser != null) {
            if (currentUser.niveauGestionEquipe != null) {
                retour = currentUser.niveauGestionEquipe;
            }
        }
        renderJSON(retour);

    }

}








