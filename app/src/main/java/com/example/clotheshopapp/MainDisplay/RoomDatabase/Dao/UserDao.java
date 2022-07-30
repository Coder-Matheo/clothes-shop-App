package com.example.clotheshopapp.MainDisplay.RoomDatabase.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.clotheshopapp.MainDisplay.RoomDatabase.Model.UserDataObj;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    long insertUser(UserDataObj userInput);

    @Query("SELECT * FROM userdataobj")
    LiveData<List<UserDataObj>> getUserAll();

    @Query("SELECT * FROM userdataobj WHERE email LIKE :emailInput Limit 1")
    LiveData<List<UserDataObj>> userFindByEmail(String emailInput);

    @Query("UPDATE userdataobj SET userName = 'User' WHERE userName = 'New User Test'")
    void setUpdateUser();

}
