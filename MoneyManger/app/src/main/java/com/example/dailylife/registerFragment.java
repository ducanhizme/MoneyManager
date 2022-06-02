package com.example.dailylife;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dailylife.controller.ILoginController;
import com.example.dailylife.controller.LoginController;
import com.example.dailylife.databinding.FragmentRegisterBinding;
import com.example.dailylife.view.ISignUpView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link registerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class registerFragment extends Fragment implements ISignUpView {

    private FragmentRegisterBinding binding_;
    private LoginController signUpController;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Context context;
    public DBHelper db;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public registerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment registerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static registerFragment newInstance(String param1, String param2) {
        registerFragment fragment = new registerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding_=FragmentRegisterBinding.inflate(inflater,container,false);
        return binding_.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db = new DBHelper(getContext());
        signUpController = new LoginController(this,db,null);
        binding_.btnSignUp.setOnClickListener(view_ ->{
            boolean check = signUpController.onSignUp(binding_.userName.getText().toString(),
                                                      binding_.SignUpEmailAddress.getText().toString(),
                                                      binding_.SignUpPassword.getText().toString());
            if(check){
                int id_ = signUpController.getID(binding_.SignUpEmailAddress.getText().toString(),binding_.SignUpPassword.getText().toString());
                Intent intent = new Intent(getContext(), MainActivity.class);
                intent.putExtra(SignInSignUpForm.ID_USER_MSG,id_);
            }
        });
    }

    @Override
    public void onSignUpFailure(int errorCode) {
        /*NOTE: ErrorCode
         * 1:Minimum 25 characters
         * 2:Invalid Email
         * 3:Minimum 6 characters*/
        switch (errorCode) {
            case 1:
                binding_.txtLayoutUsername.setError(getString(R.string.user_name_error));
                break;
            case 2:
                binding_.txtLayoutEmail.setError(getString(R.string.email_error));
                break;
            case 3:
                binding_.txtLayoutPassword.setError(getString(R.string.password_error));
                break;
            case 4:
                Toast.makeText(getContext(),getString(R.string.account_error),Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onSignUpSuccess(int successCode) {
        if(successCode ==0){
            binding_.txtLayoutUsername.setError(null);
            binding_.txtLayoutEmail.setError(null);
            binding_.txtLayoutPassword.setError(null);
        }
    }
}