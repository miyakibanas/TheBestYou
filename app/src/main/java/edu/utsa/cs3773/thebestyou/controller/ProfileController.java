package edu.utsa.cs3773.thebestyou.controller;

import edu.utsa.cs3773.thebestyou.model.UserProfile;

public class ProfileController {
    private UserProfile userProfile;

    public ProfileController() {
        this.userProfile = new UserProfile(0, "", 0, 0.0f, 0.0f, "", "");
    }

    public void updateProfile(int age, String gender, int heightInches, float weight, float targetWeight, String frequency, String level) {
        userProfile.setAge(age);
        userProfile.setGender(gender);
        userProfile.setHeightInches(heightInches);
        userProfile.setWeight(weight);
        userProfile.setTargetWeight(targetWeight);
        userProfile.setFrequency(frequency);
        userProfile.setLevel(level);


    }

    public UserProfile getUserProfile() {
        return userProfile;
    }


}

