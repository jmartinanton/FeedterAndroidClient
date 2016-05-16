package com.mar2.feedter.networkactions;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.CheckBox;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutionException;


/**
 * Created by jmart on 06/05/2016.
 */
public class NetworkActions extends AsyncTask<String, Integer, String> {

    private String filters = "";
    private String user = "";
    private String action;
    private String direcc;

    public String getDirecc() {
        return direcc;
    }

    public void setDirecc(String direcc) {
        this.direcc = direcc;
    }



    @Override
    protected String doInBackground(String... params) {
        Log.d("<--NETWORKACTIONS -->", direcc);
        String resul = "";
            URL url = null;
            try {
                url = new URL(direcc);

            } catch (MalformedURLException e) {
                e.printStackTrace();
                resul = "pifia";
            }
            try {
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();
                InputStream in = urlConnection.getInputStream();
                int bytecharacter;
                while ((bytecharacter = in.read()) != -1){
                    resul += (char) bytecharacter;
                }

               /*
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                // Acciones a realizar con el flujo de datos
                resul = inputStreamToString(in);
                */
                //Log.d("<--NETWORKACTIONS -->", resul);
                return resul;


            } catch (Exception e) {
                Log.d("<--NETWORKACTIONS -->", "PIFIA!");
                e.printStackTrace();
                resul = "pifia";
            }
        return resul;
    }

    protected void onPostExecute(String response) {
        if(response == null) {
            response = "THERE WAS AN ERROR";
        }
        Log.i("INFO", response);
    }


    private String inputStreamToString(InputStream in) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(in));
        StringBuilder total = new StringBuilder();
        String line;
        while ((line = r.readLine()) != null) {
            total.append(line).append('\n');
        }
        return total.toString();
    }

}
