package com.example.foodplanner.NetworkPkg;

import com.example.foodplanner.Home.Countries.Model.CountryResponse;
import com.example.foodplanner.Model.MealResponse;
import com.example.foodplanner.Home.Categories.Model.CategoryResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealServiceInterface {
    @GET("random.php")
    Call<MealResponse> getRandomMealResponse();

    @GET("categories.php")
    Call<CategoryResponse> getCategoryResponse();

    @GET("filter.php")
    Call<MealResponse> getMealFilteredByCategory(@Query("c") String category);

    @GET("filter.php")
    Call<MealResponse> getMealFilteredByCountry(@Query("a") String country);

    @GET("filter.php")
    Call<MealResponse> getMealFilteredByIngredient(@Query("i") String ingredient );

    @GET("list.php?a=list")
    Call<CountryResponse> getCountriesResponse();

   // @GET("list.php?i=list")
    //Call<IngredientResponse> getIngredients();
}
