package com.example.dailylife;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dailylife.controller.LoginController;
import com.example.dailylife.databinding.FragmentLoginBinding;
import com.example.dailylife.view.ISignInView;
import com.example.dailylife.view.ISignUpView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link loginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class loginFragment extends Fragment implements ISignInView {

    private FragmentLoginBinding binding_;
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public loginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment loginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static loginFragment newInstance(String param1, String param2) {
        loginFragment fragment = new loginFragment();
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
        binding_ = FragmentLoginBinding.inflate(inflater,container,false);
        return binding_.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DBHelper db = new DBHelper(getContext());
        LoginController lc = new LoginController(null,db,this);
        binding_.btnSignIn.setOnClickListener(view_ ->{
            boolean check = lc.onSignIn(binding_.emailAddress.getText().toString(),binding_.password.getText().toString());
            if(check){
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onSignInFailure(int errorCode) {
        switch (errorCode){
            case 1:
                binding_.layoutEmail.setError(getString(R.string.email_error));
                break;
            case 2:
                binding_.layoutEmail.setError(null);
                binding_.layoutEmail.setError(getString(R.string.email_error_sign_in));
                break;
            case 3:
                binding_.layoutPassword.setError(getString(R.string.password_error));
                break;
            case 4:
                binding_.layoutPassword.setError(null);
                binding_.layoutPassword.setError(getString(R.string.password_error_sign_in));
        }
    }

    @Override
    public void onSignInSuccess(int sussesCode) {
        binding_.layoutEmail.setError(null);
        binding_.layoutPassword.setError(null);
    }
}