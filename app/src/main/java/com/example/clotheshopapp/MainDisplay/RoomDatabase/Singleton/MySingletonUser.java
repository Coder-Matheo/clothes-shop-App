package com.example.clotheshopapp.MainDisplay.RoomDatabase.Singleton;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.clotheshopapp.MainDisplay.RoomDatabase.Dao.UserDao;
import com.example.clotheshopapp.MainDisplay.RoomDatabase.Model.UserDataObj;

@Database(entities = {UserDataObj.class}, version = 1)
public abstract class MySingletonUser extends RoomDatabase {
    private static final String USER_DB = "USER_DB";
    public abstract UserDao userDao();
    private static volatile MySingletonUser INSTANCE;

    public static MySingletonUser getInstance(Context context){
        if (INSTANCE == null){
            synchronized (MySingletonUser.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MySingletonUser.class, USER_DB).build();
                }
            }
        }
        return INSTANCE;
    }
}
