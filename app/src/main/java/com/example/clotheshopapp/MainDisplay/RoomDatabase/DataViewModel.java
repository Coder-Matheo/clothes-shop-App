package com.example.clotheshopapp.MainDisplay.RoomDatabase;

import android.content.Context;
import android.content.Intent;
import android.icu.text.StringPrepParseException;
import android.text.TextUtils;

import androidx.lifecycle.LiveData;
import androidx.room.util.StringUtil;

import com.example.clotheshopapp.MainDisplay.MainActivity;
import com.example.clotheshopapp.MainDisplay.RoomDatabase.Model.ProductData;
import com.example.clotheshopapp.MainDisplay.RoomDatabase.Model.UserDataObj;
import com.example.clotheshopapp.MainDisplay.RoomDatabase.Singleton.MySingletonProduct;
import com.example.clotheshopapp.MainDisplay.RoomDatabase.Singleton.MySingletonUser;

import java.util.List;
import java.util.regex.Pattern;

import okhttp3.internal.Util;

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

    public boolean insertProductQuery(ProductData productData){
        long insertedProduct = mySingletonProduct.productDao().insertProduct(productData);
        return isNumeric(insertedProduct);
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


    private Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    private boolean isNumeric(long strNum) {
        try{
            String strNu = String.valueOf(strNum);
            if (strNu == null && strNu == "null") {
                return false;
            }

            return pattern.matcher(strNu).matches();
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }


}
