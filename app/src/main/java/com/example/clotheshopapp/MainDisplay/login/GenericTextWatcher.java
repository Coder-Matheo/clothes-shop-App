package com.example.clotheshopapp.MainDisplay.login;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.clotheshopapp.MainDisplay.Administrativ.AdminControl;
import com.example.clotheshopapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


class GenericTextWatcher implements TextWatcher {

    private static final String TAG = "GenericTextWatcher";





    private View view;
    Button inputButton;
    GenericTextWatcher(View view,Button inputButton){
        this.view = view;
        this.inputButton = inputButton;

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

        String text = editable.toString();
        try{
            switch (this.view.getId()){
                case R.id.emailLogInEditText:
                    Log.i(TAG, "emailLogInEditText: "+text);
                    break;
                case R.id.passwordLogInEditText:

                    if (verifyStrongPassword(text)){

                        Log.i(TAG, "Password Verified true: "+ text);
                    }
                    break;
                case R.id.emailSignUpEditText:
                    if (validationEmail(text)){
                        Log.i(TAG, "email verified true: "+ text);
                    }
                    break;

                case R.id.passwordSignUpEditText:
                    if (verifyStrongPassword(text)){
                        Log.i(TAG, "Password Strong Verified true: "+ text);
                    }
                    break;
                case R.id.passwordRepeatSignUpEditText:
                    Log.i(TAG, "passwordRepeatSignUpEditText: "+text);
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        inputButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    switch (view.getId()){
                        case R.id.logInButton:
                            try{
                                Log.i(TAG, "Log In Button");
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            break;
                        case R.id.createAccountSignUpButton:
                            Log.i(TAG, "Create new account");
                            AdminControl adminControl = new AdminControl();

                            break;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }


    private boolean verifyStrongPassword(String passwordInput) {
        String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(passwordInput);

        /*if (passwordSignUpEditText == passwordRepeatSignUpEditText){
            Log.i(TAG, "onTextChanged: is equal");
        }*/

        List<String> passwordRepeatList = new ArrayList<>();
        if (matcher.matches()){
            Log.i(TAG, "onTextChanged: "+matcher.matches());

            return true;
        }
        return false;
    }

    public boolean validationEmail(String emailInput){
        Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailInput);
        //Log.i(TAG, "Email validated: "+matcher.find());
        boolean valid = matcher.find();
        if (valid){
            Log.i(TAG, "Valid Email");
            return true;
        }
        return false;
    }
}
