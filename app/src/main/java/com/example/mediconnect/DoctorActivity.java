package com.example.mediconnect;

//DoctorsActivity.java
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class DoctorActivity extends AppCompatActivity {

    private RecyclerView doctorsRecyclerView;
    private DoctorAdapter doctorsAdapter;
    private List<Doctor> doctorsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        doctorsRecyclerView = findViewById(R.id.doctorsRecyclerView);
        doctorsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        doctorsList = new ArrayList<>();
        doctorsList.add(new Doctor("Dr. Juan Perez", "Cardiología", R.drawable.ic_user));
        doctorsList.add(new Doctor("Dr. Ana Gomez", "Pediatría", R.drawable.ic_user));
        doctorsList.add(new Doctor("Dr. Carlos Martínez", "Neurología", R.drawable.ic_user));
        doctorsList.add(new Doctor("Dra. Laura Sánchez", "Dermatología", R.drawable.ic_user));

        doctorsAdapter = new DoctorAdapter(this, doctorsList);
        doctorsRecyclerView.setAdapter(doctorsAdapter);
    }
}





