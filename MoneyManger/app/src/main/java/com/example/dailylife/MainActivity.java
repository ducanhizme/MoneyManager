package com.example.dailylife;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.example.dailylife.databinding.ActivityMainBinding;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.background));
        View view = binding.getRoot();
        setContentView(view);
        dbHelper = new DBHelper(this);
        setUserName();
        setGreet();

    }

    public void setUserName(){
        int idUser = getIdTransition();
        Log.d("Extra",idUser+"");
        String userName = getNameDB(Integer.toString(idUser));
        binding.nameDisplay.setText(userName);
    }
    public String getNameDB(String id){
        return dbHelper.getName(id);
    }
    private int getIdTransition(){
        Intent intent = getIntent();
        return intent.getIntExtra(SignInSignUpForm.ID_USER_MSG,0);
    }



    private void setGreet(){
        String greeting = "Welcome";
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
        if(timeOfDay >= 0 && timeOfDay < 12){
             greeting = "Good morning";
        }else if(timeOfDay <= 16){
            greeting = "Good Afternoon";
        }else if(timeOfDay <= 21){
            greeting = "Good Evening";
        }else if(timeOfDay <= 24){
            greeting ="Good Night";
        }
        binding.greet.setText(greeting);
    }

}