package edu.utsa.cs3773.thebestyou.model;

public class Exercise {
    private String name;
    private String description;
    private String imageResId;
    private boolean completed; // New field to track completion status

    // Constructor
    public Exercise(String name, String description, String imageResId) {
        this.name = name;
        this.description = description;
        this.imageResId = imageResId;
        this.completed = false; // By default, exercise is not completed
    }

    // Getter and setter for 'name' field
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and setter for 'description' field
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Getter and setter for 'imageResId' field
    public String getImageResId() {
        return imageResId;
    }

    public void setImageResId(String imageResId) {
        this.imageResId = imageResId;
    }

    // Getter and setter for completion status
    public boolean isChecked() {
        return completed;
    }

    public void setChecked(boolean completed) {
        this.completed = completed;
    }
}
