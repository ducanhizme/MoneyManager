package com.example.dailylife;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.dailylife.databinding.ActivityMainBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private GoogleSignInOptions gso;
    private GoogleSignInClient gsc;
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.background));
        View view = binding.getRoot();
        setContentView(view);
        setDataGoogleSigned();
        setGreet();
    }

    private void setDataGoogleSigned(){
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account != null){
            String name = account.getDisplayName();
            Log.i("name",name);
            binding.nameDisplay.setText(name);
            Uri avatarPhotoUri = account.getPhotoUrl();
            if(avatarPhotoUri == null){
                binding.avatar.setImageResource(R.drawable.ic_baseline_person_24);
            }else{
                Picasso.get().load(avatarPhotoUri).into(binding.avatar);
            }

        }
    }

    private void setGreet(){
        String greeting = "Welcome";
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
        if(timeOfDay >= 0 && timeOfDay < 12){
             greeting = "Good morning";
        }else if(timeOfDay >= 12 && timeOfDay < 16){
            greeting = "Good Afternoon";
        }else if(timeOfDay >= 16 && timeOfDay < 21){
            greeting = "Good Evening";
        }else if(timeOfDay >= 21 && timeOfDay < 24){
            greeting ="Good Night";
        }
        binding.greet.setText(greeting);
    }

}