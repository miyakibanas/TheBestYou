package edu.utsa.cs3773.thebestyou.model;

public class UserPreferences {
    private String fitnessGoal;
    private String fitnessLevel;

    public UserPreferences(String fitnessGoal, String fitnessLevel) {
        this.fitnessGoal = fitnessGoal;
        this.fitnessLevel = fitnessLevel;
    }

    public String getFitnessGoal() {
        return fitnessGoal;
    }

    public void setFitnessGoal(String fitnessGoal) {
        this.fitnessGoal = fitnessGoal;
    }

    public String getFitnessLevel() {
        return fitnessLevel;
    }

    public void setFitnessLevel(String fitnessLevel) {
        this.fitnessLevel = fitnessLevel;
    }
}
