package com.mar2.feedter.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jmart.feedter.R;

import java.net.MalformedURLException;
import java.util.ArrayList;

import com.mar2.feedter.controller.EntryAdapter;
import com.mar2.feedter.controller.EntryFeedController;
import com.mar2.feedter.model.EntryObject;

public class EntryFeed extends AppCompatActivity {

    private ListView entryList;
    private ArrayList<EntryObject> entradas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_feed);
        EntryFeedController ef = new EntryFeedController(this.getApplicationContext());

/*
        EntryObjectCreator reader = EntryObjectCreator.getInstance();
        try {
            reader.setURL(new URL("http://feeds.weblogssl.com/xataka2"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        */
        try {
            //ef.getFeeds();
            entradas = ef.getFeeds();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        entryList = (ListView) findViewById(R.id.entryListView);
        EntryAdapter entryListViewAdapter = new EntryAdapter(this,entradas);
        // Añade un onClickListener si la vista no es null
        entryList.setAdapter(entryListViewAdapter);

        entryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EntryObject selectedEntry = (EntryObject) parent.getItemAtPosition(position);
                String titulo = selectedEntry.getTitle();
                String contenido = selectedEntry.getShortEntry();
                String imagen = selectedEntry.getPhotoLink();
                Toast.makeText (view.getContext(), "Item in position " + position + " clicked", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(view.getContext(), EntryItemView.class);
                intent.putExtra("title", titulo );
                intent.putExtra("description", contenido );
                intent.putExtra("image", imagen);
                view.getContext().startActivity(intent);
            }
        });


                /*
                setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Log.d("<--- EntryAdapter --->", "hola");
                    Intent intent = new Intent(v.getContext(), EntryItemView.class);
                    intent.putExtra("title", titulo );
                    intent.putExtra("description", contenido );
                    v.getContext().startActivity(intent);
                }
            });
*/



    }

}
