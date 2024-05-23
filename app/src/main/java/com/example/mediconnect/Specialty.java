package com.example.mediconnect;

public class Specialty {
    private String name;
    private int iconResId;

    public Specialty(String name, int iconResId) {
        this.name = name;
        this.iconResId = iconResId;
    }

    public String getName() {
        return name;
    }

    public int getIconResId() {
        return iconResId;
    }
}
