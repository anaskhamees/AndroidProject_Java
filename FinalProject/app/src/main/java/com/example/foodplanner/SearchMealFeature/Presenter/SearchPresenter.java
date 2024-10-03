package com.example.foodplanner.SearchMealFeature.Presenter;

import com.example.foodplanner.Model.MealPojo;
import com.example.foodplanner.NetworkPkg.NetworkCallBackInterface;
import com.example.foodplanner.Repository.MealRepository;
import com.example.foodplanner.SearchMealFeature.View.SearchViewInterface;

import java.util.List;

public class SearchPresenter implements SearchPresenterInterface, NetworkCallBackInterface<MealPojo> {

    private MealRepository mealRepo;
    private SearchViewInterface searchIview;

    public SearchPresenter(MealRepository mealRepo, SearchViewInterface searchIview) {
        this.mealRepo = mealRepo;
        this.searchIview = searchIview;
    }

    @Override
    public void onSuccessfulResult(List<MealPojo> meals) {
        searchIview.displayMeals(meals);
    }

    @Override
    public void onFailureResult(String errMsg) {
        searchIview.displayError(errMsg);
    }

    @Override
    public void getMealsByCategory(String category) {
        mealRepo.fetchMealFilteredByCategory(this,category);
    }

    @Override
    public void getMealsById(String id) {

    }

    @Override
    public void getMealsByName(String name) {
        mealRepo.fetchMealByName(this,name);
    }

    @Override
    public void getMealsByCountry(String country) {
        mealRepo.fetchMealFilteredByCountry(this,country);
    }

    @Override
    public void getMealsByIngredient(String ingredient) {
        mealRepo.fetchMealByIngredient(this,ingredient);
    }
}
