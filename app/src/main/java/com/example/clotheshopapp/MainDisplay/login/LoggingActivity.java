package com.example.clotheshopapp.MainDisplay.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.clotheshopapp.R;

public class LoggingActivity extends AppCompatActivity {

    TextView signup_text_view;
    TextView logging_text_view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loggin);

        signup_text_view = findViewById(R.id.signup_text_view);
        logging_text_view = findViewById(R.id.logging_text_view);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout_logging, new LoginFragment());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        signup_text_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new SignUpFragment());
            }


        });

        logging_text_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new LoginFragment());
            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout_logging, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }




}