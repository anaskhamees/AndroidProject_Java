package com.example.foodplanner.MealDetails.Presenter;


import com.example.foodplanner.MealDetails.View.MealDetailsViewInterface;
import com.example.foodplanner.Model.MealPojo;
import com.example.foodplanner.RandomMealFeature.Presenter.RandomMealPresenterInterface;
import com.example.foodplanner.Repository.MealRepository;
import com.example.foodplanner.NetworkPkg.NetworkCallBackInterface;

import java.util.List;

public class MealDetailsPresenter implements MealDetailsPresenterInterface, NetworkCallBackInterface<MealPojo> {
    private MealDetailsViewInterface MealDetailsIview;
    private MealRepository mealDetailsRepo;

    public MealDetailsPresenter(MealDetailsViewInterface Iview, MealRepository mealDetailsRepo) {
        this.MealDetailsIview = Iview;
        this.mealDetailsRepo=mealDetailsRepo;
    }

    public void getMealDetails(String name) {

        mealDetailsRepo.fetchMealByName(this,name);
    }
    public void addMealToFavorites(MealPojo meal)
    {
        mealDetailsRepo.addMealToFav(meal);
    }

    @Override
    public void onSuccessfulResult(List<MealPojo> meal) {
        MealDetailsIview.displayMealDetails(meal);
    }

    @Override
    public void onFailureResult(String errMsg) {
        MealDetailsIview.displayError(errMsg);

    }


}

