package com.example.clotheshopapp.MainDisplay.login;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.clotheshopapp.R;

public class LoginFragment extends Fragment {

    EditText emailLogInEditText;
    EditText passwordLogInEditText;
    private static final String TAG = "LoginFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.log_in_fragment, container, false);
        setElement(view);
        return view;
    }

    private void setElement(View view) {
        emailLogInEditText = view.findViewById(R.id.emailLogInEditText);
        passwordLogInEditText = view.findViewById(R.id.passwordAccountTextView);

    }

}
