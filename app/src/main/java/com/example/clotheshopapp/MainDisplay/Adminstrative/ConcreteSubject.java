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

		getAllPro();
		getAllUser();
		//insertSingleProduct();
		//insertSingleUser();
	}

	public void insertSingleProduct(){
		ProductData productData = new ProductData("Ali Dinars", "430300", "8100400");
		InsertAsyncTask insertAsyncTask = new InsertAsyncTask();
		insertAsyncTask.execute(productData);
	}
	public void insertSingleUser(){

		UserDataObj userDataObj = new UserDataObj("Rafael3", "ALI.DINARVAND@GMAIL1234.COM", "1234aside","Build");
		InsertUserAsyncTask insertUserAsyncTask = new InsertUserAsyncTask();
		insertUserAsyncTask.execute(userDataObj);
		Log.i(TAG, "insertSingleUser: new User created");
	}


	class InsertAsyncTask extends AsyncTask<ProductData, Void, Void> {

		@Override
		protected Void doInBackground(ProductData... productData) {
			MySingletonProduct.getInstance(context)
					.productDao()
					.insertProduct(productData[0]);
			return null;
		}
	}

	class InsertUserAsyncTask extends AsyncTask<UserDataObj, Void, Void> {

		@Override
		protected Void doInBackground(UserDataObj... userData) {
			MySingletonUser.getInstance(context)
					.userDao()
					.insertUser(userData[0]);
			return null;
		}
	}
	public void getAllPro() {
		LiveData<List<ProductData>> proList = MySingletonProduct.getInstance(context)
				.productDao()
				.getAllProducts();
		proList.observe(lifecycleOwner, new Observer<List<ProductData>>() {
			@Override
			public void onChanged(List<ProductData> productData) {
				Log.i(TAG, "onChanged: " + productData.toString());
				Log.i(TAG, "onChanged: " + productData.size());
			}
		});
	}

	public void getAllUser(){
		LiveData<List<UserDataObj>> userList = MySingletonUser.getInstance(context)
				.userDao()
				.getUserAll();

		userList.observe(lifecycleOwner, new Observer<List<UserDataObj>>() {
			@Override
			public void onChanged(List<UserDataObj> userDataObjs) {
				Log.i(TAG, "getAllUser: "+userDataObjs);
			}
		});
	}

}
