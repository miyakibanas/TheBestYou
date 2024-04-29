package edu.utsa.cs3773.thebestyou.model;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;

public class UserPointsManager {
    private int userPoints = 0;
    private List<RewardTier> rewardTiers;
    private String rewardTier = "Bronze"; // Default reward tier
    private Context context;

    // Singleton instance
    private static UserPointsManager instance;

    private UserPointsManager(Context context) {
        this.context = context;
    }

    // Singleton getInstance method
    public static synchronized UserPointsManager getInstance(Context context) {
        if (instance == null) {
            instance = new UserPointsManager(context.getApplicationContext());
        }
        return instance;
    }

    public void addPoints(int points) {
        userPoints += points;

        // Check if rewardTiers is not null before iterating
        if (rewardTiers != null) {
            // Check if user has reached higher reward tiers
            for (RewardTier tier : rewardTiers) {
                if (userPoints >= tier.getThreshold()) {
                    rewardTier = tier.getReward().getName();
                }
            }
        }

        // Store updated points and reward tier in SharedPreferences
        SharedPreferences sharedPreferences = context.getSharedPreferences("UserPoints", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("points", userPoints);
        editor.putString("rewardTier", rewardTier);
        editor.apply();
    }

    public int getUserPoints() {
        // Retrieve user points from SharedPreferences
        SharedPreferences sharedPreferences = context.getSharedPreferences("UserPoints", Context.MODE_PRIVATE);
        return sharedPreferences.getInt("points", 0);
    }

    public String getRewardTier() {
        // Retrieve reward tier from SharedPreferences
        SharedPreferences sharedPreferences = context.getSharedPreferences("UserPoints", Context.MODE_PRIVATE);
        return sharedPreferences.getString("rewardTier", "Bronze"); // Return "Bronze" if not found
    }

    public void setRewardTiers(List<RewardTier> rewardTiers) {
        this.rewardTiers = rewardTiers;
    }
}
