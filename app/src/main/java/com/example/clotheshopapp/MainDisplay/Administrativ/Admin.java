package com.example.clotheshopapp.MainDisplay.Administrativ;

import android.util.Log;

import java.util.ArrayList;

public class Admin extends Subject{
    static Subject cs;
    String currentUser;
    ArrayList<String> registeredUser;
    private static final String TAG = "Administrative";

    public Admin(String currentUser) {
        registeredUser = new ArrayList<>();
        registeredUser.add("Admin");
        registeredUser.add("User1");
        registeredUser.add("User2");
        registeredUser.add("User3");
        this.currentUser = currentUser;
    }

    @Override
    public void doSomeWorkForAdmin() {
        if (registeredUser.contains(currentUser)){
            if (cs == null){
                cs = new ConcreteSubject();
            }
            cs.doSomeWorkForAdmin();
        }else {
            Log.i(TAG, "doSomeWorkForAdmin: current User isn't allow"+currentUser);
        }
    }
}
