package com.example.clotheshopapp.MainDisplay.RoomDatabase;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import androidx.lifecycle.LiveData;

import java.util.List;

@Dao
public interface DatabaseUser_Dao {
    @Insert
    void insertBio(UserDataInternal bioObj);

    @Query("SELECT * FROM UserDataInternal")
    LiveData<List<UserDataInternal>> getAllUsers();

    @Query("SELECT * FROM UserDataInternal WHERE user_name LIKE :usernameOrPhoneOrEmail OR phone_number LIKE user_name " +
            "AND password LIKE :password  LIMIT 1")
    LiveData<UserDataInternal> findUserByNamePass(String usernameOrPhoneOrEmail, String password);

    @Query("SELECT * FROM UserDataInternal WHERE user_name LIKE :trim OR phone_number LIKE :trim LIKE 1")
    LiveData<UserDataInternal> findUserByNameOrPhoneNumberOrEmail(String trim);
}
