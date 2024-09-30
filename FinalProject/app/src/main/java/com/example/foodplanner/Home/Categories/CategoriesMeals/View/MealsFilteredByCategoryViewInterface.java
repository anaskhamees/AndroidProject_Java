package com.example.foodplanner.Home.Categories.CategoriesMeals.View;

import com.example.foodplanner.Model.MealPojo;

import java.util.List;

public interface MealsFilteredByCategoryViewInterface {
    void displayMealsFilteredByCategory(List<MealPojo> categoriesMeals);
    void displyError(String errMsg);
}
