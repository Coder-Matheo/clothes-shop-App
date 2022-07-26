package com.example.clotheshopapp.MainDisplay.RoomDatabase.Singleton;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.clotheshopapp.MainDisplay.RoomDatabase.Dao.UserDao;
import com.example.clotheshopapp.MainDisplay.RoomDatabase.Model.UserDataObj;

@Database(entities = {UserDataObj.class}, version = 1, exportSchema = false)
public abstract class MySingletonUser extends RoomDatabase {
    private static final String USER_DB = "USER_DB";
    public abstract UserDao userDao();
    private static volatile MySingletonUser INSTANCE;

    public static MySingletonUser getInstance(Context context){
        if (INSTANCE == null){
            synchronized (MySingletonUser.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MySingletonUser.class, USER_DB)
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
