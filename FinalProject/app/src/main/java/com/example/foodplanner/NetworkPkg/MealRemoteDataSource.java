package com.example.foodplanner.NetworkPkg;

import android.util.Log;
import android.widget.Toast;

import com.example.foodplanner.Home.Countries.Model.CountryResponse;
import com.example.foodplanner.Model.MealPojo;
import com.example.foodplanner.Model.MealResponse;
import com.example.foodplanner.Home.Categories.Model.CategoryPojo;
import com.example.foodplanner.Home.Categories.Model.CategoryResponse;

import java.util.List;

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
    MealServiceInterface mealAPI;

    private MealRemoteDataSource() {
        mealRetrofit = new Retrofit.Builder().baseUrl(apiURL).addConverterFactory(GsonConverterFactory.create())
                .build();
        mealAPI = mealRetrofit.create(MealServiceInterface.class);
    }

    public static MealRemoteDataSource getMealRemoteDataSourceInstance() {
        if (mealRemoteSource == null) {
            mealRemoteSource = new MealRemoteDataSource();
        }
        return mealRemoteSource;
    }

    public void makeRandomMealNetworkCall(NetworkCallBackInterface<MealPojo> randomMealCallBack )
    {
        Call<MealResponse> randomMealCall= mealAPI.getRandomMealResponse();
        randomMealCall.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if (response.isSuccessful()) {
                    Log.d("API Response Successful: ", "Response: " + response.body());
                    Log.i(TAG, "onResponse: Random Meal Successful "+response.body());
                    randomMealCallBack.onSuccessfulResult(response.body().getMealResponse());

                } else {
                    Log.e("API Error", "Error code: " + response.code());
                }
            }



            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {

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

    public void makeMealFilteredByCategoryNetworkCall(NetworkCallBackInterface<MealPojo> MealCallBack ,String category)
    {
        Call<MealResponse> MealFilteredByCategoryCall= mealAPI.getMealFilteredByCategory(category);
        MealFilteredByCategoryCall.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("API Response Successful: ", "Response: " + response.body());
                    Log.i(TAG, "onResponse: Random Meal Successful "+response.body());
                    MealCallBack.onSuccessfulResult(response.body().getMealResponse());

                } else {
                    Log.i("API Error for fetch meals filter by category", "Error code: " + response.code());
                    MealCallBack.onFailureResult("No meals found");
                }
            }
            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {

                MealCallBack.onFailureResult(t.getMessage());
                t.printStackTrace();
                Log.i("API Error for fetch meals filter by category",t.getMessage());
              //  Log.e("API Error", "Error: " + t.getMessage());

            }
        });
    }

    public void makeCountriesCallBack(NetworkCallBackInterface<MealPojo> callBack)
    {
        Call<CountryResponse> call =mealAPI.getCountriesResponse();
        call.enqueue(new Callback<CountryResponse>() {
            @Override
            public void onResponse(Call<CountryResponse> call, Response<CountryResponse> response) {
                if(response.isSuccessful()&&response.body()!=null)
                {
                    List<MealPojo> countriesFlags=response.body().getCountryResponse();
                    callBack.onSuccessfulResult(countriesFlags);
                }else {
                    callBack.onFailureResult("Error Fetch Countries");
                }
            }

            @Override
            public void onFailure(Call<CountryResponse> call, Throwable t) {
                callBack.onFailureResult(t.getMessage());
            }
        });
    }

    public void makeMealFilteredByCountryNetworkCall(NetworkCallBackInterface<MealPojo> MealCallBack ,String country)
    {
        Call<MealResponse> MealFilteredByCountryCall= mealAPI.getMealFilteredByCountry(country);
        MealFilteredByCountryCall.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("API Response Successful: ", "Response: " + response.body());
                    Log.i(TAG, "onResponse: Country Meal Successful "+response.body());
                    MealCallBack.onSuccessfulResult(response.body().getMealResponse());

                } else {
                    Log.i("API Error for fetch meals filter by country", "Error code: " + response.code());
                    MealCallBack.onFailureResult("No meals found");

                }
            }
            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {

                MealCallBack.onFailureResult(t.getMessage());
                t.printStackTrace();
                Log.i("API Error for fetch meals filter by country",t.getMessage());
                //  Log.e("API Error", "Error: " + t.getMessage());

            }
        });
    }

    public void makeMealByNameNetworkCallBack(NetworkCallBackInterface<MealPojo> MealCallBack ,String name)
    {
        Call<MealResponse> MealByNameCall= mealAPI.getMealByName(name);
        MealByNameCall.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.i(TAG, "onResponse:  MealByName Successful "+response.body());
                    MealCallBack.onSuccessfulResult(response.body().getMealResponse());

                } else {
                    Log.i("API Error for fetch meals filter by name", "Error code: " + response.code());
                    MealCallBack.onFailureResult("No meals found");
                }
            }
            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                MealCallBack.onFailureResult(t.getMessage());
                t.printStackTrace();
                Log.i("API Error for fetch meals filter by name",t.getMessage());
                //  Log.e("API Error", "Error: " + t.getMessage());

            }
        });
    }

    public void makeMealByIngredientNetworkCallBack(NetworkCallBackInterface<MealPojo> MealCallBack ,String ingredient)
    {
        Call<MealResponse> MealByIngredientCall= mealAPI.getMealFilteredByIngredient(ingredient);
        MealByIngredientCall.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.i(TAG, "onResponse:  MealByIngredient Successful "+response.body());
                    MealCallBack.onSuccessfulResult(response.body().getMealResponse());

                } else {
                    Log.i("API Error for fetch meals filter by ingredient", "Error code: " + response.code());
                    MealCallBack.onFailureResult("No meals found");
                }
            }
            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                MealCallBack.onFailureResult(t.getMessage());
                t.printStackTrace();
                Log.i("API Error for fetch meals filter by ingredient",t.getMessage());
                //  Log.e("API Error", "Error: " + t.getMessage());

            }
        });
    }



}
