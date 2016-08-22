package models.utils;

import play.Play;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: obal
 * Date: 26/11/15
 * Time: 12:20
 * To change this template use File | Settings | File Templates.
 */
public class UrlExecute {

    public static void execute(String urlStr) {
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


    public static String getJSON(String url, int timeout) {
        HttpURLConnection c = null;
        try {
            URL u = new URL(url);
            c = (HttpURLConnection) u.openConnection();
            c.setRequestMethod("GET");
            c.setRequestProperty("Content-length", "0");
            c.setUseCaches(false);
            c.setAllowUserInteraction(false);
            c.setConnectTimeout(timeout);
            c.setReadTimeout(timeout);
            c.connect();
            int status = c.getResponseCode();

            switch (status) {
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    br.close();
                    return sb.toString();
            }

        } catch (MalformedURLException ex) {
            /*Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);*/
        } catch (IOException ex) {
            /*Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);*/
        } finally {
            if (c != null) {
                try {
                    c.disconnect();
                } catch (Exception ex) {
                    /*Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);*/
                }
            }
        }
        return null;
    }
    public static String getJSONbyPost(String requestUrl, Map<String,Object> params) {

        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String,Object> param : params.entrySet()) {
            if (postData.length() != 0) postData.append('&');
            try {
                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }


        @SuppressWarnings("Since15") byte[] postDataBytes = postData.toString().getBytes( StandardCharsets.UTF_8 );
        HttpURLConnection conn = null;
        int status = 0;
        try {
            URL url = new URL( requestUrl );
            int postDataLength = postDataBytes.length;
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput( true );
            conn.setInstanceFollowRedirects( false );
            conn.setRequestMethod( "POST" );
            conn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty( "charset", "utf-8");
            conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
            conn.setUseCaches( false );

            DataOutputStream wr = new DataOutputStream( conn.getOutputStream());
            wr.write( postDataBytes );
            status = conn.getResponseCode();
            switch (status) {
                case 200:
                case 201:
                    Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                    StringBuilder sb = new StringBuilder();
                    for (int c; (c = in.read()) >= 0;)
                        sb.append((char)c);
                    return sb.toString();
            }

        }  catch (MalformedURLException ex) {
            /*Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);*/
        } catch (IOException ex) {
            /*Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);*/
        } finally {
            if (conn != null) {
                try {
                    conn.disconnect();
                } catch (Exception ex) {
                    /*Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);*/
                }
            }
        }
        return null;
    }

}