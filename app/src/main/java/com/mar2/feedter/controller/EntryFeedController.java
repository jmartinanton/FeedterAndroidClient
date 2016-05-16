package com.mar2.feedter.controller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mar2.feedter.model.EntryObject;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.mar2.feedter.networkactions.ServiceJsonGetter;

/**
 * Created by jmart on 14/05/2016.
 */
public class EntryFeedController {

    private String ip = "192.168.0.39";
    protected Context context;
    public EntriesSQLiteHelper entriesDbHelper;
    public SQLiteDatabase entriesDb;
    public ServiceJsonGetter actions;

    public EntryFeedController(Context context){
        this.context = context.getApplicationContext();
    }

    public ArrayList<EntryObject> getFeeds() throws MalformedURLException {

        ArrayList<EntryObject> entriesList = new ArrayList<>();
        ArrayList<EntryObject> entriesListfromdb = new ArrayList<>();
        String url = "http://"+ip+":8080/ServiceComm/app/entries";
        String params = "?user=martin&action=get&filters=fasdfasdf&date=fasfsdfa";
        String respuesta="no ha pasado nada";
        actions = new ServiceJsonGetter();
        actions.setDirecc(url+params);
        entriesList = getEntriesListFromRequest();
        entriesDbHelper = new EntriesSQLiteHelper(context, "DBEntries", null,1);
        entriesDb = entriesDbHelper.getWritableDatabase();
        if (entriesDb != null){
            entriesDb.delete("Entries",null,null);
            insertNewEntriesInDb(entriesList);
            entriesListfromdb = getallEntriesFromDb();
            entriesDb.close();
        }
        return entriesListfromdb;
    }


    private ArrayList<EntryObject> getEntriesListFromRequest(){
        String serializedEntryList="";
        try {
            serializedEntryList = actions.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        ArrayList<EntryObject> entriesList = gson.fromJson(serializedEntryList, new TypeToken<List<EntryObject>>(){}.getType());
        return entriesList;
    }

    private void insertNewEntriesInDb(ArrayList<EntryObject> entriesList){
        for (EntryObject entry : entriesList) {
            entriesDb.execSQL("INSERT INTO Entries VALUES ('" + entry.getId() + "', '"
                    + encodeHTML(entry.getLink()) + "', '"
                    + encodeHTML(entry.getTitle()) + "', '"
                    + encodeHTML(entry.getShortEntry()) + "', '"
                    + encodeHTML(entry.getPhotoLink()) + "', '"
                    + entry.getDateTime() + "')");
        }
    }

    private ArrayList<EntryObject> getallEntriesFromDb(){
        ArrayList<EntryObject> entriesListfromdb = new ArrayList<>();
        Cursor c = entriesDb.rawQuery("SELECT * FROM Entries",null);
        if (c.moveToFirst()) {
            do {
                entriesListfromdb.add(new EntryObject(c.getString(0),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3),
                        c.getString(4),
                        c.getString(5)
                ));
            } while (c.moveToNext());
        }
        return entriesListfromdb;
    }

    public static String encodeHTML(String s) {
        StringBuffer out = new StringBuffer();
        for(int i=0; i<s.length(); i++)
        {
            char c = s.charAt(i);
            if(c > 127 || c=='"'  || c=='\'' || c=='<' || c=='>')
            {
                out.append("&#"+(int)c+";");
            }
            else
            {
                out.append(c);
            }
        }
        return out.toString();
    }
}
