package com.example.clotheshopapp.MainDisplay.Adminstrative;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.clotheshopapp.MainDisplay.MainActivity;
import com.example.clotheshopapp.MainDisplay.RoomDatabase.Model.ProductData;
import com.example.clotheshopapp.MainDisplay.RoomDatabase.Singleton.MySingletonProduct;

import java.util.List;

public class ConcreteSubject extends Subject{
	//get View Life Cycle Owner
	MainActivity mainActivity;
	private static final String TAG = "ConcreteSubject";
	@Override
	public void doSomeWork(MainActivity mainActivity) {
		this.mainActivity = mainActivity;
		System.out.println("doSomeWork() inside ConcreteSubject is invoked.");

		getAllPro();
		insertSingleProduct();
	}

	public void insertSingleProduct(){
		ProductData productData = new ProductData("Ali Dinarvand", "43000", "810000");
		InsertAsyncTask insertAsyncTask = new InsertAsyncTask();
		insertAsyncTask.execute(productData);
	}


	class InsertAsyncTask extends AsyncTask<ProductData, Void, Void> {

		@Override
		protected Void doInBackground(ProductData... productData) {
			MySingletonProduct.getInstance(mainActivity)
					.productDao()
					.insertProduct(productData[0]);
			Log.i(TAG, "doInBackground: ");
			return null;
		}
	}
	public void getAllPro() {
		LiveData<List<ProductData>> proList = MySingletonProduct.getInstance(mainActivity)
				.productDao()
				.getAllProducts();
		proList.observe(mainActivity, new Observer<List<ProductData>>() {
			@Override
			public void onChanged(List<ProductData> productData) {
				Log.i(TAG, "onChanged: " + productData.toString());
			}
		});
	}

}
