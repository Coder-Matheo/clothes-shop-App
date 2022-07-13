package com.example.clotheshopapp.MainDisplay.login;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import com.example.clotheshopapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class GenericTextWatcher implements TextWatcher {

    private static final String TAG = "GenericTextWatcher";

    private View view;
    GenericTextWatcher(View view){
        this.view = view;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }
    List<String> ls = new ArrayList<>();
    @Override
    public void afterTextChanged(Editable editable) {
        String text = editable.toString();
        try{
            switch (view.getId()){
                case R.id.emailLogInEditText:
                    Log.i(TAG, "emailLogInEditText: "+text);
                    validationEmail(text);
                    ls.add(text);
                    break;
                case R.id.passwordLogInEditText:
                    Log.i(TAG, "passwordLogInEditText: "+text);
                    set();
                    break;
                case R.id.emailSignUpEditText:
                    Log.i(TAG, "emailSignUpEditText: "+text);
                    validationEmail(text);
                    break;
                case R.id.passwordSignUpEditText:
                    verifyStrongPassword(text);
                    Log.i(TAG, "passwordSignUpEditText: "+text);
                    break;
                case R.id.passwordRepeatSignUpEditText:
                    Log.i(TAG, "passwordRepeatSignUpEditText: "+text);
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void verifyStrongPassword(String passwordInput) {
        String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(passwordInput);

        Log.i(TAG, "onTextChanged: "+matcher.matches());


        /*if (passwordSignUpEditText == passwordRepeatSignUpEditText){
            Log.i(TAG, "onTextChanged: is equal");
        }*/
    }

    public void validationEmail(String emailInput){
        Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);


        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailInput);
        Log.i(TAG, "Email validated: "+matcher.find());
        boolean valid = matcher.find();
        if (valid){
            Log.i(TAG, "Valid Email");
        }if(valid == true) {
            Log.i(TAG, "UnValid Email");
        }

    }


    private void set() {
        //Log.i(TAG, "set: "+ls);
    }


}
