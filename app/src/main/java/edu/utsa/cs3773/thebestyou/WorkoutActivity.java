package edu.utsa.cs3773.thebestyou;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.utsa.cs3773.thebestyou.model.Challenge;
import edu.utsa.cs3773.thebestyou.model.ChallengeCompletionStatusManager;
import edu.utsa.cs3773.thebestyou.model.ChallengeDetail;
import edu.utsa.cs3773.thebestyou.model.CurrentWeekExerciseLoader;
import edu.utsa.cs3773.thebestyou.model.ExercisesAdapter;
import edu.utsa.cs3773.thebestyou.model.UserPointsManager;
import edu.utsa.cs3773.thebestyou.model.UserPreferences;

public class WorkoutActivity extends AppCompatActivity {

    private Challenge selectedChallenge;
    private UserPreferences userPreferences;
    private ChallengeCompletionStatusManager completionStatusManager;
    private List<Challenge> selectedChallenges;
    private UserPointsManager userPointsManager;
    private ChallengeDetail.Week week; // Week object containing current week data

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        userPointsManager = UserPointsManager.getInstance(getApplicationContext());

        // Retrieve the selected challenge, week, and user preferences from the intent
        selectedChallenge = getIntent().getParcelableExtra("Challenge");
        week = getIntent().getParcelableExtra("Week");
        userPreferences = getIntent().getParcelableExtra("UserPreferences");
        selectedChallenges = getIntent().getParcelableArrayListExtra("selectedChallenges");

        // Initialize the completionStatusManager
        completionStatusManager = new ChallengeCompletionStatusManager(selectedChallenge.numberOfDays);

        // Load exercises directly from the Week object
        loadWeekExercises(week);

        Button doneButton = findViewById(R.id.done_button);
        doneButton.setOnClickListener(v -> onDoneClicked());

        TextView weekTextView = findViewById(R.id.week);
        if (week != null) {
            weekTextView.setText("Week " + week.getWeekNumber());
        }
    }

    private void loadWeekExercises(ChallengeDetail.Week week) {
        if (week != null && week.getExercises() != null && !week.getExercises().isEmpty()) {
            populateUI(week.getExercises());
        } else {
            Toast.makeText(this, "Error loading exercises for the week", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void populateUI(List<ChallengeDetail.Exercise> exercises) {
        RecyclerView exercisesRecyclerView = findViewById(R.id.exercises_recycler_view);
        ExercisesAdapter adapter = new ExercisesAdapter(exercises, this);
        exercisesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        exercisesRecyclerView.setAdapter(adapter);
    }

    private boolean areAllExercisesCompleted() {
        RecyclerView exercisesRecyclerView = findViewById(R.id.exercises_recycler_view);
        RecyclerView.Adapter adapter = exercisesRecyclerView.getAdapter();
        if (adapter instanceof ExercisesAdapter) {
            ExercisesAdapter exercisesAdapter = (ExercisesAdapter) adapter;
            return exercisesAdapter.areAllExercisesCompleted();
        }
        return false;
    }

    private void onDoneClicked() {
        if (areAllExercisesCompleted()) {
            updateCompletionStatusForCurrentDay(selectedChallenge, LocalDate.now());
            navigateToProgressActivity();
        } else {
            Toast.makeText(this, "Please complete all exercises before proceeding.", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateCompletionStatusForCurrentDay(Challenge challenge, LocalDate currentDate) {
        int dayIndex = (int) ChronoUnit.DAYS.between(challenge.getStartDate(), currentDate);
        completionStatusManager.markDayCompleted(dayIndex);
        addPointsForCompletedExercises(challenge);
    }

    private void addPointsForCompletedExercises(Challenge challenge) {
        int pointsToAdd = calculatePointsForCompletedExercises();
        userPointsManager.addPoints(pointsToAdd);
        SharedPreferences sharedPreferences = getSharedPreferences("UserPoints", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("points", userPointsManager.getUserPoints());
        editor.apply();
    }

    private int calculatePointsForCompletedExercises() {
        return 50; // Example: 50 points for completing all exercises
    }

    private void navigateToProgressActivity() {
        Intent intent = new Intent(WorkoutActivity.this, ProgressActivity.class);
        startActivity(intent);
        finish();
    }
}