package com.example.dailylife.controller;

import com.example.dailylife.model.UserModel;
import com.example.dailylife.view.ISignUpView;

public class SignUpController implements ISignUpController {
    ISignUpView loginView;

    public SignUpController(ISignUpView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void onSignUp(String name, String email, String password) {
        UserModel user = new UserModel(name,email,password);
        /*NOTE: ErrorCode
        * 1:Minimum 25 characters
        * 2:Invalid Email
        * 3:Minimum 6 characters*/
        if(!user.isUserNameValid()) {
            loginView.onSignUpFailure(1);
        }
        if(!user.isEmailValid()){
            loginView.onSignUpFailure(2);
        }
        if(!user.isPasswordValid()){
            loginView.onSignUpFailure(3);
        }
        if(user.isUserNameValid()&&user.isEmailValid()&&user.isPasswordValid()){
            loginView.onSignUpSuccess(0);
        }
    }
}
