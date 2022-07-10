package com.example.clotheshopapp.MainDisplay.RoomDatabase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {UserDataInternal.class}, version = 1)
public abstract class MySingletonRoom_User_DB extends RoomDatabase {

    private static final String BIO_DB = "BIO_DB";
    public abstract DatabaseUser_Dao databaseBio_dao();
    private static volatile MySingletonRoom_User_DB INSTANCE;


    public static MySingletonRoom_User_DB getInstance(Context context) {
        if (INSTANCE == null){
            synchronized (MySingletonRoom_User_DB.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MySingletonRoom_User_DB.class, BIO_DB).build();
                }
            }
        }
        return INSTANCE;
    }
}
