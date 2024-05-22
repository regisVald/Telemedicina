package com.example.mediconnect;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder> {
    private List<Doctor> doctorList;
    private OnEditButtonClickListener editButtonClickListener;
    private OnDeleteButtonClickListener deleteButtonClickListener;

    public DoctorAdapter(List<Doctor> doctorList, OnEditButtonClickListener editButtonClickListener, OnDeleteButtonClickListener deleteButtonClickListener) {
        this.doctorList = doctorList;
        this.editButtonClickListener = editButtonClickListener;
        this.deleteButtonClickListener = deleteButtonClickListener;
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
        holder.nameTextView.setText(doctor.getName());
        holder.specialtyTextView.setText(doctor.getSpecialty());
        holder.emailTextView.setText(doctor.getEmail());
        holder.phoneNumberTextView.setText(doctor.getPhoneNumber());

        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editButtonClickListener != null) {
                    editButtonClickListener.onEditButtonClick(doctor);
                }
            }
        });

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (deleteButtonClickListener != null) {
                    deleteButtonClickListener.onDeleteButtonClick(doctor);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return doctorList.size();
    }

    public interface OnEditButtonClickListener {
        void onEditButtonClick(Doctor doctor);
    }

    public interface OnDeleteButtonClickListener {
        void onDeleteButtonClick(Doctor doctor);
    }

    static class DoctorViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, specialtyTextView, emailTextView, phoneNumberTextView;
        Button editButton, deleteButton;

        public DoctorViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            specialtyTextView = itemView.findViewById(R.id.specialtyTextView);
            emailTextView = itemView.findViewById(R.id.emailTextView);
            phoneNumberTextView = itemView.findViewById(R.id.phoneNumberTextView);
            editButton = itemView.findViewById(R.id.editButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}



