package com.example.clotheshopapp.MainDisplay.Adminstrative;

import android.content.Context;
import android.os.AsyncTask;
import android.service.autofill.UserData;
import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.clotheshopapp.MainDisplay.MainActivity;
import com.example.clotheshopapp.MainDisplay.RoomDatabase.Model.ProductData;
import com.example.clotheshopapp.MainDisplay.RoomDatabase.Model.UserDataObj;
import com.example.clotheshopapp.MainDisplay.RoomDatabase.Singleton.MySingletonProduct;
import com.example.clotheshopapp.MainDisplay.RoomDatabase.Singleton.MySingletonUser;

import java.util.List;

public class ConcreteSubject extends Subject{
	//get View Life Cycle Owner
	private LifecycleOwner lifecycleOwner;
	private static final String TAG = "ConcreteSubject";
	private  Context context;

	//
	@Override
	public void doSomeWork(LifecycleOwner lifecycleOwner, Context context) {
		this.lifecycleOwner = lifecycleOwner;
		this.context = context;
		Log.i(TAG,"doSomeWork() inside ConcreteSubject is invoked.");

		//getAllPro();
		//getAllUser();
		//insertSingleProduct();
		//insertSingleUser();
	}


}
