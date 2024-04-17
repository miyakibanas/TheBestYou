package edu.utsa.cs3773.thebestyou.model;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

public class WorkoutPlanner {
    private List<WorkoutTemplate> templates;

    public WorkoutPlanner(List<WorkoutTemplate> templates) {
        this.templates = templates;
    }

    public List<Challenge> planWorkouts(UserPreferences preferences) {
        List<Challenge> challenges = new ArrayList<>();
        for (String goal : preferences.getFitnessGoals()) {
            for (WorkoutTemplate template : templates) {
                if (template.getGoal().equalsIgnoreCase(goal) &&
                        template.getLevel().contains(preferences.getFitnessLevel())) {
                    for (Exercise exercise : template.getExercises()) {
                        challenges.add(new Challenge(
                                exercise.getName(),
                                exercise.getDescription(),
                                exercise.getImageResId()
                        ));
                    }
                }
            }
        }
        return challenges;
    }

}


