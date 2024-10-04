package com.example.foodplanner.Repository;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.DataBase.MealLocalDataBaseInterface;
import com.example.foodplanner.Model.MealPojo;
import com.example.foodplanner.NetworkPkg.NetworkCallBackInterface;
import com.example.foodplanner.NetworkPkg.MealRemoteDataSource;

import java.util.List;

public class MealRepository implements RepositoryInterface {

    MealRemoteDataSource mealRemoteDataSource;
    MealLocalDataBaseInterface mealLocalDataSource;

    private static MealRepository mealRepo =null;

    public static MealRepository getInstance(MealRemoteDataSource mealRemoteDataSource,MealLocalDataBaseInterface mealLocalDataSource)
    {
        if(mealRepo ==null)
        {
            mealRepo =new MealRepository(mealRemoteDataSource,mealLocalDataSource);
        }
        return mealRepo;
    }

    public MealRepository(MealRemoteDataSource mealRemoteDataSource,MealLocalDataBaseInterface mealLocalDataSource)
    {
        this.mealRemoteDataSource = mealRemoteDataSource;
        this.mealLocalDataSource=mealLocalDataSource;
    }

    @Override
    public void fetchRandomMeal(NetworkCallBackInterface networkCallBackInterface) {

        mealRemoteDataSource.makeRandomMealNetworkCall(networkCallBackInterface);
    }

    @Override
    public void fetchCategories(NetworkCallBackInterface networkCallBackInterface) {
        mealRemoteDataSource.makeCategoryNetworkCall(networkCallBackInterface);
    }

    @Override
    public void fetchMealFilteredByCategory(NetworkCallBackInterface networkCallBackInterface, String category) {
        mealRemoteDataSource.makeMealFilteredByCategoryNetworkCall(networkCallBackInterface,category);
    }

    @Override
    public void fetchCountries(NetworkCallBackInterface networkCallBackInterface) {
        mealRemoteDataSource.makeCountriesCallBack(networkCallBackInterface);
    }

    @Override
    public void fetchMealFilteredByCountry(NetworkCallBackInterface networkCallBackInterface, String country) {
        mealRemoteDataSource.makeMealFilteredByCountryNetworkCall(networkCallBackInterface,country);
    }

    @Override
    public void fetchMealByName(NetworkCallBackInterface networkCallBackInterface, String name) {
        mealRemoteDataSource.makeMealByNameNetworkCallBack(networkCallBackInterface,name);
    }

    @Override
    public void fetchMealByIngredient(NetworkCallBackInterface networkCallBackInterface, String ingredient) {
        mealRemoteDataSource.makeMealByIngredientNetworkCallBack(networkCallBackInterface,ingredient);
    }

    public void addMealToFav(MealPojo meal)
    {
        mealLocalDataSource.insertMeal(meal);
    }
    public void deleteMealFromFav(MealPojo meal)
    {
        mealLocalDataSource.deleteMeal(meal);
    }

    public LiveData<List<MealPojo>> getStoredMeals()
    {
        return mealLocalDataSource.getAllMeals();
    }
}
