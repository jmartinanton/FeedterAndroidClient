package com.mar2.feedter.controller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jmart on 15/05/2016.
 */
public class contentTypesSQLiteHelper extends SQLiteOpenHelper {

    String sqlCreate = "CREATE TABLE ContentTypes (id TEXT, contentType TEXT)";
    public contentTypesSQLiteHelper(Context contexto, String nombre,
                                    SQLiteDatabase.CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS ContentTypes");
        db.execSQL(sqlCreate);
    }

}
