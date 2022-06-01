package com.example.dailylife.model;

public interface IUser {
    String getUserName();
    String getEmail();
    String getPassword();
    boolean isUserNameValid();
    boolean isEmailValid();
    boolean isPasswordValid();
}
