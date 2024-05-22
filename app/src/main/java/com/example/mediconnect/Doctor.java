package com.example.mediconnect;

public class Doctor {
    private String id;
    private String name;
    private String specialty;
    private String email;
    private String phoneNumber;

    public Doctor() {
    }

    public Doctor(String id, String name, String specialty, String email, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
