package com.example.foodplanner.Home.Countries.Presenter;

import android.util.Log;

import com.example.foodplanner.Home.Countries.View.CountriesViewInterface;
import com.example.foodplanner.Model.MealPojo;
import com.example.foodplanner.NetworkPkg.NetworkCallBackInterface;
import com.example.foodplanner.Repository.MealRepository;

import java.util.List;

public class CountriesPresenter implements CountriesPresenterInterface,NetworkCallBackInterface<MealPojo> {
    private MealRepository remoteDataSource;
    private CountriesViewInterface countriesIview;


    public CountriesPresenter(MealRepository categoriesRepo, CountriesViewInterface countriesIview) {
        this.remoteDataSource = categoriesRepo;
        this.countriesIview = countriesIview;
    }

    @Override
    public void getCountries() {
        remoteDataSource.fetchCountries(this);
    }

    @Override
    public void onSuccessfulResult(List<MealPojo> countries) {
        Log.i("CountriesPresenter", "Fetched countries: " + countries.toString());
        countriesIview.displayCountries(countries);
    }


    @Override
    public void onFailureResult(String errMsg) {
        countriesIview.showError(errMsg);
    }

/*    @Override
    public void onSuccessfulResult(List countries) {
        countriesIview.displayCountries(countries);
    }

    @Override
    public void onFailureResult(String errMsg) {
        countriesIview.showError(errMsg);
    }*/
}