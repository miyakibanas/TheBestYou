package edu.utsa.cs3773.thebestyou;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import edu.utsa.cs3773.thebestyou.model.Challenge;
import edu.utsa.cs3773.thebestyou.model.ChallengeAdapter;
import edu.utsa.cs3773.thebestyou.model.UserPreferences;
import edu.utsa.cs3773.thebestyou.utils.PreferenceManager;

public class SelectedChallengesActivity extends AppCompatActivity {

    private ChallengeAdapter challengeAdapter;
    private List<Challenge> selectedChallenges;
    private RecyclerView challengesRecyclerView;
    private static final int REQUEST_CODE_CHALLENGE_DETAIL = 1;
    private PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_challenges);

        preferenceManager = new PreferenceManager(this);

        selectedChallenges = getIntent().getParcelableArrayListExtra("selectedChallenges");

        setupChallengesRecyclerView();
    }

    private void setupChallengesRecyclerView() {
        challengesRecyclerView = findViewById(R.id.challengesRecyclerView);
        challengeAdapter = new ChallengeAdapter(this, selectedChallenges, this::onChallengeClick);
        challengesRecyclerView.setAdapter(challengeAdapter);
        challengesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void onChallengeClick(Challenge challenge) {
        // Retrieve user preferences
        UserPreferences userPreferences = preferenceManager.loadUserPreferences();

        Log.d("SelectedChallengesActivity", "Selected Challenge: " + challenge);
        Log.d("SelectedChallengesActivity", "User Preferences: " + userPreferences);

        // Pass user preferences along with the challenge to the ChallengeDetailActivity
        Intent intent = new Intent(SelectedChallengesActivity.this, ChallengeDetailActivity.class);
        intent.putExtra("Challenge", challenge);
        intent.putExtra("UserPreferences", userPreferences);

        startActivityForResult(intent, REQUEST_CODE_CHALLENGE_DETAIL);
    }
}
