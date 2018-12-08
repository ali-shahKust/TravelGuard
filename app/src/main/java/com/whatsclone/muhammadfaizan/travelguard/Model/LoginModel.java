package com.whatsclone.muhammadfaizan.travelguard.Model;

import android.support.annotation.Nullable;

public class LoginModel implements ILoginModel {
    private String userName;
    private String userPass;
    private String  rePass;

    public LoginModel(String userName, String userPass) {
        this.userName = userName;
        this.userPass = userPass;
    }

    public LoginModel(String userName, String userPass, String rePass){
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

    private String getRePass(){
        return this.rePass;
    }


    @Override
    public boolean validateSigninCredentials() {
        if (!getUserName().equals("") && !getUserPass().equals("") && getUserName().matches("^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$") && getUserPass().length() >= 6) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean validateRegisterCredentials() {
        if (!getUserName().equals("") && !getUserPass().equals("") && !getRePass().equals("")
                && getUserName().matches("^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$") && getUserPass().length() >= 6) {
            return true;
        } else {
            return false;
        }
    }
}
