package edu.utsa.cs3773.thebestyou;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnCreateAccount = findViewById(R.id.btnCreateAccount);
        btnCreateAccount.setOnClickListener(v -> {
            // Navigate to CreateAccountActivity
            Intent intent = new Intent(MainActivity.this, CreateAccountActivity.class);
            startActivity(intent);
        });

        TextView txtLogin = findViewById(R.id.Login);
        txtLogin.setOnClickListener(v -> {
            // Navigate to LogInActivity
            Intent intent = new Intent(MainActivity.this, LogInActivity.class);
            startActivity(intent);
        });
    }
}
