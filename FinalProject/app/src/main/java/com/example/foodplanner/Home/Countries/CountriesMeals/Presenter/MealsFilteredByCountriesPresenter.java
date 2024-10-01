package com.example.foodplanner.Home.Countries.CountriesMeals.Presenter;

import android.util.Log;

import com.example.foodplanner.Home.Categories.CategoriesMeals.Presenter.MealsFilteredByCategoryPresenterInterface;
import com.example.foodplanner.Home.Categories.CategoriesMeals.View.MealsFilteredByCategoryViewInterface;
import com.example.foodplanner.Home.Countries.CountriesMeals.View.MealsFilteredByCountriesViewInterface;
import com.example.foodplanner.Model.MealPojo;
import com.example.foodplanner.NetworkPkg.NetworkCallBackInterface;
import com.example.foodplanner.Repository.MealRepository;

import java.util.List;

public class MealsFilteredByCountriesPresenter implements MealsFilteredByCountriesPresenterInterface, NetworkCallBackInterface<MealPojo>{

    private MealRepository countriesMealsRepo;
    private MealsFilteredByCountriesViewInterface countriesMealsIview;

    public MealsFilteredByCountriesPresenter(MealRepository countriesMealsRepo, MealsFilteredByCountriesViewInterface mealsIview) {
        this.countriesMealsRepo = countriesMealsRepo;
        this.countriesMealsIview =mealsIview;
    }

    public void getMealsFilteredByCountry(String country) {
        countriesMealsRepo.fetchMealFilteredByCountry(this,country);
    }


    @Override
    public void onSuccessfulResult(List<MealPojo> countriesMeals) {
        countriesMealsIview.displayMealsFilteredByCountry(countriesMeals);
        Log.i("Meals filter by country", "onSuccessfulResult: Fetch the meals of country correct ");
        Log.i("Meals filter by country", "onSuccessfulResult: Fetch the meals of country correct ");
        Log.i("Meals filter by country", "onSuccessfulResult: Fetch the meals of country correct ");
    }

    @Override
    public void onFailureResult(String errMsg) {
countriesMealsIview.displyError(errMsg);
    }
}
