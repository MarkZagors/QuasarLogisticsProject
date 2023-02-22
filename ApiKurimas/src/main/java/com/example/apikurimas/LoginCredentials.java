package com.example.apikurimas;

public class LoginCredentials {
    private boolean isAdmin;
    private boolean isAuthenticated;
    private int userIndex;
    private String userType;

    public LoginCredentials(boolean isAuthenticated, int userIndex, String userType, boolean isAdmin) {
        this.isAuthenticated = isAuthenticated;
        this.userIndex = userIndex;
        this.userType = userType;
        this.isAdmin = isAdmin;
    }

    public String getCredentials() {
        return isAuthenticated + "," + userIndex + "," + userType + "," + isAdmin;
    }
}
