package com.example.foodplanner.Home.Categories.CategoriesMeals.Presenter;

import com.example.foodplanner.Model.MealPojo;
import com.example.foodplanner.NetworkPkg.NetworkCallBackInterface;
import com.example.foodplanner.Repository.MealRepository;

import java.util.List;

public class MealsFilteredByCategoryPresenter implements MealsFilteredByCategoryPresenterInterface, NetworkCallBackInterface<MealPojo> {

    private MealRepository categoriesMealsRepo;

    @Override
    public void getMealsFilteredByCategory() {

    }

    @Override
    public void onSuccessfulResult(List<MealPojo> randomMeal) {

    }

    @Override
    public void onFailureResult(String errMsg) {

    }
}
