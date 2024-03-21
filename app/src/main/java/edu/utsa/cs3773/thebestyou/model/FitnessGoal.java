package edu.utsa.cs3773.thebestyou.model;

public class FitnessGoal {
    int imageResId;
    String name;

    public FitnessGoal(int imageResId, String name) {
        this.imageResId = imageResId;
        this.name = name;
    }

    // Getter methods
    public int getImageResId() {
        return imageResId;
    }

    public String getName() {
        return name;
    }
}