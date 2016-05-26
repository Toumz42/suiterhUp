package models.utils;

import play.Play;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created with IntelliJ IDEA.
 * User: obal
 * Date: 26/11/15
 * Time: 12:20
 * To change this template use File | Settings | File Templates.
 */
public class UrlExecute {

    public static void execute(String urlStr)
    {
        final URL url;
        try {
            url = new URL(urlStr);
            final URLConnection urlConnection = url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            urlConnection.connect();
            final OutputStream outputStream = urlConnection.getOutputStream();
            String stringData = "";
            outputStream.write(("{\"fNamn\": \"" + stringData + "\"}").getBytes("UTF-8"));
            outputStream.flush();
            final InputStream inputStream = urlConnection.getInputStream();
        } catch (MalformedURLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

}
