package edu.utsa.cs3773.thebestyou;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

import edu.utsa.cs3773.thebestyou.controller.FitnessGoalController;
import edu.utsa.cs3773.thebestyou.model.FitnessGoal;
import edu.utsa.cs3773.thebestyou.model.FitnessGoalAdapter;

public class FitnessGoalActivity extends AppCompatActivity {

    private FitnessGoalController fitnessGoalController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitness_goal);

        fitnessGoalController = new FitnessGoalController();
        List<FitnessGoal> fitnessGoals = fitnessGoalController.getFitnessGoals();

        FitnessGoalAdapter adapter = new FitnessGoalAdapter(this, fitnessGoals);
        GridView gridViewFitnessGoals = findViewById(R.id.gridViewFitnessGoals);
        gridViewFitnessGoals.setAdapter(adapter);

        gridViewFitnessGoals.setOnItemClickListener((parent, view, position, id) -> {
            adapter.toggleItemSelection(position);
        });

        findViewById(R.id.btnFinishSelection).setOnClickListener(v -> {
            List<Integer> selectedPositions = adapter.getSelectedPositions();

            // Check if any goals have been selected
            if (selectedPositions.isEmpty()) {
                // No goals selected, show an error message
                Toast.makeText(FitnessGoalActivity.this, "Please select at least one fitness goal before proceeding.", Toast.LENGTH_LONG).show();
            } else {
                // Goals have been selected, proceed with navigation
                ArrayList<String> selectedGoalsNames = fitnessGoalController.getSelectedGoalsNames(selectedPositions);
                Toast.makeText(this, "Selected Goals: " + selectedGoalsNames.toString(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(FitnessGoalActivity.this, LoadingActivity.class);
                intent.putStringArrayListExtra("selectedGoals", selectedGoalsNames);
                startActivity(intent);
            }
        });
    }
}
