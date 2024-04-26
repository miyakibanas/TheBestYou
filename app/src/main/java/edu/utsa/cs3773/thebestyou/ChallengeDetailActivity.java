package edu.utsa.cs3773.thebestyou;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import edu.utsa.cs3773.thebestyou.model.Challenge;
import edu.utsa.cs3773.thebestyou.model.ChallengeDetail;
import edu.utsa.cs3773.thebestyou.model.ChallengeDetailLoader;
import edu.utsa.cs3773.thebestyou.model.WeeksAndExercisesAdapter;
import edu.utsa.cs3773.thebestyou.model.UserPreferences;

public class ChallengeDetailActivity extends AppCompatActivity {

    private ChallengeDetail challengeDetail;
    private UserPreferences userPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge_detail);

        // Retrieve the challenge and user preferences from the intent
        Challenge challenge = getIntent().getParcelableExtra("Challenge");
        UserPreferences userPreferences = getIntent().getParcelableExtra("UserPreferences");

        // Load the challenge details based on the challenge object and user's fitness level
        if (challenge != null && userPreferences != null) {
            loadChallengeDetail(challenge, userPreferences);
        } else {
            // Handle missing challenge or user preferences
            Toast.makeText(this, "Error: Challenge or user preferences not provided", Toast.LENGTH_SHORT).show();
            finish();
        }
    }


    private void loadChallengeDetail(Challenge challenge, UserPreferences userPreferences) {
        // Retrieve the fitness level from user preferences
        String userFitnessLevel = userPreferences.getFitnessLevel();

        // Load challenge detail using ChallengeDetailLoader
        challengeDetail = ChallengeDetailLoader.loadChallenge(this, challenge.getTitle(), userFitnessLevel);

        if (challengeDetail != null) {
            // Populate the views with the loaded challenge details
            populateChallengeDetails();
        } else {
            // Handle failed to load challenge detail
            Toast.makeText(this, "Error: Failed to load challenge detail", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void populateChallengeDetails() {
        // Set the challenge name TextView
        TextView challengeNameTextView = findViewById(R.id.challenge_name_text_view);
        challengeNameTextView.setText(challengeDetail.getChallengeName());

        // Populate the weeks RecyclerView
        RecyclerView weeksRecyclerView = findViewById(R.id.weeks_recycler_view);
        WeeksAndExercisesAdapter adapter = new WeeksAndExercisesAdapter(challengeDetail.getLevel().getWeeks(), this);
        weeksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        weeksRecyclerView.setAdapter(adapter);
    }

}
