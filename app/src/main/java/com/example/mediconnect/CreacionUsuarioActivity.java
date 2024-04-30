package com.example.mediconnect;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mediconnect.db.MyDataSource;

import java.util.ArrayList;
import java.util.List;

public class CreacionUsuarioActivity extends AppCompatActivity {
    private MyDataSource dataSource;
    private EditText editTextUsuario, editTextCorreo, editTextContrasenia;
    private Button buttonCrearUsuario;
    private TextView textViewUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creacion_usuario);

        // Inicializar la fuente de datos
        dataSource = new MyDataSource(this);
        dataSource.open();

        // Enlazar vistas
        editTextUsuario = findViewById(R.id.txtUsuario);
        editTextCorreo = findViewById(R.id.txtEmail);
        editTextContrasenia = findViewById(R.id.txtPassword);
        buttonCrearUsuario = findViewById(R.id.btnRegistrase);
        Button btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreacionUsuarioActivity.this, Login.class);
                startActivity(intent);
            }
        });

        // Configurar el botón para crear usuario


        buttonCrearUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener los valores ingresados por el usuario
                String usuario = editTextUsuario.getText().toString().trim();
                String correo = editTextCorreo.getText().toString().trim();
                String contrasenia = editTextContrasenia.getText().toString().trim();

                // Insertar el nuevo usuario en la base de datos
                long id = dataSource.insertUsuario(usuario, correo, contrasenia, getCurrentDateTime());

                if (id != -1) {
                    Toast.makeText(CreacionUsuarioActivity.this, "Usuario creado con éxito", Toast.LENGTH_SHORT).show();
//                    // Mostrar el usuario creado en el TextView
//                    String usuariosAnteriores = textViewUsuarios.getText().toString();
//                    String usuariosActualizados = usuariosAnteriores + "\n" + usuario;
//                    textViewUsuarios.setText(usuariosActualizados);
//                    // Limpiar los campos después de la creación exitosa
//                    editTextUsuario.setText("");
//                    editTextCorreo.setText("");
//                    editTextContrasenia.setText("");
                } else {
                    Toast.makeText(CreacionUsuarioActivity.this, "Error al crear el usuario", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Cerrar la conexión con la base de datos al destruir la actividad
        dataSource.close();
    }

    // Método para obtener la fecha y hora actual como texto
    private String getCurrentDateTime() {
        // Aquí puedes implementar la lógica para obtener la fecha y hora actual
        // Por simplicidad, solo devolveremos una cadena de texto estática
        return "2024-04-29 12:00:00";
    }
}
