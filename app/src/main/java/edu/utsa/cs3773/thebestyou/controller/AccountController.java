package edu.utsa.cs3773.thebestyou.controller;

import edu.utsa.cs3773.thebestyou.model.CreateAccount;
import java.util.ArrayList;
import java.util.List;

public class AccountController {
    private List<CreateAccount> accountsList;

    public AccountController() {
        this.accountsList = new ArrayList<>();
    }

    public boolean createAccount(String email, String password, String reenteredPassword, String phoneNumber) {
        // Check for empty fields
        if (email.isEmpty() || password.isEmpty() || phoneNumber.isEmpty() || !password.equals(reenteredPassword)) {
            return false;
        }
        // Email validation
        if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            return false;
        }
        // Phone number validation (assuming US numbers)
        if (!phoneNumber.matches("\\d{10}")) {
            return false;
        }

        CreateAccount newAccount = new CreateAccount(email, password, phoneNumber);
        accountsList.add(newAccount);
        return true;
    }

    public List<CreateAccount> getAccountsList() {
        return accountsList;
    }
}
