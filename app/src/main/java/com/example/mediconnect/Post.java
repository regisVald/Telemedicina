package com.example.mediconnect;

public class Post {
    private String title;
    private String content;
    private String userId;
    private long timestamp;

    public Post() {
        // Constructor vacío necesario para Firebase
    }

    public Post(String title, String content, String userId, long timestamp) {
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.timestamp = timestamp;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getUserId() {
        return userId;
    }

    public long getTimestamp() {
        return timestamp;
    }
}

