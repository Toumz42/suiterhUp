package controllers;

import models.Utilisateurs;
import play.mvc.Controller;

import java.util.List;

/**
 * Created by Obal on 26/05/2016.
 */
public class UtilisateursCtrl extends Controller {

    public static void getAll()
    {
        List<Utilisateurs> list = Utilisateurs.findAll();
        renderJSON(list);
    }

}
