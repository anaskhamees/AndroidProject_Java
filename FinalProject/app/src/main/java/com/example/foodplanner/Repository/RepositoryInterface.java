package com.example.foodplanner.Repository;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.Model.MealPojoPlan;
import com.example.foodplanner.NetworkPkg.NetworkCallBackInterface;

import java.util.List;

public interface RepositoryInterface {
    public void fetchRandomMeal(NetworkCallBackInterface networkCallBackInterface);
    public void fetchCategories(NetworkCallBackInterface networkCallBackInterface);
    public void fetchMealFilteredByCategory(NetworkCallBackInterface networkCallBackInterface, String category);
    public void fetchCountries(NetworkCallBackInterface networkCallBackInterface);
    public void fetchMealFilteredByCountry(NetworkCallBackInterface networkCallBackInterface, String country);
    public void fetchMealByName(NetworkCallBackInterface networkCallBackInterface,String name);
    public void fetchMealByIngredient(NetworkCallBackInterface networkCallBackInterface,String ingredient);

    /*======================== Calendar =====================*/


    public LiveData<List<MealPojoPlan>> getPlannedMeals(String date);
    public void insertPlannedMeal(MealPojoPlan plannedMeal);
    public void deletePlannedMeal(MealPojoPlan plannedMeal);
    public void updatePlannedMeal(MealPojoPlan plannedMeal);
}
