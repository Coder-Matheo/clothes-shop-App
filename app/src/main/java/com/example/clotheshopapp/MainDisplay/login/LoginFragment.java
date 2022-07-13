package com.example.clotheshopapp.MainDisplay.login;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.clotheshopapp.R;

public class LoginFragment extends Fragment {

    EditText emailLogInEditText;
    EditText passwordLogInEditText;
    Button LogInButton;
    private static final String TAG = "LoginFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.log_in_fragment, container, false);
        emailLogInEditText = view.findViewById(R.id.emailLogInEditText);
        passwordLogInEditText = view.findViewById(R.id.passwordLogInEditText);

        passwordLogInEditText.addTextChangedListener(new GenericTextWatcher(passwordLogInEditText));
        emailLogInEditText.addTextChangedListener(new GenericTextWatcher(emailLogInEditText));


        LogInButton = view.findViewById(R.id.LogInButton);

        return view;
    }





}
