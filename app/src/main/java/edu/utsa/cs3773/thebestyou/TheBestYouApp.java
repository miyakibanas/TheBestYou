package edu.utsa.cs3773.thebestyou;

import android.app.Application;

import edu.utsa.cs3773.thebestyou.controller.AccountController;

public class TheBestYouApp extends Application {
    private static AccountController accountController;

    @Override
    public void onCreate() {
        super.onCreate();
        accountController = new AccountController(this);
    }

    public static AccountController getAccountController() {
        return accountController;
    }
}
