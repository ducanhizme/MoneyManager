package com.example.dailylife.controller;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dailylife.DBHelper;
import com.example.dailylife.model.UserModel;
import com.example.dailylife.view.ISignUpView;

import java.util.ArrayList;

public class SignUpController implements ISignUpController {
    ISignUpView loginView;
    DBHelper dbHelper;

    public SignUpController(ISignUpView loginView,DBHelper dbHelper) {
        this.loginView = loginView;
        this.dbHelper = dbHelper;


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
        if(!checkAlreadyExistsAccount(user)){
            loginView.onSignUpFailure(4);
        }
        if(user.isUserNameValid()&&user.isEmailValid() && user.isPasswordValid()&&checkAlreadyExistsAccount(user))  {
            loginView.onSignUpSuccess(0);
            insertUser(user);
        }
    }

    public boolean insertUser(UserModel user){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values =new ContentValues();
        values.put("Name",user.getUserName());
        values.put("Email",user.getEmail());
        values.put("Password",user.getPassword());
        long result = db.insert(DBHelper.TBL_USER,null,values);
        if(result ==-1) return false;
        else return true;
    }

//    public ArrayList<UserModel> select(){
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        String sql ="SELECT * FROM "+ DBHepler.TBL_COURSE + " ORDER BY year DESC";
//    }

    public Boolean checkAlreadyExistsAccount(UserModel user){
        SQLiteDatabase db = this.dbHelper.getReadableDatabase();
        String insert_user = "SELECT * FROM "+DBHelper.TBL_USER+" WHERE Name = ? AND Email = ? AND Password = ?";
        Cursor cursor = db.rawQuery(insert_user,new String[]{user.getUserName(), user.getEmail(),user.getPassword()});
        if(cursor.getCount() > 0) return false;
        else return true;
    }



}
