package com.example.dailylife.controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.example.dailylife.DBHelper;
import com.example.dailylife.model.UserModel;
import com.example.dailylife.view.ISignInView;
import com.example.dailylife.view.ISignUpView;

import org.w3c.dom.Text;

public class LoginController implements ILoginController  {
    ISignUpView signUpView;
    DBHelper dbHelper;
    ISignInView signInView;

    public LoginController(ISignUpView signUpView, DBHelper dbHelper,ISignInView signInView) {
        this.signUpView = signUpView;
        this.dbHelper = dbHelper;
        this.signInView = signInView;


    }

    @Override
    public boolean onSignUp(String name, String email, String password) {
        UserModel user = new UserModel(name,email,password);
        /*NOTE: ErrorCode
        * 1:Minimum 25 characters
        * 2:Invalid Email
        * 3:Minimum 6 characters*/
        if(!user.isUserNameValid()) {
            signUpView.onSignUpFailure(1);
        }
        if(!user.isEmailValid()){
            signUpView.onSignUpFailure(2);
        }
        if(!user.isPasswordValid()){
            signUpView.onSignUpFailure(3);
        }
        if(!checkAlreadyExistsAccount(user)){
            signUpView.onSignUpFailure(4);
        }
        if(user.isUserNameValid()&&user.isEmailValid() && user.isPasswordValid()&&checkAlreadyExistsAccount(user))  {
            signUpView.onSignUpSuccess(0);
            insertUser(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean onSignIn(String email, String password) {
        if(TextUtils.isEmpty(email)){
            signInView.onSignInFailure(1);
        }else if(!checkAlreadyExistsEmail(email)){
            signInView.onSignInFailure(2);
        }
        if(TextUtils.isEmpty(password)){
            signInView.onSignInFailure(3);
        } else if(!checkAlreadyExistsPassword(password)){
            signInView.onSignInFailure(4);
        }
        if(checkAlreadyExistsEmail(email)&&checkAlreadyExistsPassword(password)){
            signInView.onSignInSuccess(0);
            return true;
        }
        return false;
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

    public Boolean checkAlreadyExistsAccount(UserModel user){
        SQLiteDatabase db = this.dbHelper.getReadableDatabase();
        String select = "SELECT * FROM "+DBHelper.TBL_USER+" WHERE Name = ? AND Email = ? AND Password = ?";
        Cursor cursor = db.rawQuery(select,new String[]{user.getUserName(), user.getEmail(),user.getPassword()});
        if(cursor.getCount() > 0) return false;
        else return true;
    }

    public Boolean checkAlreadyExistsEmail(String email){
        SQLiteDatabase db = this.dbHelper.getReadableDatabase();
        String select = "SELECT * FROM "+DBHelper.TBL_USER+" WHERE Email = ?";
        Cursor cursor = db.rawQuery(select,new String[]{email});
        if(cursor.getCount() > 0) return true;
        else return false;
    }

    public Boolean checkAlreadyExistsPassword(String password){
        SQLiteDatabase db = this.dbHelper.getReadableDatabase();
        String select = "SELECT * FROM "+DBHelper.TBL_USER+" WHERE Password = ?";
        Cursor cursor = db.rawQuery(select,new String[]{password});
        if(cursor.getCount() > 0) return true;
        else return false;
    }

    public int getID(String email,String password){
        SQLiteDatabase db = this.dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+DBHelper.TBL_USER,null);
        while (cursor.moveToNext()){
            if(email.equals(cursor.getString(2))&&password.equals(cursor.getString(3)))
                return cursor.getInt(0);
        }
        return -1;
    }
}
