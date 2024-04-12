package edu.utsa.cs3773.thebestyou.model;

import java.util.ArrayList;
import java.util.List;

import edu.utsa.cs3773.thebestyou.R;

public class ChallengeGenerator {

    public static List<Challenge> generateChallengesForGoals(List<String> selectedGoals) {
        List<Challenge> challenges = new ArrayList<>();

        if (selectedGoals.contains("Lose Weight")) {
            challenges.addAll(getLoseWeightChallenges());
        }
        if (selectedGoals.contains("Improve Balance")) {
            challenges.addAll(getImproveBalanceChallenges());
        }
        if (selectedGoals.contains("Build Lower Body")) {
            challenges.addAll(getBuildLowerBodyChallenges());
        }
        if (selectedGoals.contains("Build Stamina")) {
            challenges.addAll(getBuildStaminaChallenges());
        }
        if (selectedGoals.contains("Flatten Stomach")) {
            challenges.addAll(getFlattenStomachChallenges());
        }
        if (selectedGoals.contains("Increase Speed")) {
            challenges.addAll(getIncreaseSpeedChallenges());
        }
        if (selectedGoals.contains("Increase Strength")) {
            challenges.addAll(getIncreaseStrengthChallenges());
        }
        if (selectedGoals.contains("Tone Up")) {
            challenges.addAll(getToneUpChallenges());
        }
        if (selectedGoals.contains("Bulk Up")) {
            challenges.addAll(getBulkUpChallenges());
        }

        return challenges;
    }

    private static List<Challenge> getLoseWeightChallenges() {
        List<Challenge> challenges = new ArrayList<>();
        challenges.add(new Challenge("Cardio Blast", "A high-intensity cardio workout to burn calories.", R.drawable.cardio_blast));
        challenges.add(new Challenge("HIIT Session", "Quick and intense bursts with rest periods.", R.drawable.hiit));
        return challenges;
    }

    private static List<Challenge> getImproveBalanceChallenges() {
        List<Challenge> challenges = new ArrayList<>();
        challenges.add(new Challenge("Yoga Basics", "Yoga poses that enhance core strength and balance.", R.drawable.yoga_balance));
        challenges.add(new Challenge("Stability Ball Exercises", "Workouts using a stability ball to improve balance.", R.drawable.stability_ball));
        return challenges;
    }

    private static List<Challenge> getBuildLowerBodyChallenges() {
        List<Challenge> challenges = new ArrayList<>();
        challenges.add(new Challenge("Squat Mastery", "Master various squat variations to build strength in your quads, glutes, and hamstrings.", R.drawable.squat_mastery));
        challenges.add(new Challenge("Glute Gains", "Target your glutes with exercises like hip thrusts and glute bridges for maximum activation.", R.drawable.glute_gains));
        return challenges;
    }

    private static List<Challenge> getBuildStaminaChallenges() {
        List<Challenge> challenges = new ArrayList<>();
        challenges.add(new Challenge("Endurance Runs", "Gradually increase your running distance to improve cardiovascular health and endurance.", R.drawable.endurance_runs));
        challenges.add(new Challenge("Stair Climbing", "Stair climbing is an effective way to build leg strength and cardiovascular endurance.", R.drawable.stair_climbing));
        return challenges;
    }

    private static List<Challenge> getFlattenStomachChallenges() {
        List<Challenge> challenges = new ArrayList<>();
        challenges.add(new Challenge("Core Conditioning", "Strengthen your core with a mix of planks, Russian twists, and leg raises.", R.drawable.core_conditioning));
        challenges.add(new Challenge("Ab Blast", "Incorporate high-intensity interval training focused on the abdominal area to burn fat and tone the core.", R.drawable.ab_blast));
        return challenges;
    }

    private static List<Challenge> getIncreaseSpeedChallenges() {
        List<Challenge> challenges = new ArrayList<>();
        challenges.add(new Challenge("Sprint Intervals", "Boost your speed with short, high-intensity sprint intervals.", R.drawable.sprint_intervals));
        challenges.add(new Challenge("Resistance Running", "Use resistance bands or parachutes to increase running resistance, improving stride power and speed.", R.drawable.resistance_running));
        return challenges;
    }

    private static List<Challenge> getIncreaseStrengthChallenges() {
        List<Challenge> challenges = new ArrayList<>();
        challenges.add(new Challenge("Bodyweight Mastery", "Advance through progressively challenging bodyweight exercises such as pull-ups, push-ups, and dips to increase muscle strength.", R.drawable.bodyweight_mastery));
        challenges.add(new Challenge("Weightlifting Power", "Master weightlifting with exercises like squats, deadlifts, and bench presses to build foundational strength.", R.drawable.weightlifting_power));
        return challenges;
    }

    private static List<Challenge> getToneUpChallenges() {
        List<Challenge> challenges = new ArrayList<>();
        challenges.add(new Challenge("Circuit Training", "Engage in high-repetition circuit training to tone muscles and improve cardiovascular health.", R.drawable.circuit_training));
        challenges.add(new Challenge("Pilates Core and More", "Focus on Pilates exercises that target the core while also toning the arms and legs for a full-body effect.", R.drawable.pilates_core_and_more));
        return challenges;
    }

    private static List<Challenge> getBulkUpChallenges() {
        List<Challenge> challenges = new ArrayList<>();
        challenges.add(new Challenge("Progressive Overload", "Focus on progressively increasing the weight you lift to continually challenge your muscles and stimulate growth.", R.drawable.progressive_overload));
        challenges.add(new Challenge("Hypertrophy-Specific Training", "Engage in workouts specifically designed for muscle growth, targeting different muscle groups with high volume and moderate weight.", R.drawable.hypertrophy_specific_training));
        return challenges;
    }
}
