package com.example.foodplanner.FavoritesFeature.Presenter;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.foodplanner.FavoritesFeature.View.FavViewInterface;
import com.example.foodplanner.Model.MealPojo;
import com.example.foodplanner.Repository.MealRepository;

import java.util.List;

public class FavPresenter {
private FavViewInterface favIview;
private MealRepository favRepo;

    public FavPresenter(FavViewInterface favIview, MealRepository favRepo) {
        this.favIview = favIview;
        this.favRepo = favRepo;
    }
    public void getFavMeals(LifecycleOwner lifecycleOwner){
        Observer<List<MealPojo>> observer=new Observer<List<MealPojo>>() {
            @Override
            public void onChanged(List<MealPojo> meals) {
                favIview.displayFavMeals(meals);
            }
        };
        LiveData<List<MealPojo>> liveData=favRepo.getStoredMeals();
        liveData.observe(lifecycleOwner,observer);
    }
    public void removeMealFromFav(MealPojo meal)
    {
        favRepo.deleteMealFromFav(meal);
    }
}
