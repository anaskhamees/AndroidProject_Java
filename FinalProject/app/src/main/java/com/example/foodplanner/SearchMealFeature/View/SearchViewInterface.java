package com.example.foodplanner.SearchMealFeature.View;
import com.example.foodplanner.Model.MealPojo;

import java.util.List;

public interface SearchViewInterface {
    void displayMeals(List<MealPojo> meals);
    void displayError(String errMsg);
}
