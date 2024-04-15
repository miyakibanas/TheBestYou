package edu.utsa.cs3773.thebestyou.model;

public class Exercise {
    private String name;
    private String description;
    private String imageResId;  // Use string to match JSON
    private int duration;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageResId() {
        return imageResId;
    }

    public int getDuration() {
        return duration;
    }
}
