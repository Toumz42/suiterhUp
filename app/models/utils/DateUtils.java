package models.utils;

import models.jourferie;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Years;
import play.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created with IntelliJ IDEA.
 * User: obal
 * Date: 02/06/14
 * Time: 10:07
 * To change this template use File | Settings | File Templates.
 */
public class DateUtils {

    private static String	formatSortie	= "dd/MM/yyyy";
    private static String	formatSortieMySql	= "yyyy-MM-dd";
    private static String	formatSortie2	= "yyyy/MM/dd";
    private static String	formatEntree	= "yyyy-MM-dd hh:mm:ss";

    /**
     * Permet de transorfmer une Date en String dd/MM/yyyy
     *
     * @param d
     * @return String
     */
    public static String formatDateToString( Date d )
    {
        String ret = "";
        if (d!=null)
        {
            SimpleDateFormat sdf = new SimpleDateFormat( formatSortie );
            String dateStr = sdf.format( d );
            sdf = new SimpleDateFormat( formatSortie );
            try
            {
                Date parse = sdf.parse( dateStr );
                ret = sdf.format( parse );
            }
            catch ( ParseException e )
            {
                e.printStackTrace();
            }
        }
        return ret;
    }

    public static String formatDateToMySql( Date d )
    {
        String ret = "";
        if (d !=null)
        {
            SimpleDateFormat sdf = new SimpleDateFormat( formatSortieMySql );
            String dateStr = sdf.format( d );
            sdf = new SimpleDateFormat( formatSortieMySql );
            try
            {
                Date parse = sdf.parse( dateStr );
                ret = sdf.format( parse );
            }
            catch ( ParseException e )
            {
                e.printStackTrace();
            }
        }

        return ret;
    }

    /**
     * Permet de recuperer l'année courante
     *
     * @return int
     */
    public static int getAnneeCourante()
    {
        Date d = new Date();
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime( d );
        int y = gc.get( Calendar.YEAR );
        return y;
    }

    public static int getAnnee(Date d)
    {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime( d );
        int y = gc.get( Calendar.YEAR );
        return y;
    }

    public static Date parse(String dateString, SimpleDateFormat dateFormat)
    {
        if (dateString == null)
        {
            return null;
        }

        Date date;

        try
        {
            date = dateFormat.parse(dateString);
        }
        catch (ParseException e)
        {
            Logger.error(e.getMessage());
            date = null;
        }

        return date;
    }

    public static String formatDateToStringAAAAMMJJ( Date d )
    {
        String ret = "";
        SimpleDateFormat sdf = new SimpleDateFormat( formatSortie2 );
        String dateStr = sdf.format( d );
        sdf = new SimpleDateFormat( formatSortie2 );
        try
        {
            Date parse = sdf.parse( dateStr );
            ret = sdf.format( parse );
        }
        catch ( ParseException e )
        {
            e.printStackTrace();
        }

        return ret;
    }

    public static String formatDateToFormat( Date d, String formatSortie )
    {
        String ret = "";
        SimpleDateFormat sdf = new SimpleDateFormat( formatSortie );
        String dateStr = sdf.format( d );
        sdf = new SimpleDateFormat( formatSortie );
        try
        {
            Date parse = sdf.parse( dateStr );
            ret = sdf.format( parse );
        }
        catch ( ParseException e )
        {
            e.printStackTrace();
        }

        return ret;
    }

    public static int dateDiffJours(Date dateDebut, Date dateFin)
    {
        int diff = 0;
        diff = Days.daysBetween(new DateTime(dateDebut), new DateTime(dateFin)).getDays();
        return diff;
    }

    public static Integer dateDiffMois(Date dateDebut, Date dateFin) {
        int diff = 0;
        diff = Years.yearsBetween(new DateTime(dateDebut), new DateTime(dateFin)).getYears();
        return diff;
    }

    public static int getNbJoursMois(int mois, int annee)
    {
        Calendar premierJourPeriode2 = GregorianCalendar.getInstance();
        premierJourPeriode2.set(annee,mois-1, 1);
        premierJourPeriode2.add(Calendar.MONTH, 1);
        premierJourPeriode2.add(Calendar.DAY_OF_YEAR,-1);
        Integer nbJours = premierJourPeriode2.get(Calendar.DAY_OF_MONTH);
        return nbJours;
    }

    public static Double dateDiffJoursOuvre(Date dateDebut, Date dateFin,String debutAmpm,String finAmpm )
    {
        Double nbJours = Double.valueOf(0);
        Calendar dateValeur = GregorianCalendar.getInstance();
        dateValeur.setTime(new Date());
        Calendar premierJourPeriode = GregorianCalendar.getInstance();
        premierJourPeriode.set(dateValeur.get(Calendar.YEAR) ,dateValeur.get(Calendar.MONTH), 1);
        Calendar debut = GregorianCalendar.getInstance();
        Calendar jourActuel = GregorianCalendar.getInstance();
        Calendar fin = GregorianCalendar.getInstance();
        debut.setTime(dateDebut);
        jourActuel.setTime(dateDebut);
        fin.setTime(dateFin);
        while(jourActuel.getTimeInMillis()<=fin.getTimeInMillis())
        {
            if (!jourferie.isJourFerie(jourActuel.getTime()))
            {
                if (jourActuel.getTimeInMillis()>=premierJourPeriode.getTimeInMillis())
                {
                    if (DateUtils.formatDateToMySql(fin.getTime()).equals(DateUtils.formatDateToMySql(debut.getTime())))
                    {
                        if (jourActuel.get(Calendar.DAY_OF_WEEK) != 0 && jourActuel.get(Calendar.DAY_OF_WEEK) != 7 )
                        {
                            if (debutAmpm.equals(finAmpm))
                            {
                                nbJours += Double.parseDouble("0.5");
                            }
                            else
                            {
                                nbJours += Double.parseDouble("1");
                            }
                        }
                    }
                    else if (DateUtils.formatDateToMySql(jourActuel.getTime()).equals(DateUtils.formatDateToMySql(debut.getTime())))
                    {
                        if (jourActuel.get(Calendar.DAY_OF_WEEK) != 0 && jourActuel.get(Calendar.DAY_OF_WEEK) != 7 )
                        {
                            if (debutAmpm.equals("pm"))
                            {
                                nbJours += Double.parseDouble("0.5");
                            }
                            else
                            {
                                nbJours += Double.parseDouble("1");
                            }
                        }

                    }
                    else if (DateUtils.formatDateToMySql(jourActuel.getTime()).equals(DateUtils.formatDateToMySql(fin.getTime())))
                    {
                        if (jourActuel.get(Calendar.DAY_OF_WEEK) != 0 && jourActuel.get(Calendar.DAY_OF_WEEK) != 7 )
                        {
                            if (finAmpm.equals("am"))
                            {
                                nbJours += Double.parseDouble("0.5");
                            }
                            else
                            {
                                nbJours += Double.parseDouble("1");
                            }
                        }
                    }
                    else
                    {
                        if (jourActuel.get(Calendar.DAY_OF_WEEK) != 0 && jourActuel.get(Calendar.DAY_OF_WEEK) != 7 )
                        {
                            nbJours += Double.parseDouble("1");
                        }
                    }
                }
            }
            jourActuel.add(Calendar.DAY_OF_YEAR,1);
        }
        return nbJours;
    }


}
