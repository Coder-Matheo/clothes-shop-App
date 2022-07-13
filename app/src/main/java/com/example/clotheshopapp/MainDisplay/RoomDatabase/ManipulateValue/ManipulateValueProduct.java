package com.example.clotheshopapp.MainDisplay.RoomDatabase.ManipulateValue;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.clotheshopapp.MainDisplay.RoomDatabase.Model.ProductData;
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

    public Boolean insertSingleProduct(String username, String x, String password, String repeat_password) throws ExecutionException, InterruptedException {
        ExecutorService executorInsert = Executors.newSingleThreadExecutor();
        Callable<Boolean> callable = new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                MySingletonProduct.getInstance(context)
                        .productDao()
                        .insertProduct(new ProductData());

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
}
