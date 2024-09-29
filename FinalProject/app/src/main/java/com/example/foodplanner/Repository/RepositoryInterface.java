package com.example.foodplanner.Repository;

import com.example.foodplanner.NetworkPkg.NetworkCallBackInterface;

public interface RepositoryInterface {
    public void fetchRandomMeal(NetworkCallBackInterface networkCallBackInterface);
    public void fetchCategories(NetworkCallBackInterface networkCallBackInterface);
    public void fetchMealFilteredByCategory(NetworkCallBackInterface networkCallBackInterface, String category);
}
