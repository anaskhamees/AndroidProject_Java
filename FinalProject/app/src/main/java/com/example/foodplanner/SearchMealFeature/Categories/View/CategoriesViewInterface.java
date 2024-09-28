package com.example.foodplanner.SearchMealFeature.Categories.View;

import com.example.foodplanner.SearchMealFeature.Categories.Model.CategoryPojo;

import java.util.List;

public interface CategoriesViewInterface {
    void displayCategories(List<CategoryPojo> categories);
    void displayError(String errMsg);
}
