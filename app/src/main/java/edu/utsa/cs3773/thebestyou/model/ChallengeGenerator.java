package edu.utsa.cs3773.thebestyou.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import edu.utsa.cs3773.thebestyou.R;

public class ChallengeGenerator {
    private Context context;
    private WorkoutTemplateLoader loader;

    public ChallengeGenerator(Context context) {
        this.context = context;
        this.loader = new WorkoutTemplateLoader();
    }

    public List<Challenge> generateChallengesForUserPreferences(UserPreferences preferences) {
        List<WorkoutTemplate> templates = loader.loadTemplates(context);
        WorkoutPlanner planner = new WorkoutPlanner(templates);
        return planner.planWorkouts(preferences);
    }
}