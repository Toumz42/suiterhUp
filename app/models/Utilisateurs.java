package models;

import controllers.Application;
import models.utils.Base64Crypt;
import models.utils.DateUtils;
import play.Logger;
import play.db.jpa.Model;

import javax.persistence.*;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: obal
 * Date: 26/05/14
 * Time: 12:59
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "UTILISATEURS")
public class Utilisateurs extends Model
{
    public String matricule;
    public String nom;
    public String prenom;
    public String email;
    public String login;
    public String motDePasse;
    public String telephone;
    public String fax;

    public Date dateEntree;
    public Date dateSortie;
    public Date dateNaissance;



}
