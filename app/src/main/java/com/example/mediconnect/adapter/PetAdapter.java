package com.example.mediconnect.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mediconnect.R;
import com.example.mediconnect.model.Pet;

import java.util.List;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.PetViewHolder> {

    private List<Pet> petList;

    public PetAdapter(List<Pet> petList) {
        this.petList = petList;
    }

    @NonNull
    @Override
    public PetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_pet_single, parent, false);
        return new PetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PetViewHolder holder, int position) {
        Pet pet = petList.get(position);
        holder.nombre.setText(pet.getNombre());
        holder.edad.setText(pet.getEdad());
        holder.color.setText(pet.getColor());
    }

    @Override
    public int getItemCount() {
        return petList.size();
    }

    public static class PetViewHolder extends RecyclerView.ViewHolder {
        public TextView nombre;
        public TextView edad;
        public TextView color;

        public PetViewHolder(View view) {
            super(view);
            nombre = view.findViewById(R.id.lblNombre);
            edad = view.findViewById(R.id.lblEdad);
            color = view.findViewById(R.id.lblColor);
        }
    }
}
