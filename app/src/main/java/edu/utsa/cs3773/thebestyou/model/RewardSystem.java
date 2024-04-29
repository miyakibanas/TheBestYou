package edu.utsa.cs3773.thebestyou.model;

// RewardSystem.java
import android.content.Context;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class RewardSystem {
    private Context context;
    private UserPointsManager userPointsManager;
    private List<RewardTier> rewardTiers;

    public RewardSystem(Context context, UserPointsManager userPointsManager) {
        this.context = context;
        this.userPointsManager = userPointsManager;
        this.rewardTiers = new ArrayList<>();
        // Initialize reward tiers
        initializeRewardTiers();
    }

    private void initializeRewardTiers() {
        // Define reward tiers with thresholds and rewards
        rewardTiers.add(new RewardTier(500, new Reward("Bronze Badge")));
        rewardTiers.add(new RewardTier(1000, new Reward("Silver Badge")));
        rewardTiers.add(new RewardTier(2000, new Reward("Gold Badge")));
        // Add more tiers as needed
    }

    public void checkRewardTier() {
        int currentPoints = userPointsManager.getUserPoints();
        for (RewardTier tier : rewardTiers) {
            if (currentPoints >= tier.getThreshold()) {
                grantReward(tier.getReward());
            }
        }
    }

    private void grantReward(Reward reward) {
        showToast("Congratulations! You've unlocked the " + reward.getName() + "!");
    }

    private void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}

