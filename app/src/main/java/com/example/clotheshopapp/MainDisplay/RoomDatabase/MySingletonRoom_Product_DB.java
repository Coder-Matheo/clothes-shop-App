package com.example.clotheshopapp.MainDisplay.RoomDatabase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;


@Database(entities = {ProductDataInternal.class}, version = 1)
public abstract class MySingletonRoom_Product_DB extends RoomDatabase {

    private static final String POST_DB = "POST_DB";
    private static volatile MySingletonRoom_Product_DB INSTANCE;
    public abstract DatabaseProduct_Dao databasePost_dao();


    public static MySingletonRoom_Product_DB getInstance(Context context) {
        if (INSTANCE == null){
            synchronized (MySingletonRoom_Product_DB.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MySingletonRoom_Product_DB.class, POST_DB).build();

                }
            }
        }
        return INSTANCE;
    }
}
