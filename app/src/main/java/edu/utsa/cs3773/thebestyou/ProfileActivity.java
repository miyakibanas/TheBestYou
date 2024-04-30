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

import java.util.Locale;

import edu.utsa.cs3773.thebestyou.controller.ProfileController;
import edu.utsa.cs3773.thebestyou.model.UserProfile;
import edu.utsa.cs3773.thebestyou.utils.PreferenceManager;

public class ProfileActivity extends AppCompatActivity {

    private ProfileController profileController;
    private PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileController = new ProfileController(this);
        preferenceManager = new PreferenceManager(this);

        final EditText editTextName = findViewById(R.id.editTextName);
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

        btnNextToGoals.setOnClickListener(view -> {
            try {
                String name = editTextName.getText().toString().trim();
                int age = Integer.parseInt(editTextAge.getText().toString());
                String gender = spinnerGender.getSelectedItem().toString();
                String heightInput = editTextHeight.getText().toString().trim();
                float weight = Float.parseFloat(editTextWeight.getText().toString());
                float targetWeight = Float.parseFloat(editTextTargetWeight.getText().toString());
                String frequency = spinnerFrequency.getSelectedItem().toString();
                String level = spinnerLevel.getSelectedItem().toString();

                boolean success = profileController.updateProfile(name, age, gender, heightInput, weight, targetWeight, frequency, level);

                if (!success) {
                    Toast.makeText(ProfileActivity.this, "Please fill in all fields correctly.", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(ProfileActivity.this, "Profile updated successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ProfileActivity.this, FitnessGoalActivity.class);
                intent.putExtra("selectedLevel", level);
                startActivity(intent);
            } catch (NumberFormatException e) {
                Toast.makeText(ProfileActivity.this, "Please fill in all fields correctly.", Toast.LENGTH_SHORT).show();
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