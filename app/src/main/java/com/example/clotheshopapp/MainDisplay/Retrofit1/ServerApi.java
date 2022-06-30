package com.example.clotheshopapp.MainDisplay.Retrofit1;

import android.service.autofill.UserData;

import com.example.clotheshopapp.MainDisplay.Model.ProductData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ServerApi {

    @GET("/user/get-all")
    Call<List<UserData>> getAllEmployees();

    @POST("/user/save")
    Call<UserData> save(@Body UserData userData);

    @GET("/product/get-all")
    Call<List<ProductData>> getAllProduct();

    @POST("/product/save")
    Call<ProductData> save(@Body ProductData productData);



}