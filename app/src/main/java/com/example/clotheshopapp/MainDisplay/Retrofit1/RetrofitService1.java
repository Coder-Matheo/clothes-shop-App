package com.example.clotheshopapp.MainDisplay.Retrofit1;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService1 {
    private static final String TAG = "RetrofitService1";
    private  Retrofit retrofit;


    public RetrofitService1() {
        initialRetrofit();
    }

    private void initialRetrofit() {
        retrofit  = new Retrofit.Builder()
                .baseUrl("http://192.168.0.101:8080")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
    }


    public Retrofit getRetrofit(){
        Log.i(TAG, "getRetrofit: "+retrofit);
        return retrofit;
    }





    /*public static Retrofit getRetrofit() {
        if (retrofit == null){
            synchronized (RetrofitService1.class){
                if (retrofit == null){
                    retrofit  = new Retrofit.Builder()
                            .baseUrl("http://192.168.0.101:8080")
                            .addConverterFactory(GsonConverterFactory.create(new Gson()))
                            .build();

                }
            }
        }
        return retrofit;
    }*/
}
