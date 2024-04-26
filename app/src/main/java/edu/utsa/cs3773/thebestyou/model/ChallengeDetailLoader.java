package edu.utsa.cs3773.thebestyou.model;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ChallengeDetailLoader {

    public static ChallengeDetail loadChallenge(Context context, String challengeName, String userLevel) {
        ChallengeDetail challengeDetail = null;
        try {
            InputStream is = context.getAssets().open("challenges1.json");
            Log.d("ChallengeDetailLoader", "JSON file opened successfully");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");
            Log.d("ChallengeDetailLoader", "JSON string: " + json.substring(0, Math.min(json.length(), 100)));

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
                                // Found the matching level
                                List<ChallengeDetail.Week> weeks = parseWeeks(levelObject.getAsJsonArray("workouts"));
                                ChallengeDetail.Level challengeLevel = new ChallengeDetail.Level(level, weeks);
                                challengeDetail = new ChallengeDetail(challengeName, null, challengeLevel);
                                break;
                            }
                        }
                        if (challengeDetail != null) {
                            break;
                        }
                    }
                }
                if (challengeDetail != null) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle IO exception gracefully, maybe return a default ChallengeDetail object
        }
        return challengeDetail;
    }

    private static List<ChallengeDetail.Week> parseWeeks(JsonArray workoutsArray) {
        List<ChallengeDetail.Week> weeks = new ArrayList<>();
        for (JsonElement workoutElement : workoutsArray) {
            JsonObject workoutObject = workoutElement.getAsJsonObject();
            int weekNumber = workoutObject.get("week").getAsInt();
            String overallDuration = workoutObject.get("overall_duration").getAsString();
            List<ChallengeDetail.Exercise> exercises = parseExercises(workoutObject.getAsJsonArray("exercises"));
            ChallengeDetail.Workout challengeWorkout = new ChallengeDetail.Workout(null, null, overallDuration, exercises);
            ChallengeDetail.Week week = new ChallengeDetail.Week(weekNumber, challengeWorkout);
            weeks.add(week);
        }
        return weeks;
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


