package com.example.clotheshopapp.MainDisplay.login;

import android.content.Context;
import android.util.Log;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.clotheshopapp.MainDisplay.RoomDatabase.Model.UserDataObj;
import com.example.clotheshopapp.MainDisplay.RoomDatabase.Singleton.MySingletonUser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextWatcherHelper {
    private static final String TAG = "TextWatcherHelper";
    private Context context;
    private LifecycleOwner lifecycleOwner;
    public TextWatcherHelper(Context context, LifecycleOwner lifecycleOwner) {
        this.context = context;
        this.lifecycleOwner = lifecycleOwner;
    }

    public void verifyEmailAsAdmin(String emailInput) {

        LiveData<List<UserDataObj>> getUserName = MySingletonUser.getInstance(context)
                .userDao()
                .userFindByEmail(emailInput);

        getUserName.observe(lifecycleOwner, new Observer<List<UserDataObj>>() {
            @Override
            public void onChanged(List<UserDataObj> userDataObjs) {
                try{

                    Log.i(TAG, "You are Admin, you can access Data");
                    Log.i(TAG, "user449: "+userDataObjs);

                    /*FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frameLayout_logging, new LoginFragment());
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();*/

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }



    public boolean verifyStrongPassword(String passwordInput) {
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
