package com.example.mediconnect;

public class Prescription {
    private String id;
    private String userId;
    private String details;

    public Prescription() {
        // Constructor vacío necesario para Firebase
    }

    public Prescription(String id, String userId, String details) {
        this.id = id;
        this.userId = userId;
        this.details = details;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
