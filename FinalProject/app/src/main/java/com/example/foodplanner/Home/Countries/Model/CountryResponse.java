package com.example.foodplanner.Home.Countries.Model;


import com.example.foodplanner.Model.MealPojo;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CountryResponse {
    @SerializedName("meals")
    List<MealPojo> meals;

    public List<MealPojo> getCountryResponse()
    {
        return meals;
    }

}
