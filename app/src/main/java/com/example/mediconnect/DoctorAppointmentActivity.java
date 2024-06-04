package com.example.mediconnect;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class DoctorAppointmentActivity extends AppCompatActivity {

    private TextView textViewDoctorName;
    private TextView textViewSpecialty;
    private TextView textViewSelectedDate;
    private Button buttonSelectDate;
    private Button buttonCreateAppointment;
    private String selectedDate = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_appointment);

        textViewDoctorName = findViewById(R.id.textViewDoctorName);
        textViewSpecialty = findViewById(R.id.textViewSpecialty);
        textViewSelectedDate = findViewById(R.id.textViewSelectedDate);
        buttonSelectDate = findViewById(R.id.buttonSelectDate);
        buttonCreateAppointment = findViewById(R.id.buttonCreateAppointment);

        // Obtener datos del intent
        String doctorName = getIntent().getStringExtra("doctorName");
        String specialty = getIntent().getStringExtra("specialty");

        // Configurar la UI con los datos del doctor
        textViewDoctorName.setText(doctorName);
        textViewSpecialty.setText(specialty);

        // Manejar la selección de fecha
        buttonSelectDate.setOnClickListener(v -> showDatePickerDialog());

        // Manejar el clic en el botón para crear la cita
        buttonCreateAppointment.setOnClickListener(v -> {
            if (!selectedDate.isEmpty()) {
                createAppointment(doctorName, specialty, selectedDate);
            } else {
                Toast.makeText(DoctorAppointmentActivity.this, "Por favor seleccione una fecha", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year1, month1, dayOfMonth) -> {
            selectedDate = dayOfMonth + "/" + (month1 + 1) + "/" + year1;
            textViewSelectedDate.setText(selectedDate);
        }, year, month, day);

        datePickerDialog.show();
    }

    private void createAppointment(String doctorName, String specialty, String date) {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("appointments");

            String appointmentId = databaseReference.push().getKey();
            String meetLink = "https://meet.google.com/your-fixed-link"; // Enlace fijo de Google Meet
            Map<String, String> appointment = new HashMap<>();
            appointment.put("doctorName", doctorName);
            appointment.put("specialty", specialty);
            appointment.put("date", date);
            appointment.put("meetLink", meetLink);
            appointment.put("userId", currentUser.getUid());

            if (appointmentId != null) {
                databaseReference.child(appointmentId).setValue(appointment)
                        .addOnSuccessListener(aVoid -> {
                            Toast.makeText(DoctorAppointmentActivity.this, "Cita creada perfectamente", Toast.LENGTH_SHORT).show();
                            Intent homeIntent = new Intent(DoctorAppointmentActivity.this, Home.class);
                            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(homeIntent);
                            finish();
                        })
                        .addOnFailureListener(e -> Toast.makeText(DoctorAppointmentActivity.this, "Error al crear la cita", Toast.LENGTH_SHORT).show());
            }
        }
    }
}
