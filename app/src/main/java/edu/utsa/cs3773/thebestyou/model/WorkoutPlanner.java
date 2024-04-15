package edu.utsa.cs3773.thebestyou.model;

import java.util.ArrayList;
import java.util.List;

public class WorkoutPlanner {
    private List<WorkoutTemplate> templates;

    public WorkoutPlanner(List<WorkoutTemplate> templates) {
        this.templates = templates;
    }

    public List<Challenge> planWorkouts(UserPreferences preferences) {
        List<Challenge> challenges = new ArrayList<>();
        for (WorkoutTemplate template : templates) {
            if (template.getGoal().equals(preferences.getFitnessGoal()) &&
                    template.getLevel().equals(preferences.getFitnessLevel())) {
                challenges.addAll(template.getExercises());
            }
        }
        return challenges;
    }
}

