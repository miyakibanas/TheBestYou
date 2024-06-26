package edu.utsa.cs3773.thebestyou.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import edu.utsa.cs3773.thebestyou.model.Challenge;
import edu.utsa.cs3773.thebestyou.UserSessionManager;
import edu.utsa.cs3773.thebestyou.model.UserPreferences;
import edu.utsa.cs3773.thebestyou.model.UserProfile;

public class PreferenceManager {
    private static final String PREF_NAME = "FitnessAppPreferences";
    private final SharedPreferences sharedPreferences;

    public PreferenceManager(Context context) {
        String userEmail = UserSessionManager.getUserEmail() != null ? UserSessionManager.getUserEmail() : "default";
        this.sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void saveUserSettings(UserPreferences preferences, UserProfile profile) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet("fitness_goals", new HashSet<>(preferences.getFitnessGoals()));
        editor.putString("fitness_level", preferences.getFitnessLevel());
        editor.putString("name", profile.getName());
        editor.putInt("age", profile.getAge());
        editor.putString("gender", profile.getGender());
        editor.putInt("heightInches", profile.getHeightInches());
        editor.putFloat("weight", profile.getWeight());
        editor.putFloat("targetWeight", profile.getTargetWeight());
        editor.putString("frequency", profile.getFrequency());
        editor.putString("level", profile.getLevel());
        editor.apply();
    }

    public void saveFitnessGoals(Set<String> goals) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet("fitness_goals", goals);
        editor.apply();
    }

    public Set<String> loadFitnessGoals() {
        return sharedPreferences.getStringSet("fitness_goals", new HashSet<>());
    }

    public UserPreferences loadUserPreferences() {
        Set<String> goals = sharedPreferences.getStringSet("fitness_goals", new HashSet<>());
        String level = sharedPreferences.getString("fitness_level", "default_level");
        return new UserPreferences(new ArrayList<>(goals), level);
    }

    public UserProfile loadUserProfile() {
        String name = sharedPreferences.getString("name", "No Name");
        int age = sharedPreferences.getInt("age", 0);
        String gender = sharedPreferences.getString("gender", "Not Specified");
        int heightInches = sharedPreferences.getInt("heightInches", 0);
        float weight = sharedPreferences.getFloat("weight", 0);
        float targetWeight = sharedPreferences.getFloat("targetWeight", 0);
        String frequency = sharedPreferences.getString("frequency", "Not Specified");
        String level = sharedPreferences.getString("level", "Not Specified");
        return new UserProfile(name, age, gender, heightInches, weight, targetWeight, frequency, level);
    }

    public void saveSelectedChallenges(List<Challenge> challenges) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String jsonChallenges = gson.toJson(challenges);
        editor.putString("selected_challenges", jsonChallenges);
        editor.apply();
    }

    public List<Challenge> loadSelectedChallenges() {
        String jsonChallenges = sharedPreferences.getString("selected_challenges", null);
        if (jsonChallenges != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Challenge>>(){}.getType();
            return gson.fromJson(jsonChallenges, type);
        }
        return new ArrayList<>();
    }

    public void clearPreferences() {
        Log.d("PreferenceManager", "Clearing all preferences.");
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public void clearUserPreferences() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("name");
        editor.remove("age");
        editor.remove("gender");
        editor.remove("heightInches");
        editor.remove("weight");
        editor.remove("targetWeight");
        editor.remove("frequency");
        editor.remove("level");
        editor.remove("fitness_goals");
        editor.remove("selected_challenges");
        editor.apply();
    }


}

