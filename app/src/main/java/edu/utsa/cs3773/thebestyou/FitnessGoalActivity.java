package edu.utsa.cs3773.thebestyou;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import edu.utsa.cs3773.thebestyou.controller.FitnessGoalController;
import edu.utsa.cs3773.thebestyou.model.FitnessGoal;
import edu.utsa.cs3773.thebestyou.model.FitnessGoalAdapter;
import edu.utsa.cs3773.thebestyou.model.UserPreferences;
import edu.utsa.cs3773.thebestyou.model.UserProfile;
import edu.utsa.cs3773.thebestyou.utils.PreferenceManager;

public class FitnessGoalActivity extends BaseActivity {

    private FitnessGoalController fitnessGoalController;
    private PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitness_goal);

        preferenceManager = new PreferenceManager(this);
        fitnessGoalController = new FitnessGoalController(this);

        List<FitnessGoal> fitnessGoals = fitnessGoalController.getFitnessGoals();
        FitnessGoalAdapter adapter = new FitnessGoalAdapter(this, fitnessGoals);
        GridView gridViewFitnessGoals = findViewById(R.id.gridViewFitnessGoals);
        gridViewFitnessGoals.setAdapter(adapter);

        gridViewFitnessGoals.setOnItemClickListener((parent, view, position, id) -> {
            adapter.toggleItemSelection(position);
        });

        findViewById(R.id.btnFinishSelection).setOnClickListener(v -> {
            String selectedLevel = getIntent().getStringExtra("selectedLevel");
            fitnessGoalController.saveSelectedGoals();

            List<FitnessGoal> selectedGoals = fitnessGoals.stream()
                    .filter(FitnessGoal::isSelected)
                    .collect(Collectors.toList());

            List<String> selectedGoalsNames = selectedGoals.stream()
                    .map(FitnessGoal::getName)
                    .collect(Collectors.toList());

            UserPreferences userPreferences = new UserPreferences(selectedGoalsNames, selectedLevel);
            UserProfile userProfile = preferenceManager.loadUserProfile();
            preferenceManager.saveUserSettings(userPreferences, userProfile);

            if (selectedGoals.isEmpty()) {
                Toast.makeText(FitnessGoalActivity.this, "Please select at least one fitness goal before proceeding.", Toast.LENGTH_LONG).show();
            } else {
                Intent intent = new Intent(FitnessGoalActivity.this, LoadingActivity.class);
                intent.putParcelableArrayListExtra("selectedGoals", new ArrayList<>(selectedGoals));
                startActivity(intent);
            }
        });
    }
}
