package com.example.foodplanner.RandomMealFeature.View;

import com.example.foodplanner.RandomMealFeature.Model.RandomMealPojo;

import java.util.List;

public interface RandomMealViewInterface {

    void displayRandomMeal(List<RandomMealPojo> mealList);
    void displayError(String errorMessage);
}
