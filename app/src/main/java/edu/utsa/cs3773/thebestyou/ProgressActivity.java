package edu.utsa.cs3773.thebestyou;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import edu.utsa.cs3773.thebestyou.model.CalendarAdapter;
import edu.utsa.cs3773.thebestyou.model.Challenge;
import edu.utsa.cs3773.thebestyou.model.ChallengeCompletionStatusManager;
import edu.utsa.cs3773.thebestyou.model.ChallengeNameAdapter;
import edu.utsa.cs3773.thebestyou.model.UserPreferences;
import edu.utsa.cs3773.thebestyou.utils.PreferenceManager;

public class ProgressActivity extends AppCompatActivity {

    private GridView calendarGridView;
    private Challenge challenge;

    private CalendarAdapter calendarAdapter;
    private ChallengeCompletionStatusManager completionStatusManager;
    private List<Challenge> selectedChallenges;
    private PreferenceManager preferenceManager;
    private RecyclerView selectedChallengesRecyclerView;
    private ChallengeNameAdapter challengeNameAdapter;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        completionStatusManager = getIntent().getParcelableExtra("CompletionStatusManager");

        preferenceManager = new PreferenceManager(this);
        initializeCompletionStatusManager();
        setupCalendarGridView();
        setupSelectedChallengesRecyclerView();

        Button backToDashboardButton = findViewById(R.id.backToDashboardButton);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        selectedChallengesRecyclerView = findViewById(R.id.selectedChallengesRecyclerView);

        challengeNameAdapter = new ChallengeNameAdapter(selectedChallenges, this::onChallengeClicked);
        selectedChallengesRecyclerView.setLayoutManager(layoutManager);
        selectedChallengesRecyclerView.setAdapter(challengeNameAdapter);

        Challenge updatedChallenge = getIntent().getParcelableExtra("UpdatedChallenge");
        UserPreferences userPreferences = getIntent().getParcelableExtra("UserPreferences");
        if (updatedChallenge != null) {
            Toast.makeText(this, "Error loading", Toast.LENGTH_SHORT).show();
            // Update your UI or perform any necessary operations with the updatedChallenge
        }
        backToDashboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to the dashboard activity
                Intent intent = new Intent(ProgressActivity.this, DashboardActivity.class);
                intent.putExtra("Challenge", challenge);
                intent.putExtra("UserPreferences", userPreferences);
                intent.putParcelableArrayListExtra("selectedChallenges", (ArrayList<Challenge>) selectedChallenges);
                intent.putExtra("UpdatedChallenge", challenge);;

                startActivity(intent);
            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initializeCompletionStatusManager() {
        int numberOfDays = getNumberOfDaysInMonth();
        completionStatusManager = new ChallengeCompletionStatusManager(numberOfDays);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private int getNumberOfDaysInMonth() {
        Calendar calendar = Calendar.getInstance();
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentYear = calendar.get(Calendar.YEAR);

        LocalDate firstDayOfMonth = LocalDate.of(currentYear, currentMonth + 1, 1);
        LocalDate lastDayOfMonth = firstDayOfMonth.withDayOfMonth(firstDayOfMonth.lengthOfMonth());
        return (int) ChronoUnit.DAYS.between(firstDayOfMonth, lastDayOfMonth) + 1;
    }

    private void setupCalendarGridView() {
        calendarGridView = findViewById(R.id.calendarGridView);
        calendarAdapter = new CalendarAdapter(this, 30, selectedChallenges, completionStatusManager);
        calendarGridView.setAdapter(calendarAdapter);
    }

    private void setupSelectedChallengesRecyclerView() {
        RecyclerView selectedChallengesRecyclerView = findViewById(R.id.selectedChallengesRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        selectedChallengesRecyclerView.setLayoutManager(layoutManager);

        selectedChallenges = getIntent().getParcelableArrayListExtra("selectedChallenges");
        Log.d("ProgressActivity", "Selected challenges: " + selectedChallenges);

        challengeNameAdapter = new ChallengeNameAdapter(selectedChallenges, this::onChallengeClicked);
        selectedChallengesRecyclerView.setAdapter(challengeNameAdapter);
    }

    private void onChallengeClicked(Challenge challenge) {
        Log.d("ProgressActivity", "Clicked Challenge: " + challenge);
        Log.d("ProgressActivity", "Start date set for challenge " + challenge.getTitle() + ": " + challenge.getStartDate());

        // Check if selectedChallenges is null or empty
        if (selectedChallenges != null && !selectedChallenges.isEmpty()) {
            // Log the size of selectedChallenges
            Log.d("ProgressActivity", "Selected Challenges size: " + selectedChallenges.size());

            // Log the contents of selectedChallenges
            for (Challenge selectedChallenge : selectedChallenges) {
                Log.d("ProgressActivity", "Selected Challenge: " + selectedChallenge.getTitle());
                Log.d("ProgressActivity", "Selected Challenge Start Date: " + selectedChallenge.getStartDate());
                // Log any other relevant information about the selected challenges
            }
            UserPreferences userPreferences = preferenceManager.loadUserPreferences();
            Intent intent = new Intent(ProgressActivity.this, WorkoutActivity.class);
            intent.putExtra("Challenge", challenge);
            intent.putExtra("UserPreferences", userPreferences);
            intent.putParcelableArrayListExtra("selectedChallenges", (ArrayList<Challenge>) selectedChallenges);
            intent.putExtra("UpdatedChallenge", challenge);

            startActivity(intent);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        // Add log statements to indicate that onResume() is called
        Log.d("ProgressActivity", "onResume() called");

        // Notify the adapter of any data set changes
        calendarAdapter.notifyDataSetChanged();

        // Check for any updated challenges received from WorkoutActivity
        List<Challenge> updatedChallenges = getIntent().getParcelableArrayListExtra("selectedChallenges");
        if (updatedChallenges != null) {
            // Log the updated challenges
            Log.d("ProgressActivity", "Updated challenges: " + updatedChallenges);

            // Update the adapter with the updated challenges
            challengeNameAdapter.updateChallenges(updatedChallenges);
            challengeNameAdapter.notifyDataSetChanged();
            // Log the completion status of the updated challenges
            for (Challenge challenge : updatedChallenges) {
                List<Boolean> completionStatus = challenge.getCompletionStatusManager().getCompletionStatus();
                for (int i = 0; i < completionStatus.size(); i++) {
                    Log.d("ProgressActivity", "Completion status of day " + (i + 1) + " in challenge '" + challenge.getTitle() + "': " + (completionStatus.get(i) ? "Completed" : "Not completed"));
                }
            }
        } else {
            // Log that no updated challenges are received
            Log.d("ProgressActivity", "No updated challenges received");
        }
    }

}
