package edu.utsa.cs3773.thebestyou;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.utsa.cs3773.thebestyou.model.CalendarAdapter;
import edu.utsa.cs3773.thebestyou.model.Challenge;
import edu.utsa.cs3773.thebestyou.model.ChallengeAdapter;
import edu.utsa.cs3773.thebestyou.model.UserPreferences;
import edu.utsa.cs3773.thebestyou.utils.PreferenceManager;

public class SelectedChallengesActivity extends AppCompatActivity {

    private ChallengeAdapter challengeAdapter;
    private CalendarAdapter calendarAdapter;
    private List<Challenge> selectedChallenges;
    private List<Boolean> completionStatus;
    private RecyclerView challengesRecyclerView;
    private GridView calendarGridView;
    private static final int REQUEST_CODE_CHALLENGE_DETAIL = 1;
    private PreferenceManager preferenceManager;

    private ActivityResultLauncher<Intent> challengeDetailLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_challenges);

        preferenceManager = new PreferenceManager(this);

        selectedChallenges = getIntent().getParcelableArrayListExtra("selectedChallenges");
        initializeCompletionStatus(30);

        setupChallengesRecyclerView();
        setupCalendarGridView();
        setupActivityResultLauncher();
    }

    private void setupActivityResultLauncher() {
        challengeDetailLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        int dayCompleted = result.getData().getIntExtra("dayCompleted", -1);
                        if (dayCompleted >= 0) {
                            completionStatus.set(dayCompleted, true);
                            calendarAdapter.notifyDataSetChanged();
                        }
                    }
                });
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

        challengeDetailLauncher.launch(intent);
    }


    private void setupCalendarGridView() {
        calendarGridView = findViewById(R.id.calendarGridView);
        calendarAdapter = new CalendarAdapter(this, 30, completionStatus);
        calendarGridView.setAdapter(calendarAdapter);
    }

    private void initializeCompletionStatus(int daysCount) {
        completionStatus = new ArrayList<>(Collections.nCopies(daysCount, false));
    }
}

