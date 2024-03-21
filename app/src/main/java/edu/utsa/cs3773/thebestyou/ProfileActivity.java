package edu.utsa.cs3773.thebestyou;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import edu.utsa.cs3773.thebestyou.FitnessGoalActivity;
import edu.utsa.cs3773.thebestyou.model.UserProfile;
import edu.utsa.cs3773.thebestyou.controller.ProfileController;

public class ProfileActivity extends AppCompatActivity {

    private ProfileController profileController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileController = new ProfileController();

        final EditText editTextAge = findViewById(R.id.editTextAge);
        final Spinner spinnerGender = findViewById(R.id.spinnerGender);
        final EditText editTextHeight = findViewById(R.id.editTextHeight);
        final EditText editTextWeight = findViewById(R.id.editTextWeight);
        final EditText editTextTargetWeight = findViewById(R.id.editTextTargetWeight);
        final Spinner spinnerFrequency = findViewById(R.id.spinnerFrequency);
        final Spinner spinnerLevel = findViewById(R.id.spinnerLevel);
        Button btnNextToGoals = findViewById(R.id.btnNextToGoals);

        setupSpinner(spinnerGender, R.array.gender_options);
        setupSpinner(spinnerFrequency, R.array.frequency_options);
        setupSpinner(spinnerLevel, R.array.level_options);

        btnNextToGoals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int age = Integer.parseInt(editTextAge.getText().toString());
                    String gender = spinnerGender.getSelectedItem().toString();
                    String heightInput = editTextHeight.getText().toString().trim();
                    int feet = 0;
                    int inches = 0;
                    if (!heightInput.isEmpty() && heightInput.contains("'") && heightInput.contains("\"")) {
                        feet = Integer.parseInt(heightInput.split("'")[0]);
                        inches = Integer.parseInt(heightInput.split("'")[1].replace("\"", ""));
                    }
                    int totalInches = feet * 12 + inches;

                    float weight = Float.parseFloat(editTextWeight.getText().toString());
                    float targetWeight = Float.parseFloat(editTextTargetWeight.getText().toString());
                    String frequency = spinnerFrequency.getSelectedItem().toString();
                    String level = spinnerLevel.getSelectedItem().toString();

                    profileController.updateProfile(age, gender, totalInches, weight, targetWeight, frequency, level);


                    Toast.makeText(ProfileActivity.this, "Profile updated successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ProfileActivity.this, FitnessGoalActivity.class);
                    startActivity(intent);
                } catch (NumberFormatException e) {
                    Toast.makeText(ProfileActivity.this, "Please fill in all fields correctly.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setupSpinner(Spinner spinner, int arrayResourceId) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                arrayResourceId, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}
