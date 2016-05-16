package com.mar2.feedter.controller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mar2.feedter.model.classification.contentTypeObject;
import com.mar2.feedter.model.classification.GenericClassificationObject;
import com.mar2.feedter.networkactions.ServiceJsonGetter;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by jmart on 14/05/2016.
 */
public class FilterCreationController {

    private String ip = "192.168.0.39";
    protected Context context;
    public contentTypesSQLiteHelper contentTypesDbHelper;
    public SQLiteDatabase contentTypesDb;
    public ServiceJsonGetter josnGetter;

    public FilterCreationController(Context context){
        this.context = context.getApplicationContext();
    }

    public ArrayList<GenericClassificationObject> getFeeds() throws MalformedURLException {

        ArrayList<GenericClassificationObject> contentTypesList = new ArrayList<>();
        ArrayList<GenericClassificationObject> contentTypesListFromDb = new ArrayList<>();
        String url = "http://"+ip+":8080/ServiceComm/app/types";
        String params = "?type=contentType&parent=ninguno";
        String respuesta="no ha pasado nada";
        josnGetter = new ServiceJsonGetter();
        josnGetter.setDirecc(url+params);
        contentTypesList = getListFromRequest("contentType");
        contentTypesDbHelper = new contentTypesSQLiteHelper(context, "DBClassification", null,1);
        contentTypesDb = contentTypesDbHelper.getWritableDatabase();
        if (contentTypesDb != null){
            contentTypesDb.delete("ContentTypes",null,null);
            insertNewEntriesInDb(contentTypesList,"contentType");
            contentTypesListFromDb = getallEntriesFromDb("contentType");
            contentTypesDb.close();
        }
        return contentTypesListFromDb;
    }


    private ArrayList<GenericClassificationObject> getListFromRequest(String type){
        String serializedEntryList="";
        try {
            serializedEntryList = josnGetter.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        ArrayList<GenericClassificationObject> entriesList = new ArrayList<>();
        switch (type){
            case "contentType":
                entriesList = gson.fromJson(serializedEntryList, new TypeToken<List<contentTypeObject>>(){}.getType());
                break;
            case "categories":
                entriesList = gson.fromJson(serializedEntryList, new TypeToken<List<contentTypeObject>>(){}.getType());
                break;
            case "subcategories":
                entriesList = gson.fromJson(serializedEntryList, new TypeToken<List<contentTypeObject>>(){}.getType());
                break;
            default:

                break;
        }

        return entriesList;
    }

    private void insertNewEntriesInDb(ArrayList<GenericClassificationObject> entriesList, String type){
        switch (type){
            case "contentType":
                for ( GenericClassificationObject obj : entriesList) {
                    contentTypesDb.execSQL("INSERT INTO ContentTypes VALUES ('" + ((contentTypeObject)obj).getId() + "', '"
                            + ((contentTypeObject)obj).getTypeOfContent() + "')");
                }
                break;
            case "category":

                break;
            case "subcategory":

                break;
        }
    }

    private ArrayList<GenericClassificationObject> getallEntriesFromDb(String type){
        ArrayList<GenericClassificationObject> entriesListfromdb = new ArrayList<>();
        Cursor c;
        switch (type){
            case "contentType":
                c = contentTypesDb.rawQuery("SELECT * FROM ContentTypes",null);
                if (c.moveToFirst()) {
                    do {
                        entriesListfromdb.add(new contentTypeObject(c.getString(0),
                                c.getString(1)
                        ));
                    } while (c.moveToNext());
                }
                break;
            case "category":
                c = contentTypesDb.rawQuery("SELECT * FROM Categories",null);
                if (c.moveToFirst()) {
                    do {
                        entriesListfromdb.add(new contentTypeObject(c.getString(0),
                                c.getString(1)
                        ));
                    } while (c.moveToNext());
                }
                break;
            case "subcategory":
                c = contentTypesDb.rawQuery("SELECT * FROM Subcategories",null);
                if (c.moveToFirst()) {
                    do {
                        entriesListfromdb.add(new contentTypeObject(c.getString(0),
                                c.getString(1)
                        ));
                    } while (c.moveToNext());
                }
                break;

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
