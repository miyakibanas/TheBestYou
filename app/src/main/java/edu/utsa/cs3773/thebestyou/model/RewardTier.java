package edu.utsa.cs3773.thebestyou.model;

// RewardTier.java
public class RewardTier {
    private int threshold;
    private Reward reward;

    public RewardTier(int threshold, Reward reward) {
        this.threshold = threshold;
        this.reward = reward;
    }

    public int getThreshold() {
        return threshold;
    }

    public Reward getReward() {
        return reward;
    }
}
