package com.mar2.feedter.networkactions;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by jmart on 15/05/2016.
 */
public class ImageGetFromUrl extends AsyncTask<String, Integer, Drawable> {

    private String url;


    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    protected Drawable doInBackground(String... params) {
        InputStream URLcontent = null;
        try {
            URLcontent = (InputStream) new URL(url).getContent();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Drawable imagen = Drawable.createFromStream(URLcontent, "your source link");
        return imagen;
    }
}
