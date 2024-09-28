package com.example.foodplanner.SearchMealFeature.Categories.Presenter;

import com.example.foodplanner.NetworkPkg.NetworkCallBackInterface;
import com.example.foodplanner.SearchMealFeature.Categories.Model.CategoryPojo;

import java.util.List;

public class CategoryPresenter implements CategoryPresenterInterface, NetworkCallBackInterface<CategoryPojo> {
    @Override
    public void getCategories() {

    }

    @Override
    public void onSuccessfulResult(List<CategoryPojo> randomMeal) {
        
    }

    @Override
    public void onFailureResult(String errMsg) {

    }
}
