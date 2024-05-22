package com.example.mediconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.mediconnect.adapter.PetAdapter;
import com.example.mediconnect.model.Pet;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class TestFirebase extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private FirebaseFirestore db;
    private RecyclerView recyclerView;
    private PetAdapter petAdapter;
    private List<Pet> petList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_firebase);

        db = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        petList = new ArrayList<>();
        petAdapter = new PetAdapter(petList);
        recyclerView.setAdapter(petAdapter);

        readData();
    }

    public void readData() {
        db.collection("pet")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            petList.clear();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Pet pet = document.toObject(Pet.class);
                                petList.add(pet);
                            }
                            petAdapter.notifyDataSetChanged();
                            Toast.makeText(TestFirebase.this, "Data read", Toast.LENGTH_SHORT).show();
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                            Toast.makeText(TestFirebase.this, "Error reading data", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
