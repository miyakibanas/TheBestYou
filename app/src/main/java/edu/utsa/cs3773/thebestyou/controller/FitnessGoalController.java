package edu.utsa.cs3773.thebestyou.controller;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import edu.utsa.cs3773.thebestyou.R;
import edu.utsa.cs3773.thebestyou.model.FitnessGoal;
import edu.utsa.cs3773.thebestyou.utils.PreferenceManager;

import java.util.HashSet;
import java.util.Set;

public class FitnessGoalController {
    private List<FitnessGoal> fitnessGoals;
    private PreferenceManager preferenceManager;
    private boolean isLoaded = false;

    public FitnessGoalController(Context context) {
        this.preferenceManager = new PreferenceManager(context);
        this.fitnessGoals = new ArrayList<>();
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
        if (!isLoaded) {
            loadSelectedGoals();
            isLoaded = true;
        }
        return fitnessGoals;
    }

    private void loadSelectedGoals() {
        Set<String> selectedGoals = preferenceManager.loadFitnessGoals();
        for (FitnessGoal goal : fitnessGoals) {
            if (selectedGoals.contains(goal.getName())) {
                goal.setSelected(true);
            }
        }
    }

    public void saveSelectedGoals() {
        Set<String> selectedGoals = new HashSet<>();
        for (FitnessGoal goal : fitnessGoals) {
            if (goal.isSelected()) {
                selectedGoals.add(goal.getName());
            }
        }
        preferenceManager.saveFitnessGoals(selectedGoals);
    }
}
