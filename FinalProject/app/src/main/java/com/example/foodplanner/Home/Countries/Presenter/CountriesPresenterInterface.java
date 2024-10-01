package com.example.foodplanner.Home.Countries.Presenter;

import com.example.foodplanner.Model.MealPojo;

import java.util.List;

public interface CountriesPresenterInterface {
    void getCountries();

    void onSuccessfulResult(List<MealPojo> countries);
}
