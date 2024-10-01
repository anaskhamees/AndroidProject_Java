package com.example.foodplanner.Home.Countries.View;

import com.example.foodplanner.Model.MealPojo;

import java.util.List;

public interface CountriesViewInterface {
    void displayCountries(List<MealPojo> countries);
    void showError(String message);
}
