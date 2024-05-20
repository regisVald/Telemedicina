package com.example.mediconnect.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class MyDataSource{
    private SQLiteDatabase database;
    private dbHelper dbHelper;

    public MyDataSource(Context context) {
        dbHelper = new dbHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long insertUsuario(String usuario, String correo, String contrasenia, String fechaCreacion) {
        ContentValues values = new ContentValues();
        values.put("usuario", usuario);
        values.put("correo", correo);
        values.put("contrasenia", contrasenia);
        values.put("fechaCreacion", fechaCreacion);
        return database.insert("usuario", null, values);
    }
    public Cursor getAllUsuarios() {
        return database.query("usuario", null, null, null, null, null, null);
    }

}

