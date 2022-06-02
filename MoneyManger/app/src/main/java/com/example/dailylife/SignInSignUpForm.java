package com.example.dailylife;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.dailylife.databinding.ActivitySignInSignUpFormBinding;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Objects;

public class SignInSignUpForm extends AppCompatActivity {
    private ActivitySignInSignUpFormBinding binding;
    private ViewPagerAdapter vpa;
    public static final String ID_USER_MSG = "ID_USER";
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInSignUpFormBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        getWindow().setStatusBarColor(ContextCompat.getColor(SignInSignUpForm.this,R.color.background));
        setContentView(view);
        loadFragmentToViewPager();
    }

    private void loadFragmentToViewPager(){
        vpa = new ViewPagerAdapter(this);
        binding.viewPager.setAdapter(vpa);
        new TabLayoutMediator(binding.tabLayout,binding.viewPager,(tab, position) -> {
            switch (position){
                case 0:
                    tab.setText("Sign In");
                    break;
                case 1:
                    tab.setText("Sign Up");
                    break;
            }
        }).attach();
    }

}