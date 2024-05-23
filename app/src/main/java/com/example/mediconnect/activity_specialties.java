package com.example.mediconnect;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class activity_specialties extends AppCompatActivity {

    private RecyclerView specialtiesRecyclerView;
    private SpecialtiesAdapter specialtiesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialties);

        specialtiesRecyclerView = findViewById(R.id.specialtiesRecyclerView);
        specialtiesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Dummy data for demonstration
        List<Specialty> specialties = Arrays.asList(
                new Specialty("Cardiología", R.drawable.ic_heart),
                new Specialty("Dermatología", R.drawable.ic_dermatology),
                new Specialty("Pediatría", R.drawable.ic_pediatrics),
                new Specialty("Neurología", R.drawable.ic_neurology)
        );

        specialtiesAdapter = new SpecialtiesAdapter(specialties);
        specialtiesRecyclerView.setAdapter(specialtiesAdapter);
    }
}
