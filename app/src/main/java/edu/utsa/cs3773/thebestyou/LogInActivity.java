package edu.utsa.cs3773.thebestyou;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import edu.utsa.cs3773.thebestyou.controller.AccountController;
import edu.utsa.cs3773.thebestyou.utils.PreferenceManager;

public class LogInActivity extends AppCompatActivity {
    private EditText editTextEmail, editTextPassword;
    private Button btnLogin;
    private AccountController accountController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        accountController = TheBestYouApp.getAccountController();

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> {
            String email = editTextEmail.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();

            if (accountController.login(email, password)) {
                loginUser(email);
            } else {
                Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loginUser(String userEmail) {
        UserSessionManager.setUserEmail(userEmail);
        Intent intent = new Intent(LogInActivity.this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }
}

