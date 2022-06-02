package com.example.dailylife.controller;

public interface ILoginController {
    boolean onSignUp(String name,String email,String password);
    boolean onSignIn(String email,String password);
}
