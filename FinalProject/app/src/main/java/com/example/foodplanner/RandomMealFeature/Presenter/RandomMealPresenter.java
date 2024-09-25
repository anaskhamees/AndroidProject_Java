package com.example.foodplanner.RandomMealFeature.Presenter;


import android.widget.Toast;

import com.example.foodplanner.RandomMealFeature.Model.RandomMealPojo;
import com.example.foodplanner.RandomMealFeature.NetworkPkg.RandomMealNetworkCallBackInterface;
import com.example.foodplanner.RandomMealFeature.NetworkPkg.RandomMealRemoteDataSource;
import com.example.foodplanner.RandomMealFeature.View.RandomMealViewInterface;

import java.util.List;

public class RandomMealPresenter {
    private RandomMealViewInterface randomMealIview;
    private RandomMealRemoteDataSource randomMealRemoteDataSource;

    public RandomMealPresenter(RandomMealViewInterface view) {
        this.randomMealIview = view;
        this.randomMealRemoteDataSource = RandomMealRemoteDataSource.getRandomMealRemoteDataSourceInstance();
    }

    public void fetchRandomMeal() {
        randomMealRemoteDataSource.makeRandomMealNetworkCall(new RandomMealNetworkCallBackInterface() {
            @Override
            public void onSuccessfulResult(List<RandomMealPojo> randomMeal) {
                randomMealIview.displayRandomMeal(randomMeal);
            }

            @Override
            public void onFailureResult(String errMsg) {
                randomMealIview.displayError(errMsg);

            }
        });
    }
}
