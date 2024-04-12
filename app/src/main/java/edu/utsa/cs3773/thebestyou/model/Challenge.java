package edu.utsa.cs3773.thebestyou.model;

public class Challenge {
    private String title;
    private String description;
    private int imageUrl;

    public Challenge(String title, String description, int imageUrl) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getImageUrl() {
        return imageUrl;
    }
}
