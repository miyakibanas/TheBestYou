package edu.utsa.cs3773.thebestyou;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

import edu.utsa.cs3773.thebestyou.controller.ProfileController;
import edu.utsa.cs3773.thebestyou.model.UserProfile;

public class UserProfileSettingActivity extends AppCompatActivity {

    private ProfileController profileController;
    private EditText editTextName, editTextAge, editTextHeight, editTextWeight, editTextTargetWeight;
    private Spinner spinnerGender, spinnerFrequency, spinnerLevel;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_setting);

        initializeUI();
        setupSpinners();
        profileController = new ProfileController(this);

        loadUserData();
        configureSaveButton();
    }

    private void initializeUI() {
        // Initialize your UI components here
        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
        spinnerGender = findViewById(R.id.spinnerGender);
        editTextHeight = findViewById(R.id.editTextHeight);
        editTextWeight = findViewById(R.id.editTextWeight);
        editTextTargetWeight = findViewById(R.id.editTextTargetWeight);
        spinnerFrequency = findViewById(R.id.spinnerFrequency);
        spinnerLevel = findViewById(R.id.spinnerLevel);
        btnSave = findViewById(R.id.btnSave);
    }

    private void setupSpinners() {
        setupSpinner(spinnerGender, R.array.gender_options);
        setupSpinner(spinnerFrequency, R.array.frequency_options);
        setupSpinner(spinnerLevel, R.array.level_options);
    }

    private void loadUserData() {
        UserProfile userProfile = profileController.getUserProfile();
        if (userProfile != null) {
            editTextName.setText(userProfile.getName());
            editTextAge.setText(String.valueOf(userProfile.getAge()));
            setSpinnerToValue(spinnerGender, userProfile.getGender());
            editTextHeight.setText(convertHeightToFeetInches(userProfile.getHeightInches()));
            editTextWeight.setText(String.format(Locale.getDefault(), "%.1f", userProfile.getWeight()));
            editTextTargetWeight.setText(String.format(Locale.getDefault(), "%.1f", userProfile.getTargetWeight()));
            setSpinnerToValue(spinnerFrequency, userProfile.getFrequency());
            setSpinnerToValue(spinnerLevel, userProfile.getLevel());
        }
    }

    private void configureSaveButton() {
        btnSave.setOnClickListener(v -> {
            if (updateUserProfileFromUI()) {
                Toast.makeText(this, "Profile updated successfully.", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Please check your input.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean updateUserProfileFromUI() {
        try {
            // Get data from UI and update profile
            return profileController.updateProfile(
                    editTextName.getText().toString().trim(),
                    Integer.parseInt(editTextAge.getText().toString().trim()),
                    spinnerGender.getSelectedItem().toString(),
                    editTextHeight.getText().toString().trim(),
                    Float.parseFloat(editTextWeight.getText().toString().trim()),
                    Float.parseFloat(editTextTargetWeight.getText().toString().trim()),
                    spinnerFrequency.getSelectedItem().toString(),
                    spinnerLevel.getSelectedItem().toString()
            );
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please ensure all numerical fields are correctly filled.", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void setupSpinner(Spinner spinner, int arrayResourceId) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                arrayResourceId, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void setSpinnerToValue(Spinner spinner, String value) {
        ArrayAdapter<CharSequence> adapter = (ArrayAdapter<CharSequence>) spinner.getAdapter();
        if (adapter != null) {
            int position = adapter.getPosition(value);
            spinner.setSelection(position >= 0 ? position : 0);
        }
    }

    private String convertHeightToFeetInches(int inches) {
        int feet = inches / 12;
        int remainingInches = inches % 12;
        return String.format(Locale.getDefault(), "%d' %d\"", feet, remainingInches);
    }
}
