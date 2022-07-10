package com.example.clotheshopapp.MainDisplay.RoomDatabase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class DatabaseViewModel extends AndroidViewModel {
    MySingletonRoom_User_DB mySingleton_database;
    MySingletonRoom_Product_DB mySingleton_Room_post_db;


    public DatabaseViewModel(@NonNull Application application) {
        super(application);

        mySingleton_database = MySingletonRoom_User_DB.getInstance(application.getApplicationContext());
        mySingleton_Room_post_db = MySingletonRoom_Product_DB.getInstance(application.getApplicationContext());

    }

    public LiveData<List<UserDataInternal>> getAllUserBioQuery(){
        return mySingleton_database.databaseBio_dao().getAllUsers();
    }

    public LiveData<UserDataInternal> findUserByNamePassBioQuery(String username, String password){
        return mySingleton_database.databaseBio_dao().findUserByNamePass(username, password);
    }

    public LiveData<List<ProductDataInternal>> getAllTweetPostQuery(){
        return (LiveData<List<ProductDataInternal>>) mySingleton_Room_post_db.databasePost_dao().getAllTweetPost();
    }
}
