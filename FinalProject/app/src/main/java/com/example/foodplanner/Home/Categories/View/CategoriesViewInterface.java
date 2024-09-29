package com.example.foodplanner.Home.Categories.View;

import com.example.foodplanner.Home.Categories.Model.CategoryPojo;

import java.util.List;

public interface CategoriesViewInterface {
    void displayCategories(List<CategoryPojo> categories);
    void displayError(String errMsg);
}
