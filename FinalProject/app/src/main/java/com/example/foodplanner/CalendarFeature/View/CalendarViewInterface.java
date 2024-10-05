package com.example.foodplanner.CalendarFeature.View;

import com.example.foodplanner.Model.MealPojoPlan;

import java.util.List;

public interface CalendarViewInterface {
    void displayPlannedMeals(List<MealPojoPlan> mealList);
    void displayError(String errorMessage);
}
