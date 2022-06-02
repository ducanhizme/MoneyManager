package com.example.dailylife.model;

import android.text.TextUtils;
import android.util.Patterns;

import java.lang.reflect.Array;
import java.util.regex.Pattern;

public class UserModel implements IUser{
    private String userName;
    private String email;
    private String password;
    private final static int VALID_PASSWORD_CHARACTERS = 6;
    private final static int VALID_USERNAME_CHARACTERS = 25;

    public UserModel(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }


    @Override
    public String getUserName() {
        return this.userName;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isUserNameValid() {
        return !TextUtils.isEmpty(getUserName()) && getUserName().length() < VALID_USERNAME_CHARACTERS;
    }

    @Override
    public boolean isEmailValid() {
        return !TextUtils.isEmpty(getEmail()) && Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches();
    }

    @Override
    public boolean isPasswordValid() {
        return !TextUtils.isEmpty(getPassword()) && getPassword().length() >= VALID_PASSWORD_CHARACTERS;
    }

}
