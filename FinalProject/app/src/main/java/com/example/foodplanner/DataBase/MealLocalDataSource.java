package com.example.foodplanner.DataBase;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodplanner.Model.MealPojo;

import java.util.List;

public class MealLocalDataSource implements MealLocalDataBaseInterface {
    private MealDAO mealDAO;
    private static MealLocalDataSource mealLocalDataSource=null;
    private LiveData<List<MealPojo>> storedMeals;

    public MealLocalDataSource(Context context) {

        AppDataBase appDataBase=AppDataBase.getInstance(context.getApplicationContext());
        mealDAO=appDataBase.getMealDAO();
        storedMeals=mealDAO.getAllMeals();
    }

    public  static MealLocalDataSource getInstance(Context context)
    {
        if(mealLocalDataSource==null)
        {
            mealLocalDataSource=new MealLocalDataSource(context);
        }
        return mealLocalDataSource;
    }

    @Override
    public LiveData<List<MealPojo>> getAllMeals() {
        return storedMeals;
    }

    @Override
    public void insertMeal(MealPojo meal) {

        new Thread(){
            @Override
            public void run() {
                super.run();
                mealDAO.insertMeal(meal);
                Log.i("MealInsert", "Inserted meal: " + meal.getStrMeal());

            }
        }.start();
    }

    @Override
    public void deleteMeal(MealPojo meal) {
        new Thread(){
            @Override
            public void run() {
                super.run();
                mealDAO.deleteMeal(meal);
            }
        }.start();
    }
}
