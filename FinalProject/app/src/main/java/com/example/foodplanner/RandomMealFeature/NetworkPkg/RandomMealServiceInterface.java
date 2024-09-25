package com.example.foodplanner.RandomMealFeature.NetworkPkg;

import com.example.foodplanner.RandomMealFeature.Model.RandomMealPojo;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RandomMealServiceInterface {
    @GET("random.php")
    Call<RandomMealResponse> getRandomMealResponse();
}
