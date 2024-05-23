package com.example.mediconnect;

// DoctorsAdapter.java
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder> {

    private Context context;
    private List<Doctor> doctorsList;

    public DoctorAdapter(Context context, List<Doctor> doctorsList) {
        this.context = context;
        this.doctorsList = doctorsList;
    }

    @NonNull
    @Override
    public DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_doctor, parent, false);
        return new DoctorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorViewHolder holder, int position) {
        Doctor doctor = doctorsList.get(position);
        holder.doctorName.setText(doctor.getName());
        holder.doctorSpecialty.setText(doctor.getSpecialty());
        holder.doctorImage.setImageResource(doctor.getImageResId());
    }

    @Override
    public int getItemCount() {
        return doctorsList.size();
    }

    public static class DoctorViewHolder extends RecyclerView.ViewHolder {

        ImageView doctorImage;
        TextView doctorName;
        TextView doctorSpecialty;

        public DoctorViewHolder(@NonNull View itemView) {
            super(itemView);
            doctorImage = itemView.findViewById(R.id.doctorImage);
            doctorName = itemView.findViewById(R.id.doctorName);
            doctorSpecialty = itemView.findViewById(R.id.doctorSpecialty);
        }
    }
}





