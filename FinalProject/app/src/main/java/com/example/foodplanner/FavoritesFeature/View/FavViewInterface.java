package com.example.foodplanner.FavoritesFeature.View;

import com.example.foodplanner.Model.MealPojo;

import java.util.List;

public interface FavViewInterface {
    void displayFavMeals(List<MealPojo> meals);
    void displayError(String errMsg);
}
