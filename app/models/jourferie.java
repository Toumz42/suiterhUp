package models;

import models.utils.DateUtils;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: obal
 * Date: 28/07/14
 * Time: 17:34
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "JOURFERIE")
public class jourferie extends Model
{

    public Date dateFerie;

    public String libelle;

    public boolean isActif;

    @OneToOne
    public Utilisateurs creePar;
    public Date creeLe;
    @OneToOne
    public Utilisateurs modifiePar;
    public Date         modifieLe;

    public static Boolean isJourFerie(Date d)
    {
        jourferie jf = jourferie.find("Select jf From jourferie jf Where dateFerie = '" + DateUtils.formatDateToMySql(d) +"'").first();
        return jf != null;
    }

}
