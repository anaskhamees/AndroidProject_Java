package com.example.foodplanner.RandomMealFeature.Presenter;


import com.example.foodplanner.Model.MealPojo;
import com.example.foodplanner.Model.MealPojoPlan;
import com.example.foodplanner.Repository.MealRepository;
import com.example.foodplanner.NetworkPkg.NetworkCallBackInterface;
import com.example.foodplanner.RandomMealFeature.View.RandomMealViewInterface;

import java.util.List;

public class RandomMealPresenter implements RandomMealPresenterInterface, NetworkCallBackInterface<MealPojo> {
    private RandomMealViewInterface randomMealIview;
    private MealRepository randomMealRepo;

    public RandomMealPresenter(RandomMealViewInterface Iview, MealRepository randomMealRepo) {
        this.randomMealIview = Iview;
        this.randomMealRepo=randomMealRepo;
        //this.randomMealRemoteDataSource = RandomMealRemoteDataSource.getRandomMealRemoteDataSourceInstance();
    }

    public void getRandomMeal() {

        randomMealRepo.fetchRandomMeal(this);
    }
    public void addMealToFavorites(MealPojo meal)
    {
        randomMealRepo.addMealToFav(meal);
    }

    @Override
    public void onSuccessfulResult(List<MealPojo> randomMeal) {
        randomMealIview.displayRandomMeal(randomMeal);
    }

    @Override
    public void onFailureResult(String errMsg) {
        randomMealIview.displayError(errMsg);

    }

    public void addMealToCalendar(MealPojoPlan plannedMeal)
    {
        randomMealRepo.insertPlannedMeal(plannedMeal);
    }



}
