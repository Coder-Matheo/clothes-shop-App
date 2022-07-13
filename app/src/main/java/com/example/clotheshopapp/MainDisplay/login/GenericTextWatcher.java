package com.example.clotheshopapp.MainDisplay.login;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import com.example.clotheshopapp.R;

import java.util.ArrayList;
import java.util.List;

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
                    ls.add(text);
                    break;
                case R.id.passwordLogInEditText:
                    Log.i(TAG, "passwordLogInEditText: "+text);
                    set();
                    break;
                case R.id.emailSignUpEditText:
                    Log.i(TAG, "emailSignUpEditText: "+text);
                    break;
                case R.id.passwordSignUpEditText:
                    Log.i(TAG, "passwordSignUpEditText: "+text);
                    break;
                case R.id.passwordRepeatSignUpEditText:
                    Log.i(TAG, "passwordRepeatSignUpEditText: "+text);
                    break;

            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }
    }

    private void set() {
        Log.i(TAG, "set: "+ls);
    }


}
