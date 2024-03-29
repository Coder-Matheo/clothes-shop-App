package com.example.clotheshopapp.MainDisplay.RoomDatabase.Singleton;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.clotheshopapp.MainDisplay.RoomDatabase.Dao.ProductDao;
import com.example.clotheshopapp.MainDisplay.RoomDatabase.Model.ProductData;

@Database(entities = {ProductData.class}, version = 2)
public abstract class MySingletonProduct extends RoomDatabase {
    private static final String PRODUCT_DB = "PRODUCT_DB";
    public abstract ProductDao productDao();
    private static volatile MySingletonProduct INSTANCE;

    public static MySingletonProduct getInstance(Context context){
        if (INSTANCE == null){
            synchronized (MySingletonProduct.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MySingletonProduct.class, PRODUCT_DB)
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
