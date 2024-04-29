package edu.utsa.cs3773.thebestyou;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import edu.utsa.cs3773.thebestyou.model.UserPointsManager;
import edu.utsa.cs3773.thebestyou.model.RewardSystem;


public class RewardsActivity extends AppCompatActivity {

    private UserPointsManager userPointsManager;
    private RewardSystem rewardSystem;
    private TextView textRewardTier;
    private TextView textPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewards);

        // Initialize UserPointsManager and RewardSystem
        userPointsManager = UserPointsManager.getInstance(this);
        rewardSystem = new RewardSystem(this, userPointsManager);

        // Find TextViews
        textRewardTier = findViewById(R.id.textRewardTier);
        textPoints = findViewById(R.id.textPoints);
        ImageView imageRewardBadge = findViewById(R.id.imageRewardBadge);

// Fetch the user's reward tier
        String rewardTier = userPointsManager.getRewardTier();

// Set the appropriate image resource based on the reward tier
        if (rewardTier.equals("Bronze")) {
            imageRewardBadge.setImageResource(R.drawable.bronze_badge); // Replace with your bronze badge drawable
        } else if (rewardTier.equals("Silver")) {
            imageRewardBadge.setImageResource(R.drawable.silverbadge); // Replace with your silver badge drawable
        } else if (rewardTier.equals("Gold")) {
            imageRewardBadge.setImageResource(R.drawable.gold_badge); // Replace with your gold badge drawable
        }
        // Fetch user's reward tier and points from SharedPreferences
        updateRewardInfo();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Update user's reward tier and points when the activity resumes
        updateRewardInfo();
    }

    // Method to fetch the user's points and reward tier from SharedPreferences
    private void updateRewardInfo() {
        int points = fetchPointsFromSharedPreferences();
        String rewardTier = userPointsManager.getRewardTier();

        // Update TextViews with user's reward tier and points
        textRewardTier.setText("You are in the " + rewardTier + " tier!");
        textPoints.setText("You have " + points + " points");

        // Check if the user has reached a new reward tier
        rewardSystem.checkRewardTier();
    }

    // Method to fetch the user's points from SharedPreferences
    private int fetchPointsFromSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserPoints", MODE_PRIVATE);
        return sharedPreferences.getInt("points", 0);
        // Default
    }
}
