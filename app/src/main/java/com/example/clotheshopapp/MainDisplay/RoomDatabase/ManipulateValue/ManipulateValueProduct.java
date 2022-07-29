package com.example.clotheshopapp.MainDisplay.RoomDatabase.ManipulateValue;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.clotheshopapp.MainDisplay.RoomDatabase.Model.ProductData;
import com.example.clotheshopapp.MainDisplay.RoomDatabase.Model.UserDataObj;
import com.example.clotheshopapp.MainDisplay.RoomDatabase.Singleton.MySingletonProduct;
import com.example.clotheshopapp.MainDisplay.RoomDatabase.Singleton.MySingletonUser;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ManipulateValueProduct {
    private static final String TAG = "ManipulateValueProduct";
    Context context;

    public ManipulateValueProduct(Context context) {
        this.context = context;
    }

    public Boolean insertSingleProduct(String pro_name, String pro_price, String pro_dataOff, byte[] pro_image) throws ExecutionException, InterruptedException {
        ExecutorService executorInsert = Executors.newSingleThreadExecutor();
        Callable<Boolean> callable = new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                MySingletonProduct.getInstance(context)
                        .productDao()
                        .insertProduct(new ProductData(pro_name,pro_price,pro_dataOff, pro_image));

                return true;
            }
        };
        Future<Boolean> future = executorInsert.submit(callable);
        executorInsert.shutdown();
        Boolean inserted = future.get();
        return inserted;
    }


    public List<String> getAllProducts() throws ExecutionException, InterruptedException {
        ArrayList<String> productListRet = new ArrayList<>();

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Callable<ArrayList<String>> callable = new Callable<ArrayList<String>>() {
            @Override
            public ArrayList<String> call() throws Exception {
                LiveData<List<ProductData>> productList = MySingletonProduct.getInstance(context)
                        .productDao()
                        .getAllProducts();

                for (int i = 0; i < productList.getValue().size(); i++){
                    productListRet.add(productList.getValue().get(i).getProductName());
                }
                return productListRet;
            }
        };

        Future<ArrayList<String>> future = executor.submit(callable);
        executor.shutdown();
        List l = future.get();
        return l;

    }


    /*public void insertSingleProduct(){
        //ProductData productData = new ProductData("Ali Dinars", "430300", "8100400");
        InsertAsyncTask insertAsyncTask = new InsertAsyncTask();
        //insertAsyncTask.execute(productData);
    }

    class InsertAsyncTask extends AsyncTask<ProductData, Void, Void> {

        @Override
        protected Void doInBackground(ProductData... productData) {
            MySingletonProduct.getInstance(context)
                    .productDao()
                    .insertProduct(productData[0]);
            return null;
        }
    }*/


   /* public void insertSingleProduct(){
        //ProductData productData = new ProductData("Ali Dinars", "430300", "8100400");
        InsertAsyncTask insertAsyncTask = new InsertAsyncTask();
        //insertAsyncTask.execute(productData);
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
    }*/

}
