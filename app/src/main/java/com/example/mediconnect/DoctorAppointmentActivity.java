package com.example.mediconnect;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DoctorAppointmentActivity extends AppCompatActivity {

    private TextView textViewDoctorName;
    private EditText editTextPatientName;
    private EditText editTextDate;
    private EditText editTextTime;
    private Button buttonSubmitAppointment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_appointment);

        textViewDoctorName = findViewById(R.id.textViewDoctorName);
        editTextPatientName = findViewById(R.id.editTextPatientName);
        editTextDate = findViewById(R.id.editTextDate);
        editTextTime = findViewById(R.id.editTextTime);
        buttonSubmitAppointment = findViewById(R.id.buttonSubmitAppointment);

        // Obtener los datos del intent
        String doctorName = getIntent().getStringExtra("doctorName");
        textViewDoctorName.setText(doctorName);

        buttonSubmitAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String patientName = editTextPatientName.getText().toString();
                String date = editTextDate.getText().toString();
                String time = editTextTime.getText().toString();

                // Aquí puedes guardar la cita en la base de datos o realizar la acción deseada
            }
        });
    }
}
