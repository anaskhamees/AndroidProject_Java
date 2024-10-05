package com.example.foodplanner.DataBase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.foodplanner.Model.MealPojo;
import com.example.foodplanner.Model.MealPojoPlan;

import java.util.List;

@Dao
public interface MealDAO {

    /*=================== For Favorites =================*/
    @Query("Select * FROM meal_table")
    LiveData<List<MealPojo>>getAllMeals();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMeal(MealPojo meal);
    @Delete
    void deleteMeal(MealPojo meal);

    /*=================== For Calendar =================*/

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertPlannedMeal(MealPojoPlan plannedMeal);

    @Query("SELECT * FROM plan_meal_table WHERE date = :date")
    LiveData<List<MealPojoPlan>> getPlannedFood(String date);

    @Query("SELECT * FROM plan_meal_table WHERE date = :date")
    List<MealPojoPlan> getPlannedMealList(String date);

    @Delete
    void deletePlannedMeal(MealPojoPlan plannedMeal);

    @Update
    void updatePlannedMeals(MealPojoPlan plannedMeal);

    @Query("DELETE FROM plan_meal_table WHERE date = :date")
    void deletePlannedMealByDate(String date);
}
