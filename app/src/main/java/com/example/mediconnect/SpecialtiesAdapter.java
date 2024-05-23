package com.example.mediconnect;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SpecialtiesAdapter extends RecyclerView.Adapter<SpecialtiesAdapter.SpecialtyViewHolder> {

    private List<Specialty> specialties;

    public SpecialtiesAdapter(List<Specialty> specialties) {
        this.specialties = specialties;
    }

    @NonNull
    @Override
    public SpecialtyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_specialty, parent, false);
        return new SpecialtyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpecialtyViewHolder holder, int position) {
        Specialty specialty = specialties.get(position);
        holder.specialtyName.setText(specialty.getName());
        holder.specialtyIcon.setImageResource(specialty.getIconResId());
    }

    @Override
    public int getItemCount() {
        return specialties.size();
    }

    public static class SpecialtyViewHolder extends RecyclerView.ViewHolder {
        public TextView specialtyName;
        public ImageView specialtyIcon;

        public SpecialtyViewHolder(View itemView) {
            super(itemView);
            specialtyName = itemView.findViewById(R.id.specialtyName);
            specialtyIcon = itemView.findViewById(R.id.specialtyIcon);
        }
    }
}
