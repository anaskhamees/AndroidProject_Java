package com.example.foodplanner.DataBase;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.Model.MealPojo;
import com.example.foodplanner.Model.MealPojoPlan;

import java.util.List;

public class MealLocalDataSource implements MealLocalDataBaseInterface {
    private MealDAO mealDAO;
    private static MealLocalDataSource mealLocalDataSource=null;
    private LiveData<List<MealPojo>> storedMeals;

    private LiveData<List<MealPojo>> plannedMeals;

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

    @Override
    public LiveData<List<MealPojoPlan>> getPlannedFood(String date) {
        return mealDAO.getPlannedFood(date);
    }

    @Override
    public void insertFoodPlan(MealPojoPlan plannedMeal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDAO.insertPlannedMeal(plannedMeal);
            }
        }).start();
    }

    @Override
    public void deleteFoodPlan(MealPojoPlan plannedMeal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDAO.deletePlannedMeal(plannedMeal);
            }
        }).start();

    }

    @Override
    public void updateFoodPlan(MealPojoPlan plannedMeal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDAO.updatePlannedMeals(plannedMeal);
            }
        }).start();
    }
}
