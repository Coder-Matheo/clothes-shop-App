package com.example.clotheshopapp.MainDisplay.RoomDatabase.Singleton;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.clotheshopapp.MainDisplay.RoomDatabase.Dao.ProductDao;
import com.example.clotheshopapp.MainDisplay.RoomDatabase.Model.ProductData;

@Database(entities = {ProductData.class}, version = 1)
public abstract class MySingletonProduct extends RoomDatabase {
    private static final String PRODUCT_DB = "PRODUCT_DB";
    public abstract ProductDao productDao();
    private static volatile MySingletonProduct INSTANCE;

    public static MySingletonProduct getInstance(Context context){
        if (INSTANCE == null){
            synchronized (MySingletonProduct.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MySingletonProduct.class, PRODUCT_DB).build();
                }
            }
        }
        return INSTANCE;
    }

}
