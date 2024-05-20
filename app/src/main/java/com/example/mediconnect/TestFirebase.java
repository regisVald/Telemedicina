package com.example.mediconnect;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import java.util.HashMap;
import java.util.Map;

public class TestFirebase extends AppCompatActivity {

    private static final String TAG = "TestFirebase";

    private Button btnAgregar;
    private EditText txtNombre, txtEdad, txtColor;
    private FirebaseFirestore mfirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_firebase);

        initializeFirebase();
        initializeUI();

        btnAgregar.setOnClickListener(this::onAddButtonClick);

        //prueba
    }

    private void initializeFirebase() {
        mfirestore = FirebaseFirestore.getInstance();
    }

    private void initializeUI() {
        btnAgregar = findViewById(R.id.btnAgregar);
        txtNombre = findViewById(R.id.txtNombre);
        txtEdad = findViewById(R.id.txtEdad);
        txtColor = findViewById(R.id.txtColor);
    }

    private void onAddButtonClick(View view) {
        String nombre = txtNombre.getText().toString().trim();
        String edad = txtEdad.getText().toString().trim();
        String color = txtColor.getText().toString().trim();

        if (isInputValid(nombre, edad, color)) {
            enviarDatos(nombre, edad, color);
        } else {
            Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isInputValid(String nombre, String edad, String color) {
        return !nombre.isEmpty() && !edad.isEmpty() && !color.isEmpty();
    }

    private void enviarDatos(String nombre, String edad, String color) {
        Map<String, Object> data = new HashMap<>();
        data.put("nombre", nombre);
        data.put("edad", edad);
        data.put("color", color);

        mfirestore.collection("pet")
                .add(data)
                .addOnSuccessListener(documentReference -> {
                    Log.d(TAG, "Documento creado con ID: " + documentReference.getId());
                    Toast.makeText(TestFirebase.this, "Creado Exitosamente", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Log.e(TAG, "Error al crear documento", e);
                    Toast.makeText(TestFirebase.this, "Error al ingresar", Toast.LENGTH_SHORT).show();
                });
    }
}
