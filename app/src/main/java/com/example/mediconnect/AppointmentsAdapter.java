package com.example.mediconnect;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AppointmentsAdapter extends RecyclerView.Adapter<AppointmentsAdapter.AppointmentViewHolder> {

    private List<Appointment> appointmentList;

    public AppointmentsAdapter(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    @NonNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_appointment, parent, false);
        return new AppointmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int position) {
        Appointment appointment = appointmentList.get(position);
        holder.doctorNameText.setText("Doctor: " + appointment.getDoctorName());
        holder.specialtyText.setText("Especialidad: " + appointment.getSpecialty());
        holder.dateText.setText("Fecha: " + appointment.getDate());
        holder.meetLinkText.setText("Meet Link: " + appointment.getMeetLink());
    }

    @Override
    public int getItemCount() {
        return appointmentList.size();
    }

    static class AppointmentViewHolder extends RecyclerView.ViewHolder {

        TextView doctorNameText;
        TextView specialtyText;
        TextView dateText;
        TextView meetLinkText;

        public AppointmentViewHolder(@NonNull View itemView) {
            super(itemView);
            doctorNameText = itemView.findViewById(R.id.doctorNameText);
            specialtyText = itemView.findViewById(R.id.specialtyText);
            dateText = itemView.findViewById(R.id.dateText);
            meetLinkText = itemView.findViewById(R.id.meetLinkText);
        }
    }
}
