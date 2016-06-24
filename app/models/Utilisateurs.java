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
public class Utilisateurs extends Model{

    public String nom;
    public String prenom;
    public String email;
    public String login;
    public String motDePasse;
    public String telephone;
    public String fax;
    public String categorieCotisant;
    public String classification;
    public String libelleEmploi;
    public String matriculeManagerImport;
    public String niveauGestionEquipe;

    public Date dateEntree;
    public Date dateSortie;
    public Date dateNaissance;


    public Boolean noMajManN1;
    @OneToOne(cascade = CascadeType.ALL)
    public Utilisateurs	managerN2;

    @OneToOne(cascade = CascadeType.ALL)
    public Utilisateurs	manager;

    public Boolean noMajManN2;
    @ManyToMany(cascade = CascadeType.ALL)
    public List<Utilisateurs>   listmanager;



    public boolean nonSoumisRtt;
    public Boolean isActif;



    @OneToOne
    public Utilisateurs creePar;
    public Date creeLe;

    @OneToOne
    public Utilisateurs modifiePar;
    public Date         modifieLe;

    public Boolean      vip;

    public Boolean accesMobile;
    public Boolean activeMobile;
    public Integer compteurEssai;
    public String dateExpirationMdp;




}
