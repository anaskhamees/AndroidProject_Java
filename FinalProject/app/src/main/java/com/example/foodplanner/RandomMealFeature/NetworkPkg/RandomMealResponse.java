package com.example.foodplanner.RandomMealFeature.NetworkPkg;

import com.example.foodplanner.RandomMealFeature.Model.RandomMealPojo;

import java.util.List;

public class RandomMealResponse {

    List<RandomMealPojo> meals;
    public List<RandomMealPojo> getRandomMealResponse()
    {
        return meals;
    }
}
