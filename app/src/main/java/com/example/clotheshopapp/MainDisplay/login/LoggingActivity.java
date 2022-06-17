package com.example.clotheshopapp.MainDisplay.login;

import static android.graphics.Color.GREEN;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.clotheshopapp.R;

public class LoggingActivity extends AppCompatActivity {

    TextView signup_text_view;
    TextView logging_text_view;
    private static final String TAG = "LoggingActivity";


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

        logging_text_view.setTextColor(getResources().getColor(R.color.baseColor));
        logging_text_view.setTypeface(null, Typeface.BOLD);

        signup_text_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new SignUpFragment(), "SignUpFragment");
            }
        });

        logging_text_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new LoginFragment(), "LoginFragment");
            }
        });

    }

    private void replaceFragment(Fragment fragment, String nameOfFragment) {

        if (nameOfFragment.equals("LoginFragment") ){
            logging_text_view.setTextColor(getResources().getColor(R.color.baseColor));
            logging_text_view.setTypeface( null, Typeface.BOLD);
            signup_text_view.setTextColor(Color.BLACK);
            signup_text_view.setTypeface( null, Typeface.NORMAL);
        }else {
            signup_text_view.setTextColor(getResources().getColor(R.color.baseColor));
            signup_text_view.setTypeface( null, Typeface.BOLD);
            logging_text_view.setTextColor(Color.BLACK);
            logging_text_view.setTypeface( null, Typeface.NORMAL);
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout_logging, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }




}