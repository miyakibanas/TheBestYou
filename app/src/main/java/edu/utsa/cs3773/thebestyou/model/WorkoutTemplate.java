package edu.utsa.cs3773.thebestyou.model;

import java.util.List;

public class WorkoutTemplate {
    private String goal;
    private String level;
    private List<Exercise> exercises;

    public String getGoal() {
        return goal;
    }

    public String getLevel() {
        return level;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }
}

