package com.whatsclone.muhammadfaizan.travelguard.Model;

public class LoginModel implements ILoginModel {
    private String userName;
    private String userPass;

    public LoginModel(String userName, String userPass) {
        this.userName = userName;
        this.userPass = userPass;
    }

    private String getUserName() {
        return this.userName;
    }

    private String getUserPass() {
        return this.userPass;
    }


    @Override
    public boolean validateCredentials() {
        if (!getUserName().equals("") && !getUserPass().equals("") && getUserName().matches("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$") && getUserPass().length() >= 6) {
            return true;
        } else {
            return false;
        }
    }
}
