package com.example.clotheshopapp.MainDisplay.Adminstrative;

import android.content.Context;

import androidx.lifecycle.LifecycleOwner;

import com.example.clotheshopapp.MainDisplay.MainActivity;

abstract class Subject {
    public abstract void doSomeWork(LifecycleOwner lifecycleOwner, Context context);
}
