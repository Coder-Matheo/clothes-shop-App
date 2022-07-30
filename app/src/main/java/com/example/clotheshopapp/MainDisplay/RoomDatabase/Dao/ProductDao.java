package com.example.clotheshopapp.MainDisplay.RoomDatabase.Dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.clotheshopapp.MainDisplay.RoomDatabase.Model.ProductData;

import java.util.List;

@Dao
public interface ProductDao {
    @Insert
    void insertProduct(ProductData productData);

    @Query("SELECT * FROM productdata")
    LiveData<List<ProductData>> getAllProducts();

    @Query("DELETE FROM productdata WHERE bid LIKE :product_id")
    void deleteProductById(int product_id);

    @Query("DELETE FROM productdata")
    void deleteAllProducts();




}
