package edu.utsa.cs3773.thebestyou.controller;

import android.content.Context;
import android.content.SharedPreferences;

import edu.utsa.cs3773.thebestyou.model.CreateAccount;
import java.util.ArrayList;
import java.util.List;

public class AccountController {
    private static final String ACCOUNT_PREF = "Accounts";
    private SharedPreferences sharedPreferences;

    public AccountController(Context context) {
        this.sharedPreferences = context.getSharedPreferences(ACCOUNT_PREF, Context.MODE_PRIVATE);
    }

    public boolean createAccount(String email, String password, String reenteredPassword, String phoneNumber) {
        if (email.isEmpty() || password.isEmpty() || phoneNumber.isEmpty() || !password.equals(reenteredPassword)) {
            return false;
        }
        if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            return false;
        }
        if (!phoneNumber.matches("\\d{10}")) {
            return false;
        }

        // Check if email already exists
        String existing = sharedPreferences.getString(email, null);
        if (existing != null) {
            return false;
        }

        // Save the new account
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(email, password);
        editor.apply();
        return true;
    }

    public boolean login(String email, String password) {
        String storedPassword = sharedPreferences.getString(email, null);
        return storedPassword != null && storedPassword.equals(password);
    }
}