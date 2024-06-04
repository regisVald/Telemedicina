package com.example.mediconnect;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class DoctorProfileActivity extends AppCompatActivity {

    private ImageView doctorProfileImage;
    private TextView doctorProfileName;
    private TextView doctorProfileSpecialty;
    private TextView doctorProfileEmail;
    private Button buttonMakeAppointment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_profile);

        doctorProfileImage = findViewById(R.id.doctorProfileImage);
        doctorProfileName = findViewById(R.id.doctorProfileName);
        doctorProfileSpecialty = findViewById(R.id.doctorProfileSpecialty);
        doctorProfileEmail = findViewById(R.id.doctorProfileEmail);
        buttonMakeAppointment = findViewById(R.id.buttonMakeAppointment);

        // Obtener datos del intent
        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra("imageUrl");
        String name = intent.getStringExtra("name");
        String specialty = intent.getStringExtra("specialty");
        String email = intent.getStringExtra("email");

        // Configurar la UI con los datos del doctor
        Glide.with(this)
                .load(imageUrl)
                .apply(new RequestOptions().placeholder(R.drawable.user_icon).error(R.drawable.user_icon))
                .into(doctorProfileImage);

        doctorProfileName.setText(name);
        doctorProfileSpecialty.setText(specialty);
        doctorProfileEmail.setText(email);

        // Manejar el clic en el botón para hacer cita
        buttonMakeAppointment.setOnClickListener(v -> {
            Intent appointmentIntent = new Intent(DoctorProfileActivity.this, DoctorAppointmentActivity.class);
            appointmentIntent.putExtra("doctorName", name);
            appointmentIntent.putExtra("specialty", specialty);
            startActivity(appointmentIntent);
        });
    }
}
