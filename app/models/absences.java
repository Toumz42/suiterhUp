package models;

import controllers.Application;
import models.utils.DateUtils;
import models.utils.UrlExecute;
import play.db.jpa.Model;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: obal
 * Date: 04/06/15
 * Time: 11:54
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "ABSENCES")
public class Absences extends Model {
    @OneToOne
    public Utilisateurs salarieAbsence;

    public Date dateDebut;
    public String debutAmpm;
    public Date dateFin;
    public String finAmpm;


    public Double nombreHeures;
    public boolean transmis;


    @Column(name = "commentaires", length = 65535)
    public String commentaires;

    @Column(name = "motifRefus", length = 65535)
    public String motifRefus;

    public boolean isActif;
    public boolean envoiMailCreate;

    @OneToOne
    public Utilisateurs creePar;
    public Date creeLe;
    @OneToOne
    public Utilisateurs modifiePar;
    public Date modifieLe;

}
