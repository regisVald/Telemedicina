package com.example.mediconnect.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.Date;

public class dbRol extends dbHelper{

    Context context;
    public dbRol(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarRol(String nombre, Integer estado, String fechaCreacion){

        long id = 0;
        try {
            dbHelper db = new dbHelper(context);
            SQLiteDatabase dbt = db.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("estado", estado);
            values.put("fechaCreacion",fechaCreacion);

             id = dbt.insert(TABLE_ROL,null, values);
        }catch (Exception ex){
            ex.toString();
        }

        return id;
    }
}
