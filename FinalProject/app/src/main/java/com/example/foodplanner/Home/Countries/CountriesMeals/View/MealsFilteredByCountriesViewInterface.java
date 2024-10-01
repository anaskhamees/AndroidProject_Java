package com.example.foodplanner.Home.Countries.CountriesMeals.View;

import com.example.foodplanner.Model.MealPojo;

import java.util.List;

public interface MealsFilteredByCountriesViewInterface {
    void displayMealsFilteredByCountry(List<MealPojo> countriesMeals);
    void displyError(String errMsg);
}
