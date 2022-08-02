package com.example.clotheshopapp.MainDisplay.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.clotheshopapp.R;

public class LoggingActivity extends AppCompatActivity {

    private TextView admin_text_view;
    private TextView logging_text_view;
    private static final String TAG = "LoggingActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loggin);

        admin_text_view = findViewById(R.id.admin_text_view);
        logging_text_view = findViewById(R.id.logging_text_view);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout_logging, new LoginFragment());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        logging_text_view.setTextColor(getResources().getColor(R.color.combineColor));
        logging_text_view.setTypeface(null, Typeface.BOLD);

        admin_text_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new AdminFragment(), "AdminFragment");
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
            logging_text_view.setTextColor(getResources().getColor(R.color.combineColor));
            logging_text_view.setTypeface( null, Typeface.BOLD);
            admin_text_view.setTextColor(Color.BLACK);
            admin_text_view.setTypeface( null, Typeface.NORMAL);
        }else {
            admin_text_view.setTextColor(getResources().getColor(R.color.combineColor));
            admin_text_view.setTypeface( null, Typeface.BOLD);
            logging_text_view.setTextColor(Color.BLACK);
            logging_text_view.setTypeface( null, Typeface.NORMAL);
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout_logging, fragment);
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}