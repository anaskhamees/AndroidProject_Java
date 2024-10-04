package com.example.foodplanner.DataBase;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.Model.MealPojo;

import java.util.List;

public interface MealLocalDataBaseInterface {
    LiveData<List<MealPojo>>getAllMeals();
    void insertMeal(MealPojo meal);
    void deleteMeal(MealPojo meal);

}
