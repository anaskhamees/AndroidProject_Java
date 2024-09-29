package com.example.foodplanner.Repository;

import com.example.foodplanner.NetworkPkg.NetworkCallBackInterface;
import com.example.foodplanner.NetworkPkg.MealRemoteDataSource;

public class MealRepository implements RepositoryInterface {

    MealRemoteDataSource mealRemoteDataSource;

    private static MealRepository randomMealRepo=null;

    public static MealRepository getInstance(MealRemoteDataSource mealRemoteDataSource)
    {
        if(randomMealRepo==null)
        {
            randomMealRepo=new MealRepository(mealRemoteDataSource);
        }
        return randomMealRepo;
    }

    public MealRepository(MealRemoteDataSource mealRemoteDataSource)
    {
        this.mealRemoteDataSource = mealRemoteDataSource;
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
}
