package com.example.mediconnect;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mediconnect.db.dbHelper;

public class Login extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        // Obtiene referencias a los EditText para correo electrónico y contraseña
        emailEditText = findViewById(R.id.editTextTextEmailAddress);
        passwordEditText = findViewById(R.id.editTextTextPassword);


        // Añade un OnClickListener al botón de inicio de sesión
        Button loginButton = findViewById(R.id.button2);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtiene el correo electrónico y la contraseña ingresados por el usuario
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // Verifica las credenciales en la base de datos
                dbHelper dbHelper = new dbHelper(Login.this);
                if (dbHelper.checkUserCredentials(email, password)) {

                    String nombreUsuario = dbHelper.obtenerNombreUsuario(email);
                    // Si las credenciales son válidas, inicia la actividad Home2
                    Intent intent = new Intent(Login.this, Home.class);
                    intent.putExtra("nombreUsuario", nombreUsuario);
                    startActivity(intent);
                    finish(); // Finaliza la actividad de inicio de sesión para que el usuario no pueda volver atrás
                } else {
                    // Si las credenciales son inválidas, muestra un mensaje de error
                    Toast.makeText(Login.this, "Correo electrónico o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}