package edu.utsa.cs3773.thebestyou.controller;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import edu.utsa.cs3773.thebestyou.model.UserPreferences;
import edu.utsa.cs3773.thebestyou.model.UserProfile;
import edu.utsa.cs3773.thebestyou.utils.PreferenceManager;

public class ProfileController {
    private PreferenceManager preferenceManager;

    public ProfileController(Context context) {
        this.preferenceManager = new PreferenceManager(context);
    }

    private int convertHeightToInches(String heightInput) {
        try {
            int feet = Integer.parseInt(heightInput.split("'")[0].trim());
            int inches = Integer.parseInt(heightInput.split("'")[1].trim().replace("\"", ""));
            return feet * 12 + inches;
        } catch (Exception e) {
            return -1;
        }
    }

    public boolean updateProfile(int age, String gender, String heightInput, float weight, float targetWeight, String frequency, String level) {
        if (age <= 0 || weight <= 0 || targetWeight <= 0 || gender.isEmpty() || frequency.isEmpty() || level.isEmpty() || heightInput.isEmpty()) {
            return false;
        }

        int totalInches = convertHeightToInches(heightInput);
        if (totalInches == -1) {
            return false;
        }


        Set<String> existingGoals = preferenceManager.loadFitnessGoals();
        List<String> fitnessGoals = new ArrayList<>(existingGoals);

        UserProfile userProfile = new UserProfile(age, gender, totalInches, weight, targetWeight, frequency, level);
        UserPreferences userPreferences = new UserPreferences(fitnessGoals, level);
        preferenceManager.saveUserSettings(userPreferences, userProfile);
        return true;
    }

    public UserProfile getUserProfile() {
        return preferenceManager.loadUserProfile();
    }
}


