package com.example.foodplanner.DataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodplanner.Model.MealPojo;

@Database(entities = {MealPojo.class},version = 2)
public abstract class AppDataBase extends RoomDatabase{
    private static AppDataBase instance=null;
    public abstract MealDAO getMealDAO();

    public static synchronized  AppDataBase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),AppDataBase.class,"mealdb")
                    .build();
        }
        return instance;
    }
}
