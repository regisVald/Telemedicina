package com.example.mediconnect.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class dbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Mediconnet";
    private static final int DATABASE_VERSION = 1;

    public dbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public boolean checkUserCredentials(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = { "correo" };
        String selection = "correo = ? AND contrasenia = ?";
        String[] selectionArgs = { email, password };
        Cursor cursor = db.query("usuario", columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        return count > 0;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear la tabla "usuario" con las columnas especificadas
        String createTableQuery = "CREATE TABLE usuario ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "usuario TEXT, "
                + "correo TEXT, "
                + "contrasenia TEXT, "
                + "fechaCreacion TEXT)";
        db.execSQL(createTableQuery);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Actualizar la base de datos si es necesario
        db.execSQL("DROP TABLE IF EXISTS usuario");
        onCreate(db);
    }

    public String obtenerNombreUsuario(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String nombreUsuario = "";

        String[] columns = { "usuario" };
        String selection = "correo = ?";
        String[] selectionArgs = { email };

        Cursor cursor = db.query("usuario", columns, selection, selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndex("usuario");
            if (columnIndex != -1) {
                nombreUsuario = cursor.getString(columnIndex);
            } else {
                // Columna no encontrada en el cursor
                Log.e("DatabaseHelper", "Columna 'usuario' no encontrada en el cursor");
            }
        } else {
            // No se encontraron filas en el cursor
            Log.e("DatabaseHelper", "No se encontraron filas en el cursor para el correo electrónico: " + email);
        }

        cursor.close();
        db.close();

        return nombreUsuario;
    }
}
