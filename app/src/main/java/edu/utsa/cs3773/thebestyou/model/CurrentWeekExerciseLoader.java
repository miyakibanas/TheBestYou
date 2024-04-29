// CurrentWeekExerciseLoader.java

package edu.utsa.cs3773.thebestyou.model;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CurrentWeekExerciseLoader {

    public static List<ChallengeDetail.Exercise> loadCurrentWeekExercises(Context context, String challengeName, String userLevel, int currentWeek) {
        List<ChallengeDetail.Exercise> exercises = new ArrayList<>();
        try {
            InputStream is = context.getAssets().open("challenges1.json");
            Log.d("ExerciseLoader", "JSON file opened successfully");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, StandardCharsets.UTF_8);
            Log.d("ExerciseLoader", "JSON string: " + json.substring(0, Math.min(json.length(), 100)));

            Gson gson = new Gson();
            JsonObject goalsObject = gson.fromJson(json, JsonObject.class);
            JsonArray fitnessGoalsArray = goalsObject.getAsJsonArray("fitness_goals");

            // Iterate over fitness goals
            for (JsonElement goalElement : fitnessGoalsArray) {
                JsonObject goalObject = goalElement.getAsJsonObject();
                JsonArray challengesArray = goalObject.getAsJsonArray("challenges");
                // Iterate over challenges
                for (JsonElement challengeElement : challengesArray) {
                    JsonObject challengeObject = challengeElement.getAsJsonObject();
                    String challenge = challengeObject.get("name").getAsString();
                    if (challengeName.equals(challenge)) {
                        JsonArray levelsArray = challengeObject.getAsJsonArray("level");
                        // Iterate over levels
                        for (JsonElement levelElement : levelsArray) {
                            JsonObject levelObject = levelElement.getAsJsonObject();
                            String level = levelObject.get("name").getAsString();
                            if (userLevel.equals(level)) {
                                JsonArray workoutsArray = levelObject.getAsJsonArray("workouts");
                                // Find the current week's exercises
                                for (JsonElement workoutElement : workoutsArray) {
                                    JsonObject workoutObject = workoutElement.getAsJsonObject();
                                    int weekNumber = workoutObject.get("week").getAsInt();
                                    if (weekNumber == currentWeek) {
                                        exercises = parseExercises(workoutObject.getAsJsonArray("exercises"));
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle IO exception gracefully, maybe return an empty list
        }
        return exercises;
    }

    private static List<ChallengeDetail.Exercise> parseExercises(JsonArray exercisesArray) {
        List<ChallengeDetail.Exercise> exercises = new ArrayList<>();
        for (JsonElement exerciseElement : exercisesArray) {
            JsonObject exerciseObject = exerciseElement.getAsJsonObject();
            String exerciseName = exerciseObject.get("name").getAsString();
            String exerciseDuration = exerciseObject.get("duration").getAsString();
            exercises.add(new ChallengeDetail.Exercise(exerciseName, exerciseDuration));
        }
        return exercises;
    }
}
