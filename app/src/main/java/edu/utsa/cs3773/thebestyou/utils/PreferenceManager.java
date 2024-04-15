package edu.utsa.cs3773.thebestyou.utils;

import android.content.Context;
import android.content.SharedPreferences;
import edu.utsa.cs3773.thebestyou.model.UserPreferences;

public class PreferenceManager {
    private static final String PREF_NAME = "FitnessAppPreferences";
    private static final String KEY_FITNESS_GOAL = "fitness_goal";
    private static final String KEY_FITNESS_LEVEL = "fitness_level";

    private SharedPreferences sharedPreferences;

    public PreferenceManager(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void saveFitnessPreferences(String goal, String level) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_FITNESS_GOAL, goal);
        editor.putString(KEY_FITNESS_LEVEL, level);
        editor.apply();
    }

    public UserPreferences loadFitnessPreferences() {
        String goal = sharedPreferences.getString(KEY_FITNESS_GOAL, null);
        String level = sharedPreferences.getString(KEY_FITNESS_LEVEL, null);
        return new UserPreferences(goal, level);
    }
}

