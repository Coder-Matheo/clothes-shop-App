package com.example.clotheshopapp.MainDisplay.RoomDatabase;

import android.app.Application;
import android.service.autofill.UserData;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.clotheshopapp.MainDisplay.RoomDatabase.Model.ProductData;
import com.example.clotheshopapp.MainDisplay.RoomDatabase.Singleton.MySingletonProduct;
import com.example.clotheshopapp.MainDisplay.RoomDatabase.Singleton.MySingletonUser;

import java.util.List;


public class DataViewModel extends AndroidViewModel {
    MySingletonProduct mySingletonProduct;
    MySingletonUser mySingletonUser;

    public DataViewModel(@NonNull Application application) {
        super(application);

        mySingletonProduct = MySingletonProduct.getInstance(application.getApplicationContext());
        mySingletonUser = MySingletonUser.getInstance(application.getApplicationContext());
    }

    public LiveData<List<ProductData>> getAllProductQuery(){
        return mySingletonProduct.productDao().getAllProducts();
    }


}
