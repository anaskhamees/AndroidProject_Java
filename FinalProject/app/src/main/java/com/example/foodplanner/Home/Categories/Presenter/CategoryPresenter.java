package com.example.foodplanner.Home.Categories.Presenter;

import com.example.foodplanner.NetworkPkg.NetworkCallBackInterface;
import com.example.foodplanner.Repository.MealRepository;
import com.example.foodplanner.Home.Categories.Model.CategoryPojo;
import com.example.foodplanner.Home.Categories.View.CategoriesViewInterface;

import java.util.List;

public class CategoryPresenter implements CategoryPresenterInterface, NetworkCallBackInterface<CategoryPojo> {

    private CategoriesViewInterface categoriesIview;
    private MealRepository categoriesRepo;

    public CategoryPresenter(CategoriesViewInterface categoriesIview,MealRepository categoriesRepo) {
        this.categoriesIview = categoriesIview;
        this.categoriesRepo=categoriesRepo;
    }

    @Override
    public void getCategories() {

        categoriesRepo.fetchCategories(this);
    }

    @Override
    public void onSuccessfulResult(List<CategoryPojo> categories) {
        categoriesIview.displayCategories(categories);
    }

    @Override
    public void onFailureResult(String errMsg) {
        categoriesIview.displayError(errMsg);
    }
}
