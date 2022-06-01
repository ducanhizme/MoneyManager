package com.example.dailylife;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.dailylife.R;
import com.example.dailylife.databinding.ActivityLoginBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;


     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
         getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.background));
         setContentView(view);
         loginBtn();
    }

    private void loginBtn(){
        binding.loginBtn.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this,SignInSignUpForm.class);
            startActivity(intent);
        });
    }


}