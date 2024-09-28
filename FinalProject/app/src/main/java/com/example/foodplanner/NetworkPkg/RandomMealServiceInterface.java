package com.example.foodplanner.NetworkPkg;

import com.example.foodplanner.RandomMealFeature.Model.RandomMealResponse;
import com.example.foodplanner.SearchMealFeature.Categories.Model.CategoryResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RandomMealServiceInterface {
    @GET("random.php")
    Call<RandomMealResponse> getRandomMealResponse();

    @GET("categories.php")
    Call<CategoryResponse> getCategoryResponse();
}
