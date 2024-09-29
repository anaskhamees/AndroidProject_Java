package com.example.foodplanner.RandomMealFeature.View;

import com.example.foodplanner.Model.MealPojo;

import java.util.List;

public interface RandomMealViewInterface {

    void displayRandomMeal(List<MealPojo> mealList);
    void displayError(String errorMessage);
}
