package edu.utsa.cs3773.thebestyou.controller;

import edu.utsa.cs3773.thebestyou.model.UserProfile;

public class ProfileController {
    private UserProfile userProfile;

    public ProfileController() {
        this.userProfile = new UserProfile(0, "", 0, 0.0f, 0.0f, "", "");
    }

    private int convertHeightToInches(String heightInput) {
        try {
            int feet = Integer.parseInt(heightInput.split("'")[0]);
            int inches = Integer.parseInt(heightInput.split("'")[1].replace("\"", ""));
            return feet * 12 + inches;
        } catch (Exception e) {
            return -1;
        }
    }

    public boolean updateProfile(int age, String gender, String heightInput, float weight, float targetWeight, String frequency, String level) {
        if (age <= 0 || weight <= 0 || targetWeight <= 0) {
            return false;
        }

        if (gender.isEmpty() || frequency.isEmpty() || level.isEmpty() || heightInput.isEmpty()) {
            return false;
        }

        int totalInches = convertHeightToInches(heightInput);
        if (totalInches == -1) {
            return false;
        }

        userProfile.setAge(age);
        userProfile.setGender(gender);
        userProfile.setHeightInches(totalInches);
        userProfile.setWeight(weight);
        userProfile.setTargetWeight(targetWeight);
        userProfile.setFrequency(frequency);
        userProfile.setLevel(level);

        return true;
    }

    public UserProfile getUserProfile() {

        return userProfile;
    }

}

