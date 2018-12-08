package com.whatsclone.muhammadfaizan.travelguard.Model;

public class LoginModel implements ILoginModel {
    private String userName;
    private String userPass;
    private String userPassReenter;

    public LoginModel(String userName, String userPass, String userPassReenter) {
        this.userName = userName;
        this.userPass = userPass;
        this.userPassReenter = userPassReenter;
    }

    private String getUserName() {
        return this.userName;
    }

    private String getUserPass() {
        return this.userPass;
    }

    private String getUserPassReenter(){
        return this.userPassReenter;
    }

    @Override
    public boolean validateCredentials() {
        if (!getUserName().equals("") && !getUserPass().equals("") && getUserPassReenter().equals(getUserPass()) && getUserName().matches("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$") && getUserPass().length() >= 6) {
            return true;
        } else {
            return false;
        }
    }
}
