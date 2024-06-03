package com.example.mediconnect;

public class Doctor {
    private String Apellidos;
    private String Nombres;
    private String email;
    private String especialidad;
    private String imageUrl;

    public Doctor() {
        // Constructor vacío necesario para Firebase
    }

    public Doctor(String apellidos, String nombres, String email, String especialidad, String imageUrl) {
        Apellidos = apellidos;
        Nombres = nombres;
        this.email = email;
        this.especialidad = especialidad;
        this.imageUrl = imageUrl;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public String getNombres() {
        return Nombres;
    }

    public String getEmail() {
        return email;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
