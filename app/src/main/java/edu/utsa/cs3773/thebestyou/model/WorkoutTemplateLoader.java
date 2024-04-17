package edu.utsa.cs3773.thebestyou.model;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class WorkoutTemplateLoader {
    public List<WorkoutTemplate> loadTemplates(Context context) {
        try {
            InputStream is = context.getAssets().open("workouts.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");
            return parseTemplates(json);
        } catch (IOException ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    private List<WorkoutTemplate> parseTemplates(String json) {
        Gson gson = new Gson();
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        JsonArray workoutsArray = jsonObject.getAsJsonArray("workouts");
        Type workoutListType = new TypeToken<List<WorkoutTemplate>>(){}.getType();
        return gson.fromJson(workoutsArray, workoutListType);
    }

}

