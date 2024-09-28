package com.example.foodplanner.NetworkPkg;

import android.util.Log;

import com.example.foodplanner.RandomMealFeature.Model.RandomMealPojo;
import com.example.foodplanner.RandomMealFeature.Model.RandomMealResponse;
import com.example.foodplanner.SearchMealFeature.Categories.Model.CategoryPojo;
import com.example.foodplanner.SearchMealFeature.Categories.Model.CategoryResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealRemoteDataSource {

    final static String apiURL = "https://www.themealdb.com/api/json/v1/1/";
    final public static String TAG = "Meal_RetrofitConnection";
    private static MealRemoteDataSource mealRemoteSource = null;
    Retrofit mealRetrofit;
    RandomMealServiceInterface mealAPI;

    private MealRemoteDataSource() {
        mealRetrofit = new Retrofit.Builder().baseUrl(apiURL).addConverterFactory(GsonConverterFactory.create())
                .build();
        mealAPI = mealRetrofit.create(RandomMealServiceInterface.class);
    }

    public static MealRemoteDataSource getMealRemoteDataSourceInstance() {
        if (mealRemoteSource == null) {
            mealRemoteSource = new MealRemoteDataSource();
        }
        return mealRemoteSource;
    }

    public void makeRandomMealNetworkCall(NetworkCallBackInterface<RandomMealPojo> randomMealCallBack )
    {
        Call<RandomMealResponse> randomMealCall= mealAPI.getRandomMealResponse();
        randomMealCall.enqueue(new Callback<RandomMealResponse>() {
            @Override
            public void onResponse(Call<RandomMealResponse> call, Response<RandomMealResponse> response) {
                if (response.isSuccessful()) {
                    Log.d("API Response Successful: ", "Response: " + response.body());
                    Log.i(TAG, "onResponse: Random Meal Successful "+response.body());
                    randomMealCallBack.onSuccessfulResult(response.body().getRandomMealResponse());

                } else {
                    Log.e("API Error", "Error code: " + response.code());
                }
            }



            @Override
            public void onFailure(Call<RandomMealResponse> call, Throwable t) {

                randomMealCallBack.onFailureResult(t.getMessage());
                t.printStackTrace();
                Log.i(TAG, "onFailure: Failed to Fetch data from API Server");
                Log.e("API Error", "Error: " + t.getMessage());

            }
        });
    }

    public void makeCategoryNetworkCall(NetworkCallBackInterface<CategoryPojo> randomMealCallBack )
    {
        Call<CategoryResponse> categoryMealCall= mealAPI.getCategoryResponse();
        categoryMealCall.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if (response.isSuccessful()) {
                    Log.d("API Response Successful: ", "Response: " + response.body());
                    Log.i(TAG, "onResponse: Categories Successful "+response.body());
                    randomMealCallBack.onSuccessfulResult(response.body().getCategoryResponse());

                } else {
                    Log.e("API Error", "Error code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {

                randomMealCallBack.onFailureResult(t.getMessage());
                t.printStackTrace();
                Log.i(TAG, "onFailure: Failed to Fetch data from API Server");
                Log.e("API Error", "Error: " + t.getMessage());

            }
        });
    }


}
