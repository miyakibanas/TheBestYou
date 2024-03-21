package edu.utsa.cs3773.thebestyou;

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

import edu.utsa.cs3773.thebestyou.model.FitnessGoal;
import edu.utsa.cs3773.thebestyou.model.FitnessGoalAdapter;

public class FitnessGoalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitness_goal);

        List<FitnessGoal> fitnessGoals = new ArrayList<>();
        fitnessGoals.add(new FitnessGoal(R.drawable.increase_strength, "Increase Strength"));
        fitnessGoals.add(new FitnessGoal(R.drawable.tone_up, "Tone Up"));
        fitnessGoals.add(new FitnessGoal(R.drawable.build_stamina, "Build Stamina"));
        fitnessGoals.add(new FitnessGoal(R.drawable.lose_weight, "Lose Weight"));
        fitnessGoals.add(new FitnessGoal(R.drawable.build_lower_body, "Build Lower Body"));
        fitnessGoals.add(new FitnessGoal(R.drawable.flatten_stomach, "Flatten Stomach"));
        fitnessGoals.add(new FitnessGoal(R.drawable.improve_balance, "Improve Balance"));
        fitnessGoals.add(new FitnessGoal(R.drawable.bulk_up, "Bulk Up"));
        fitnessGoals.add(new FitnessGoal(R.drawable.increase_speed, "Increase Speed"));

        FitnessGoalAdapter adapter = new FitnessGoalAdapter(this, fitnessGoals);
        GridView gridViewFitnessGoals = findViewById(R.id.gridViewFitnessGoals);
        gridViewFitnessGoals.setAdapter(adapter);

        gridViewFitnessGoals.setOnItemClickListener((parent, view, position, id) -> {
            adapter.toggleItemSelection(position);
        });

        findViewById(R.id.btnFinishSelection).setOnClickListener(v -> {
            List<Integer> selectedPositions = adapter.getSelectedPositions();
            StringBuilder selectedGoalsNames = new StringBuilder("Selected Goals: ");
            for (Integer position : selectedPositions) {
                selectedGoalsNames.append(fitnessGoals.get(position).getName()).append(", ");
            }
            Toast.makeText(this, selectedGoalsNames.toString(), Toast.LENGTH_LONG).show();

        });
    }
}