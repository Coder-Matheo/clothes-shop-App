package com.example.clotheshopapp.MainDisplay.Administrativ;

import android.util.Log;

public class ConcreteSubject extends Subject{
    private static final String TAG = "ConcreteSubject";
    @Override
    public void doSomeWorkForAdmin() {
        Log.i(TAG, "doSomeWorkForAdmin() inside ConcreteSubject is invoked: ");
    }
}
