package edu.utsa.cs3773.thebestyou.controller;

import edu.utsa.cs3773.thebestyou.model.CreateAccount;
import java.util.ArrayList;
import java.util.List;

public class AccountController {
    private List<CreateAccount> accountsList;

    public AccountController() {
        this.accountsList = new ArrayList<>();
    }

    public boolean createAccount(String email, String password, String phoneNumber) {
        // Basic validation (you can expand this according to your requirements)
        if (email.isEmpty() || password.isEmpty() || phoneNumber.isEmpty()) {
            return false;
        }
        // Create and add the new account
        CreateAccount newAccount = new CreateAccount(email, password, phoneNumber);
        accountsList.add(newAccount);
        return true;
    }

    public List<CreateAccount> getAccountsList() {
        return accountsList;
    }
}