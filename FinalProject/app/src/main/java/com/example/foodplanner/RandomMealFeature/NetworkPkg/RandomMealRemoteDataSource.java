package com.example.foodplanner.RandomMealFeature.NetworkPkg;

import android.util.Log;

import com.example.foodplanner.RandomMealFeature.Model.RandomMealPojo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RandomMealRemoteDataSource {

    final static String apiURL = "https://www.themealdb.com/api/json/v1/1/";
    final public static String TAG = "RandomMeal_RetrofitConnection";
    private static RandomMealRemoteDataSource randomMealRemoteSource = null;
    Retrofit randomMealRetrofit;
    RandomMealServiceInterface randomMealAPI;

    private RandomMealRemoteDataSource() {
        randomMealRetrofit = new Retrofit.Builder().baseUrl(apiURL).addConverterFactory(GsonConverterFactory.create())
                .build();
        randomMealAPI = randomMealRetrofit.create(RandomMealServiceInterface.class);
    }

    public static RandomMealRemoteDataSource getRandomMealRemoteDataSourceInstance() {
        if (randomMealRemoteSource == null) {
            randomMealRemoteSource = new RandomMealRemoteDataSource();
        }
        return randomMealRemoteSource;
    }

    public void makeRandomMealNetworkCall(RandomMealNetworkCallBackInterface randomMealCallBack )
    {
        Call<RandomMealResponse> randomMealCall=randomMealAPI.getRandomMealResponse();
        randomMealCall.enqueue(new Callback<RandomMealResponse>() {
            @Override
            public void onResponse(Call<RandomMealResponse> call, Response<RandomMealResponse> response) {
                if (response.isSuccessful()) {
                    Log.d("API Response Successful: ", "Response: " + response.body());
                    Log.i(TAG, "onResponse: Successful "+response.body());
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


}
