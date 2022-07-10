package com.example.clotheshopapp.MainDisplay.RoomDatabase;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface DatabaseProduct_Dao {
    @Insert
    void insert(ProductDataInternal postObj);

    @Query("SELECT * FROM ProductDataInternal")
    List<ProductDataInternal> getAllTweetPost();
    //LiveData<List<PostObj>> getAllTweetPost();

    @Update
    void updateLike(ProductDataInternal postObj);

    @Query("SELECT * FROM ProductDataInternal WHERE message_post LIKE :tweetSearchId LIMIT 1")
    ProductDataInternal findByText(String tweetSearchId);




}
