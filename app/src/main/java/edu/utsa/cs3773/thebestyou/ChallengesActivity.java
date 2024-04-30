package edu.utsa.cs3773.thebestyou;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import edu.utsa.cs3773.thebestyou.model.Challenge;
import edu.utsa.cs3773.thebestyou.model.ChallengeAdapter;
import edu.utsa.cs3773.thebestyou.model.ChallengeGenerator;
import edu.utsa.cs3773.thebestyou.model.FitnessGoal;
import edu.utsa.cs3773.thebestyou.model.UserPreferences;
import edu.utsa.cs3773.thebestyou.utils.PreferenceManager;

public class ChallengesActivity extends BaseActivity {
    private ChallengeAdapter adapter;
    private RecyclerView recyclerView;
    private ChallengeGenerator challengeGenerator;
    private PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenges);

        preferenceManager = new PreferenceManager(this);
        challengeGenerator = new ChallengeGenerator(this);
        UserPreferences preferences = preferenceManager.loadUserPreferences();
        List<Challenge> challenges = challengeGenerator.generateChallengesForUserPreferences(preferences);

        recyclerView = findViewById(R.id.challengesRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ChallengeAdapter(this, challenges, challenge -> {});
        recyclerView.setAdapter(adapter);

        Button btnProceed = findViewById(R.id.btnProceedToDashboard);
        btnProceed.setOnClickListener(v -> proceedToDashboard());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void proceedToDashboard() {
        ArrayList<Challenge> selectedChallenges = new ArrayList<>();
        for (Challenge challenge : adapter.getAllChallenges()) {
            if (challenge.isSelected()) {
                challenge.setStartDate(LocalDate.now());
                Log.d("ChallengesActivity", "Start date set for challenge " + challenge.getTitle() + ": " + challenge.getStartDate());

                selectedChallenges.add(challenge);
            }
        }

        if (selectedChallenges.isEmpty()) {
            Toast.makeText(this, "Please select at least one challenge.", Toast.LENGTH_SHORT).show();
        } else {
            preferenceManager.saveSelectedChallenges(selectedChallenges);
            Intent intent = new Intent(ChallengesActivity.this, DashboardActivity.class);
            intent.putParcelableArrayListExtra("selectedChallenges", selectedChallenges);

            startActivity(intent);
        }
    }
}

