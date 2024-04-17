package edu.utsa.cs3773.thebestyou.model;

import java.util.List;

public class WorkoutTemplate {
    private String goal;
    private List<String> level;
    private List<Exercise> exercises;

    // Getters and Setters
    public String getGoal() {
        return goal;
    }

    public List<String> getLevel() {
        return level;  }

    public List<Exercise> getExercises() {
        return exercises;
    }
}
