package edu.utsa.cs3773.thebestyou;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.utsa.cs3773.thebestyou.model.Challenge;
import edu.utsa.cs3773.thebestyou.model.ChallengeAdapter;
import edu.utsa.cs3773.thebestyou.model.ChallengeGenerator;
import edu.utsa.cs3773.thebestyou.model.FitnessGoal;
import edu.utsa.cs3773.thebestyou.model.UserPreferences;

public class ChallengesActivity extends AppCompatActivity {
    private ChallengeAdapter adapter;
    private RecyclerView recyclerView;
    private ChallengeGenerator challengeGenerator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenges);

        challengeGenerator = new ChallengeGenerator(this);
        UserPreferences preferences = getUserPreferences();
        List<Challenge> challenges = challengeGenerator.generateChallengesForUserPreferences(preferences);

        recyclerView = findViewById(R.id.challengesRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ChallengeAdapter(this, challenges, challenges -> {});
        recyclerView.setAdapter(adapter);

        Button btnProceed = findViewById(R.id.btnProceedToDashboard);
        btnProceed.setOnClickListener(v -> proceedToDashboard());
    }

    private UserPreferences getUserPreferences() {
        return new UserPreferences("Lose Weight", "Beginner", "Any", 5, 30, "None");
    }

    private void proceedToDashboard() {
        ArrayList<Challenge> selectedChallenges = new ArrayList<>();
        for (Challenge challenge : adapter.getChallenges()) {
            if (challenge.isSelected()) {
                selectedChallenges.add(challenge);
            }
        }

        if (selectedChallenges.isEmpty()) {
            Toast.makeText(this, "Please select at least one challenge.", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(ChallengesActivity.this, DashboardActivity.class);
            intent.putParcelableArrayListExtra("selectedChallenges", selectedChallenges);
            startActivity(intent);
        }
    }

}
