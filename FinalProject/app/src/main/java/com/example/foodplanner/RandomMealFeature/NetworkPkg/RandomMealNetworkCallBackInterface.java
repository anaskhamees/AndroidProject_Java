package com.example.foodplanner.RandomMealFeature.NetworkPkg;

import com.example.foodplanner.RandomMealFeature.Model.RandomMealPojo;

import java.util.List;

public interface RandomMealNetworkCallBackInterface {

    public void onSuccessfulResult(List<RandomMealPojo> randomMeal);
    public void onFailureResult(String errMsg);

}
