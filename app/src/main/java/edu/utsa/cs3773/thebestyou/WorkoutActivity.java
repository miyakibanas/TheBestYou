package edu.utsa.cs3773.thebestyou;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
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
    UserPointsManager userPointsManager;



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        userPointsManager = UserPointsManager.getInstance(getApplicationContext());

        // Retrieve the selected challenge and user preferences from the intent
        selectedChallenge = getIntent().getParcelableExtra("Challenge");
        userPreferences = getIntent().getParcelableExtra("UserPreferences");
        selectedChallenges = getIntent().getParcelableArrayListExtra("selectedChallenges");

        // Initialize the completionStatusManager
        completionStatusManager = new ChallengeCompletionStatusManager(selectedChallenge.numberOfDays);

        // Calculate the current week
        int currentWeekNumber = calculateCurrentWeek(selectedChallenge);

        // Load current week exercises based on the selected challenge and current week
        loadCurrentWeekExercises(selectedChallenge, userPreferences, currentWeekNumber);
        Button doneButton = findViewById(R.id.done_button);

        // Set a click listener for the "Done" button
        doneButton.setOnClickListener(v -> onDoneClicked());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private int calculateCurrentWeek(Challenge selectedChallenge) {
        // Get the start date of the challenge
        LocalDate startDate = selectedChallenge.getStartDate();

        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Calculate the number of days between the start date and the current date
        long daysBetween = ChronoUnit.DAYS.between(startDate, currentDate);

        // Calculate the current week based on the current day
        return (int) (daysBetween / 7) + 1;
    }

    private void loadCurrentWeekExercises(Challenge selectedChallenge, UserPreferences userPreferences, int currentWeekNumber) {
        // Load current week exercises using CurrentWeekExerciseLoader
        List<ChallengeDetail.Exercise> exercises = CurrentWeekExerciseLoader.loadCurrentWeekExercises(this, selectedChallenge.getTitle(), userPreferences.getFitnessLevel(), currentWeekNumber);

        if (exercises != null && !exercises.isEmpty()) {
            // If exercises are loaded successfully, populate UI
            populateUI(exercises);
        } else {
            // Handle error loading exercises
            Toast.makeText(this, "Error loading current week exercises", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void populateUI(List<ChallengeDetail.Exercise> exercises) {
        // Set up RecyclerView to display exercises
        RecyclerView exercisesRecyclerView = findViewById(R.id.exercises_recycler_view);
        ExercisesAdapter adapter = new ExercisesAdapter(exercises, this);
        exercisesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        exercisesRecyclerView.setAdapter(adapter);
    }

    // Method to handle the "Done" button click
    private void onDoneClicked() {
        // Check if all exercises are completed
        if (areAllExercisesCompleted()) {
            // Update the completion status for the current day in the selected challenge
            LocalDate currentDate = LocalDate.now();
            updateCompletionStatusForCurrentDay(selectedChallenge, currentDate);

            addPointsForCompletedExercises(selectedChallenge);

            // If all exercises are completed, proceed to ProgressActivity
            navigateToProgressActivity();
        } else {
            // If not all exercises are completed, show a toast message indicating that the user needs to complete all exercises
            Toast.makeText(this, "Please complete all exercises before proceeding.", Toast.LENGTH_SHORT).show();
        }
    }

    // Method to check if all exercises are completed
    private boolean areAllExercisesCompleted() {
        // Get the RecyclerView
        RecyclerView exercisesRecyclerView = findViewById(R.id.exercises_recycler_view);

        // Get the adapter attached to the RecyclerView
        RecyclerView.Adapter adapter = exercisesRecyclerView.getAdapter();

        // Check if the adapter is an instance of ExercisesAdapter
        if (adapter instanceof ExercisesAdapter) {
            ExercisesAdapter exercisesAdapter = (ExercisesAdapter) adapter;

            // Get the list of exercises from the adapter
            List<ChallengeDetail.Exercise> exercises = exercisesAdapter.getExercises();

            // Iterate through the list of exercises
            for (ChallengeDetail.Exercise exercise : exercises) {
                // Check if the exercise is completed
                if (!exercise.isCompleted()) {
                    return false; // If any exercise is not completed, return false
                }
            }
            return true; // If all exercises are completed, return true
        } else {
            // Return false if the adapter is not an instance of ExercisesAdapter
            return false;
        }
    }

    // Method to update the completion status for the current day in the selected challenge
    // Method to update the completion status for the current day in the selected challenge
    private void updateCompletionStatusForCurrentDay(Challenge challenge, LocalDate currentDate) {
        // Update the completion status for the current day in the selected challenge
        int dayIndex = calculateDayIndex(challenge.getStartDate(), currentDate);

        // Update the completion status for the current day in the selected challenge
        completionStatusManager.markDayCompleted(dayIndex);
        Log.d("CompletionStatusUpdate", "Day " + dayIndex + " completed for challenge: " + challenge.getTitle());

        // Update the selected challenge in the list with the modified completion status
        updateSelectedChallenge(challenge, dayIndex);

        addPointsForCompletedExercises(challenge);

    }


    // Method to update the selected challenge in the list with the modified completion status
    private void updateSelectedChallenge(Challenge challenge, int dayIndex) {
        for (int i = 0; i < selectedChallenges.size(); i++) {
            Challenge selectedChallenge = selectedChallenges.get(i);
            if (selectedChallenge.getTitle().equals(challenge.getTitle())) {
                // Update the completion status of the challenge in the selectedChallenges list
                ChallengeCompletionStatusManager completionStatusManager = selectedChallenge.getCompletionStatusManager();
                if (completionStatusManager != null) {
                    completionStatusManager.updateCompletionStatus(dayIndex, true); // Assuming true means completed
                }
                break; // No need to iterate further
            }
        }
    }



    // Method to calculate the day index within the challenge
    private int calculateDayIndex(LocalDate startDate, LocalDate currentDate) {
        int dayIndex = (int) ChronoUnit.DAYS.between(startDate, currentDate);

        // Log the day index calculation
        Log.d("DayIndexCalculation", "Day index calculated: " + dayIndex);

        return dayIndex;
    }
    private void addPointsForCompletedExercises(Challenge challenge) {
        // Add points for completing exercises based on your criteria
        int pointsToAdd = calculatePointsForCompletedExercises(challenge);
        userPointsManager.addPoints(pointsToAdd);

        // Store updated points in SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("UserPoints", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("points", userPointsManager.getUserPoints());
        editor.apply();
    }



    // Method to calculate points for completing exercises
    private int calculatePointsForCompletedExercises(Challenge challenge) {
        // Calculate points for completed exercises
        // For example, you can give 50 points per completed exercise
        return 50;
    }

    // Method to navigate to ProgressActivity
    // Inside navigateToProgressActivity() method
    private void navigateToProgressActivity() {
        if (selectedChallenges != null && !selectedChallenges.isEmpty()) {
            Intent intent = new Intent(WorkoutActivity.this, ProgressActivity.class);
            intent.putParcelableArrayListExtra("selectedChallenges", (ArrayList<Challenge>) selectedChallenges);
            startActivity(intent);
            finish();
        } else {
            // Handle the case where selectedChallenges is null or empty
            Log.e("WorkoutActivity", "Selected challenges list is null or empty.");
            // You can choose to show an error message or perform other actions here
        }
    }


}
