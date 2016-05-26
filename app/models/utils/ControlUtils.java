package models.utils;

/**
 * Created with IntelliJ IDEA.
 * User: obal
 * Date: 21/12/15
 * Time: 10:34
 * To change this template use File | Settings | File Templates.
 */
public class ControlUtils {
    public static boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }

    public static boolean isInteger(String str)
    {
        try
        {
            Integer d = Integer.valueOf(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }

    public static String formatPrenom(String prenom)
    {
        try
        {
            String retour = "";
            retour = prenom.substring(0,1).toUpperCase()  + prenom.substring(1,prenom.length()).toLowerCase();
            return retour;
        }
        catch ( Exception e )
        {
            return prenom;
        }
    }
}
