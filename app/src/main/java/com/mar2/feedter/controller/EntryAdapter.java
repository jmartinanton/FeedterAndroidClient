package com.mar2.feedter.controller;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jmart.feedter.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import com.mar2.feedter.model.EntryObject;

import com.mar2.feedter.networkactions.ImageGetFromUrl;

/**
 * Created by jmart on 08/05/2016.
 */
public class EntryAdapter extends ArrayAdapter<EntryObject> {

    ArrayList<EntryObject> entries;
    Gson gson = new Gson();
    public EntryAdapter(Context context, ArrayList<EntryObject> entries){
        super(context, R.layout.entrylistlayout, entries);
        this.entries = entries;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());

        EntryObject entry = entries.get(position);
        CharSequence titulo = Html.fromHtml(entry.getTitle());
        CharSequence contenido = Html.fromHtml(entry.getShortEntry());
        String urlImagen = entry.getPhotoLink();


        View item = inflater.inflate(R.layout.entrylistlayout, null);
        TextView lblTitulo = (TextView)item.findViewById(R.id.LblTitle);
        lblTitulo.setText(titulo);

        TextView lblSubtitulo = (TextView)item.findViewById(R.id.LblDescription);
        lblSubtitulo.setText(contenido);

        if (urlImagen != null){
            ImageGetFromUrl na = new ImageGetFromUrl();
            na.setUrl(urlImagen);
            ImageView imgPhoto = (ImageView)item.findViewById(R.id.ImgEntry);
            try {
                imgPhoto.setImageDrawable(na.execute().get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }

        return(item);
    }

}
