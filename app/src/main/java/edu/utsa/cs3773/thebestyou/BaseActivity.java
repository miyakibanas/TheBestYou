package edu.utsa.cs3773.thebestyou;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    protected boolean shouldPerformSessionCheck = true;
    @Override
    protected void onStart() {
        super.onStart();
        checkSession();
    }

    private void checkSession() {
        if (!UserSessionManager.isUserLoggedIn()) {
            startActivity(new Intent(this, LogInActivity.class));
            finish();
        }
    }
}