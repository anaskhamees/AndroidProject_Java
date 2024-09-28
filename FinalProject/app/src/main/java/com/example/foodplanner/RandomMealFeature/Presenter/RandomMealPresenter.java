package com.example.foodplanner.RandomMealFeature.Presenter;


import com.example.foodplanner.RandomMealFeature.Model.RandomMealPojo;
import com.example.foodplanner.Repository.MealRepository;
import com.example.foodplanner.NetworkPkg.NetworkCallBackInterface;
import com.example.foodplanner.RandomMealFeature.View.RandomMealViewInterface;

import java.util.List;

public class RandomMealPresenter implements RandomMealPresenterInterface, NetworkCallBackInterface<RandomMealPojo> {
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
    @Override
    public void onSuccessfulResult(List<RandomMealPojo> randomMeal) {
        randomMealIview.displayRandomMeal(randomMeal);
    }

    @Override
    public void onFailureResult(String errMsg) {
        randomMealIview.displayError(errMsg);

    }


}
