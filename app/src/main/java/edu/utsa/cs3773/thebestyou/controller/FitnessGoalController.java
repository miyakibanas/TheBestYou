package edu.utsa.cs3773.thebestyou.controller;

import java.util.ArrayList;
import java.util.List;

import edu.utsa.cs3773.thebestyou.R;
import edu.utsa.cs3773.thebestyou.model.FitnessGoal;

public class FitnessGoalController {
    private List<FitnessGoal> fitnessGoals;

    public FitnessGoalController() {
        fitnessGoals = new ArrayList<>();
        initializeGoals();
    }

    private void initializeGoals() {
        fitnessGoals.add(new FitnessGoal(R.drawable.increase_strength, "Increase Strength"));
        fitnessGoals.add(new FitnessGoal(R.drawable.tone_up, "Tone Up"));
        fitnessGoals.add(new FitnessGoal(R.drawable.build_stamina, "Build Stamina"));
        fitnessGoals.add(new FitnessGoal(R.drawable.lose_weight, "Lose Weight"));
        fitnessGoals.add(new FitnessGoal(R.drawable.build_lower_body, "Build Lower Body"));
        fitnessGoals.add(new FitnessGoal(R.drawable.flatten_stomach, "Flatten Stomach"));
        fitnessGoals.add(new FitnessGoal(R.drawable.improve_balance, "Improve Balance"));
        fitnessGoals.add(new FitnessGoal(R.drawable.bulk_up, "Bulk Up"));
        fitnessGoals.add(new FitnessGoal(R.drawable.increase_speed, "Increase Speed"));
    }

    public List<FitnessGoal> getFitnessGoals() {
        return fitnessGoals;
    }

    public List<FitnessGoal> getSelectedFitnessGoals() {
        List<FitnessGoal> selectedGoals = new ArrayList<>();
        for (FitnessGoal goal : fitnessGoals) {
            if (goal.isSelected()) {
                selectedGoals.add(goal);
            }
        }
        return selectedGoals;
    }
}
