package com.example.mediconnect;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
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
    private EditText nameEditText, specialtyEditText, emailEditText, phoneNumberEditText;
    private Button addButton;
    private RecyclerView doctorRecyclerView;
    private DoctorAdapter doctorAdapter;
    private List<Doctor> doctorList = new ArrayList<>();
    private DatabaseReference databaseReference;
    private Doctor selectedDoctor; // Almacena el doctor seleccionado para edición

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        nameEditText = findViewById(R.id.nameEditText);
        specialtyEditText = findViewById(R.id.specialtyEditText);
        emailEditText = findViewById(R.id.emailEditText);
        phoneNumberEditText = findViewById(R.id.phoneNumberEditText);
        addButton = findViewById(R.id.addButton);
        doctorRecyclerView = findViewById(R.id.doctorRecyclerView);

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Doctor.class.getSimpleName());

        doctorAdapter = new DoctorAdapter(doctorList, new DoctorAdapter.OnEditButtonClickListener() {
            @Override
            public void onEditButtonClick(Doctor doctor) {
                loadDoctorForEdit(doctor);
            }
        }, new DoctorAdapter.OnDeleteButtonClickListener() {
            @Override
            public void onDeleteButtonClick(Doctor doctor) {
                deleteDoctor(doctor);
            }
        });
        doctorRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        doctorRecyclerView.setAdapter(doctorAdapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedDoctor == null) {
                    addDoctor();
                } else {
                    updateDoctor();
                }
            }
        });

        loadDoctors();
    }

    private void addDoctor() {
        String name = nameEditText.getText().toString();
        String specialty = specialtyEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String phoneNumber = phoneNumberEditText.getText().toString();

        String id = databaseReference.push().getKey();
        if (id != null) {
            Doctor doctor = new Doctor(id, name, specialty, email, phoneNumber);
            databaseReference.child(id).setValue(doctor);
            clearFields();
        }
    }

    private void updateDoctor() {
        String name = nameEditText.getText().toString();
        String specialty = specialtyEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String phoneNumber = phoneNumberEditText.getText().toString();

        selectedDoctor.setName(name);
        selectedDoctor.setSpecialty(specialty);
        selectedDoctor.setEmail(email);
        selectedDoctor.setPhoneNumber(phoneNumber);

        databaseReference.child(selectedDoctor.getId()).setValue(selectedDoctor);
        selectedDoctor = null;
        addButton.setText("Agregar Doctor");
        clearFields();
    }

    private void loadDoctorForEdit(Doctor doctor) {
        selectedDoctor = doctor;
        nameEditText.setText(doctor.getName());
        specialtyEditText.setText(doctor.getSpecialty());
        emailEditText.setText(doctor.getEmail());
        phoneNumberEditText.setText(doctor.getPhoneNumber());
        addButton.setText("Actualizar Doctor");
    }

    private void deleteDoctor(Doctor doctor) {
        databaseReference.child(doctor.getId()).removeValue();
        clearFields();
    }

    private void clearFields() {
        nameEditText.setText("");
        specialtyEditText.setText("");
        emailEditText.setText("");
        phoneNumberEditText.setText("");
    }

    private void loadDoctors() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                doctorList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Doctor doctor = snapshot.getValue(Doctor.class);
                    doctorList.add(doctor);
                }
                doctorAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle possible errors
            }
        });
    }
}



