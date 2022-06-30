package com.example.clotheshopapp.MainDisplay;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService1 {
    private Retrofit retrofit;

    public RetrofitService1() {
        initialRetrofit();
    }

    private void initialRetrofit() {

        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.101:8080")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
    }

    public Retrofit getRetrofit(){
        return retrofit;
    }
}
