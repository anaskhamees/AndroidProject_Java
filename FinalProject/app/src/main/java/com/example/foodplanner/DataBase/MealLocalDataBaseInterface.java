package com.example.foodplanner.DataBase;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.Model.MealPojo;
import com.example.foodplanner.Model.MealPojoPlan;

import java.util.List;

public interface MealLocalDataBaseInterface {

   /*=========  for Favorites =============*/
    LiveData<List<MealPojo>>getAllMeals();
    void insertMeal(MealPojo meal);
    void deleteMeal(MealPojo meal);

    /*=========  for Calendar =============*/

    LiveData<List<MealPojoPlan>> getPlannedFood(String date);
    void insertFoodPlan(MealPojoPlan foodPlan);
    void deleteFoodPlan(MealPojoPlan foodPlan);
    void updateFoodPlan(MealPojoPlan foodPlan);

}
