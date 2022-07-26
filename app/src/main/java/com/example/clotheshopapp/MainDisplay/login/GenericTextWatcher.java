package com.example.clotheshopapp.MainDisplay.login;

import android.app.Application;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.clotheshopapp.MainDisplay.MainActivity;
import com.example.clotheshopapp.MainDisplay.RoomDatabase.DataViewModel;
import com.example.clotheshopapp.MainDisplay.RoomDatabase.Model.UserDataObj;
import com.example.clotheshopapp.MainDisplay.RoomDatabase.Singleton.MySingletonUser;
import com.example.clotheshopapp.R;

import java.util.HashMap;
import java.util.List;


class GenericTextWatcher extends Application implements TextWatcher{
    private static final String TAG = "GenericTextWatcher";
    private TextWatcherHelper textWatcherHelper;
    private LifecycleOwner lifecycleOwner;
    private Context context;
    private View view;
    Button inputButton;



    GenericTextWatcher(View view,Button inputButton,LifecycleOwner lifecycleOwner, Context context){

        textWatcherHelper = new TextWatcherHelper(context, lifecycleOwner);
        this.lifecycleOwner = lifecycleOwner;
        this.view = view;
        this.inputButton = inputButton;
        this.context = context;
    }
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    String text;
    @Override
    public void afterTextChanged(Editable editable) {
        text = editable.toString();

        switch (this.view.getId()){
            case R.id.emailLogInEditText:
                if (textWatcherHelper.validationEmail(text)){
                    Log.i(TAG, "email verified true: "+ text);
                    //textWatcherHelper.verifyEmailAsAdmin(text);

                }
                break;
            case R.id.passwordLogInEditText:
                if (textWatcherHelper.verifyStrongPassword(text)){
                    Log.i(TAG, "Password Verified true: "+ text);

                }
                break;
            case R.id.emailSignUpEditText:
                if (textWatcherHelper.validationEmail(text)){
                    Log.i(TAG, "email verified true: "+ text);

                }
                break;
            case R.id.passwordSignUpEditText:
                if (textWatcherHelper.verifyStrongPassword(text)){
                    Log.i(TAG, "Password Strong Verified true: "+ text);
                }
                break;
            case R.id.passwordRepeatSignUpEditText:
                Log.i(TAG, "passwordRepeatSignUpEditText: "+text);
                break;

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
                            break;
                        case R.id.existAccountSignUpButton:
                            Log.i(TAG, "existAccountSignUpButton: ");
                            break;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }


}


