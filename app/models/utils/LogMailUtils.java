package models.utils;

import play.Play;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: obal
 * Date: 04/12/15
 * Time: 09:45
 * To change this template use File | Settings | File Templates.
 */
public class LogMailUtils {
    public static void logMail(String message)
    {
        String fullPath = Play.configuration.get("import.path.log").toString();

        File f = new File(fullPath);
        f.mkdir();

        if ( f.exists() )
        {
            try
            {
                /*Init chemin et nom de fichier*/
                String filePath = Play.configuration.get("import.path.log").toString();
                String nameExport = "logMail.txt";

                PrintWriter log = null;
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_hhmmss");
                String ddj = sdf.format(date);

                /*Vérifiaction de l'existance du répertoire*/
                File fl = new File(filePath);
                fl.mkdir();

                if ( fl.exists() )
                {
                    /*Ouverture du fichier en append*/
                    FileWriter fw = new FileWriter(filePath + "/" + nameExport, true);
                    /*Ecriture du message et d'un saut de ligne*/
                    fw.write(ddj + ";" + message);
                    fw.write(System.getProperty("line.separator"));
                    /*Fermeture du fichier*/
                    if ( fw != null )
                    {
                        fw.close();
                    }
                }
            }

            catch ( Exception e )
            {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }
}
