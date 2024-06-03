package com.example.mediconnect;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.SearchView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class DoctorActivity extends AppCompatActivity {

    private RecyclerView recyclerViewDoctors;
    private DoctorAdapter doctorsAdapter;
    private List<Doctor> doctorList;
    private List<Doctor> filteredDoctorList;
    private DatabaseReference databaseReference;
    private SearchView searchView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        recyclerViewDoctors = findViewById(R.id.recyclerViewDoctors);
        searchView = findViewById(R.id.searchView);

        recyclerViewDoctors.setLayoutManager(new LinearLayoutManager(this));
        doctorList = new ArrayList<>();
        filteredDoctorList = new ArrayList<>();
        doctorsAdapter = new DoctorAdapter(this, filteredDoctorList);
        recyclerViewDoctors.setAdapter(doctorsAdapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("Doctors");

        // Cargar los doctores de Firebase
        loadDoctorsFromFirebase();

        // Configurar el SearchView
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterDoctorsBySpecialty(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterDoctorsBySpecialty(newText);
                return false;
            }
        });
    }

    private void loadDoctorsFromFirebase() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@Nullable DataSnapshot dataSnapshot) {
                doctorList.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Doctor doctor = postSnapshot.getValue(Doctor.class);
                    doctorList.add(doctor);
                }
                // Inicialmente mostrar todos los doctores
                filteredDoctorList.clear();
                filteredDoctorList.addAll(doctorList);
                doctorsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@Nullable DatabaseError databaseError) {
                // Manejo de errores
            }
        });
    }

    private void filterDoctorsBySpecialty(String query) {
        filteredDoctorList.clear();
        if (TextUtils.isEmpty(query)) {
            filteredDoctorList.addAll(doctorList);
        } else {
            for (Doctor doctor : doctorList) {
                if (doctor.getEspecialidad().toLowerCase().contains(query.toLowerCase())) {
                    filteredDoctorList.add(doctor);
                }
            }
        }
        doctorsAdapter.notifyDataSetChanged();
    }
}
