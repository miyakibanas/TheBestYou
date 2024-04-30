package edu.utsa.cs3773.thebestyou;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import edu.utsa.cs3773.thebestyou.controller.AccountController;
import edu.utsa.cs3773.thebestyou.model.CreateAccount;
import edu.utsa.cs3773.thebestyou.model.RewardSystem;
import edu.utsa.cs3773.thebestyou.model.UserPointsManager;

public class CreateAccountActivity extends AppCompatActivity {

    private AccountController accountController;
    private UserPointsManager userPointsManager;
    private RewardSystem rewardSystem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        accountController = TheBestYouApp.getAccountController();

        // Initialize UserPointsManager and RewardSystem
        // User earns points for creating an account
        userPointsManager = UserPointsManager.getInstance(this);
        rewardSystem = new RewardSystem(this, userPointsManager);

        EditText Email = findViewById(R.id.Email);
        EditText Password = findViewById(R.id.Password);
        EditText ReenterPassword = findViewById(R.id.ReenterPassword);
        EditText PhoneNumber = findViewById(R.id.PhoneNumber);
        Button btnCreateAccount = findViewById(R.id.btnCreateAccount);

        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (accountController == null) {
                    Toast.makeText(CreateAccountActivity.this, "Account creation service is unavailable. Please restart the app.", Toast.LENGTH_LONG).show();
                    return; // Early exit if accountController is null
                }

                String email = Email.getText().toString().trim();
                String password = Password.getText().toString().trim();
                String reenteredPassword = ReenterPassword.getText().toString().trim();
                String phoneNumber = PhoneNumber.getText().toString().trim();

                if (!password.equals(reenteredPassword)) {
                    Toast.makeText(CreateAccountActivity.this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
                    return; // Stop further execution if passwords don't match
                }

                if (accountController.createAccount(email, password, reenteredPassword, phoneNumber)) {
                    UserSessionManager.setUserEmail(email);
                    Toast.makeText(CreateAccountActivity.this, "Account created successfully!", Toast.LENGTH_SHORT).show();
                    addPointsForCreatingAccount();

                    Intent intent = new Intent(CreateAccountActivity.this, ProfileActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(CreateAccountActivity.this, "Please enter all details correctly.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void addPointsForCreatingAccount() {
        // Add points for creating an account based on your criteria
        int pointsToAdd = calculatePointsForCreatingAccount();
        userPointsManager.addPoints(pointsToAdd);
    }

    // Method to calculate points for creating an account
    private int calculatePointsForCreatingAccount() {
        // Calculate points for creating an account based on your criteria
        return 100; // For example, you can give 100 points for creating an account
    }
}