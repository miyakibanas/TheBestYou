package edu.utsa.cs3773.thebestyou;

public class UserSessionManager {
    private static String currentUserEmail;
    private static boolean isNewUser = true;

    public static void setUserEmail(String email) {
        currentUserEmail = email;
        isNewUser = false;
    }

    public static String getUserEmail() {
        return currentUserEmail;
    }

    public static boolean isUserLoggedIn() {
        return currentUserEmail != null;
    }

    public static void clearUserSession() {
        currentUserEmail = null;
        isNewUser = true;
    }

    public static boolean isNewUser() {
        return isNewUser;
    }
}

