package com.example.mediconnect;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import java.util.List;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder> {

    private List<Doctor> doctorList;
    private Context context;

    public DoctorAdapter(Context context, List<Doctor> doctorList) {
        this.context = context;
        this.doctorList = doctorList;
    }

    @NonNull
    @Override
    public DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_doctor, parent, false);
        return new DoctorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorViewHolder holder, int position) {
        Doctor doctor = doctorList.get(position);
        holder.textViewDoctorName.setText(doctor.getNombres() + " " + doctor.getApellidos());
        holder.textViewDoctorSpecialty.setText(doctor.getEspecialidad());
        holder.textViewDoctorEmail.setText(doctor.getEmail());

        // Glide configuration with error handling
        Glide.with(holder.itemView.getContext())
                .load(doctor.getImageUrl())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.ic_user) // Imagen de marcador de posición
                        .error(R.drawable.ic_user) // Imagen de error
                        .diskCacheStrategy(DiskCacheStrategy.ALL)) // Estrategia de caché
                .into(holder.imageViewDoctor);

        // Manejar clics en los ítems
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DoctorProfileActivity.class);
            intent.putExtra("imageUrl", doctor.getImageUrl());
            intent.putExtra("name", doctor.getNombres() + " " + doctor.getApellidos());
            intent.putExtra("specialty", doctor.getEspecialidad());
            intent.putExtra("email", doctor.getEmail());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return doctorList.size();
    }

    public static class DoctorViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageViewDoctor;
        public TextView textViewDoctorName;
        public TextView textViewDoctorSpecialty;
        public TextView textViewDoctorEmail;

        public DoctorViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewDoctor = itemView.findViewById(R.id.imageViewDoctor);
            textViewDoctorName = itemView.findViewById(R.id.textViewDoctorName);
            textViewDoctorSpecialty = itemView.findViewById(R.id.textViewDoctorSpecialty);
            textViewDoctorEmail = itemView.findViewById(R.id.textViewDoctorEmail);
        }
    }
}
