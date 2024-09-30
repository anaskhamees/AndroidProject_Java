package com.example.foodplanner.Home.Categories.CategoriesMeals.Presenter;

import android.util.Log;

import com.example.foodplanner.Home.Categories.CategoriesMeals.View.MealsFilteredByCategoryViewInterface;
import com.example.foodplanner.Model.MealPojo;
import com.example.foodplanner.NetworkPkg.NetworkCallBackInterface;
import com.example.foodplanner.Repository.MealRepository;

import java.util.List;

public class MealsFilteredByCategoryPresenter implements MealsFilteredByCategoryPresenterInterface, NetworkCallBackInterface<MealPojo>{

    private MealRepository categoriesMealsRepo;
    private MealsFilteredByCategoryViewInterface categoriesMealsIview;

    public MealsFilteredByCategoryPresenter(MealRepository categoriesMealsRepo,MealsFilteredByCategoryViewInterface mealsIview) {
        this.categoriesMealsRepo = categoriesMealsRepo;
        this.categoriesMealsIview=mealsIview;
    }

    @Override
    public void getMealsFilteredByCategory(String category) {
        categoriesMealsRepo.fetchMealFilteredByCategory(this,category);
    }

    @Override
    public void onSuccessfulResult(List<MealPojo> categoriesMeals) {
        categoriesMealsIview.displayMealsFilteredByCategory(categoriesMeals);
        Log.i("Meals filter by category", "onSuccessfulResult: Fetch the meals of category correct ");
        Log.i("Meals filter by category", "onSuccessfulResult: Fetch the meals of category correct ");
        Log.i("Meals filter by category", "onSuccessfulResult: Fetch the meals of category correct ");

    }

    @Override
    public void onFailureResult(String errMsg) {
        categoriesMealsIview.displyError(errMsg);
    }


}
