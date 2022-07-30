package com.example.clotheshopapp.MainDisplay.RoomDatabase;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.clotheshopapp.MainDisplay.RoomDatabase.Model.ProductData;
import com.example.clotheshopapp.MainDisplay.RoomDatabase.Model.UserDataObj;
import com.example.clotheshopapp.MainDisplay.RoomDatabase.Singleton.MySingletonProduct;
import com.example.clotheshopapp.MainDisplay.RoomDatabase.Singleton.MySingletonUser;

import java.util.List;

//extends AndroidViewModel
public class DataViewModel {
    MySingletonProduct mySingletonProduct;
    MySingletonUser mySingletonUser;

    public DataViewModel(Context context) {
        mySingletonProduct = MySingletonProduct.getInstance(context);
        mySingletonUser = MySingletonUser.getInstance(context);
    }

    //Product Query
    public LiveData<List<ProductData>> getAllProductQuery(){
        return mySingletonProduct.productDao().getAllProducts();
    }

    public void deleteAllProductsQuery(){
        mySingletonProduct.productDao().deleteAllProducts();
    }
    public void deleteProduct(int product_id){
        mySingletonProduct.productDao().deleteProductById(product_id);
    }

    public void insertProductQuery(ProductData productData){
        mySingletonProduct.productDao().insertProduct(productData);
    }

    //User Query
    public void insertUserQuery(UserDataObj userDataObj){
        mySingletonUser.userDao().insertUser(userDataObj);
    }

    public LiveData<List<UserDataObj>> getAllUser(){
        return mySingletonUser.userDao().getUserAll();
    }

    public LiveData<List<UserDataObj>> userFindByEmailQuery(String email){
        return mySingletonUser.userDao().userFindByEmail(email);
    }


    public void setUpdateUser(){
        mySingletonUser.userDao().setUpdateUser();
    }

}
