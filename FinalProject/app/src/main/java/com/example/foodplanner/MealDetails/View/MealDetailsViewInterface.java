package com.example.foodplanner.MealDetails.View;

import com.example.foodplanner.Model.MealPojo;

import java.util.List;

public interface MealDetailsViewInterface {
    void displayMealDetails(List<MealPojo> mealList);
    void displayError(String errorMessage);
}
