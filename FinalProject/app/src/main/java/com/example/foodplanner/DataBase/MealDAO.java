package com.example.foodplanner.DataBase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodplanner.Model.MealPojo;

import java.util.List;

@Dao
public interface MealDAO {
    @Query("Select * FROM meal_table")
    LiveData<List<MealPojo>>getAllMeals();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMeal(MealPojo meal);
    @Delete
    void deleteMeal(MealPojo meal);
}
