package com.whatsclone.muhammadfaizan.travelguard.Model;

public class LoginModel implements ILoginModel {
    private String userName;
    private String userPass;
    private String rePass;

    public LoginModel(String userName, String userPass) {
        this.userName = userName;
        this.userPass = userPass;
    }

    public LoginModel(String userName, String userPass, String rePass) {
        this.userName = userName;
        this.userPass = userPass;
        this.rePass = rePass;
    }

    private String getUserName() {
        return this.userName;
    }

    private String getUserPass() {
        return this.userPass;
    }

    private String getRePass() {
        return this.rePass;
    }


    @Override
    public boolean validateSigninCredentials() {
        if (!getUserName().equals("") && !getUserPass().equals("") && getUserPass().length() >= 6
                && getUserName().matches("^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean validateRegisterCredentials() {
        if (!getUserName().equals("") && !getUserPass().equals("") && !getRePass().equals("") && getUserPass().length() >= 6
                && getRePass().equals(getUserPass()) && getUserName().matches("^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$")) {
            return true;
        } else {
            return false;
        }
    }
}
