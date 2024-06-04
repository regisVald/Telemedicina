package com.example.mediconnect;

public class Appointment {
    private String doctorName;
    private String specialty;
    private String date;
    private String meetLink;
    private String userId;

    public Appointment() {
        // Constructor vacío requerido para Firebase
    }

    public Appointment(String doctorName, String specialty, String date, String meetLink, String userId) {
        this.doctorName = doctorName;
        this.specialty = specialty;
        this.date = date;
        this.meetLink = meetLink;
        this.userId = userId;
    }

    // Getters y setters
    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMeetLink() {
        return meetLink;
    }

    public void setMeetLink(String meetLink) {
        this.meetLink = meetLink;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
