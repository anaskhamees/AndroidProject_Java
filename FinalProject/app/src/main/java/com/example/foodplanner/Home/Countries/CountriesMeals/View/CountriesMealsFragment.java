package com.example.foodplanner.Home.Countries.CountriesMeals.View;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.DataBase.MealLocalDataSource;
import com.example.foodplanner.Home.Countries.CountriesMeals.Presenter.MealsFilteredByCountriesPresenter;
import com.example.foodplanner.Home.Countries.CountriesMeals.Presenter.MealsFilteredByCountriesPresenterInterface;
import com.example.foodplanner.Model.MealPojo;
import com.example.foodplanner.NetworkPkg.MealRemoteDataSource;
import com.example.foodplanner.R;
import com.example.foodplanner.Repository.MealRepository;

import java.util.ArrayList;
import java.util.List;

public class CountriesMealsFragment extends Fragment implements MealsFilteredByCountriesViewInterface {

    private RecyclerView mealsRecyclerView;
    private CountriesMealsAdapter mealAdapter;
    private List<MealPojo> mealList = new ArrayList<>(); // Initialize to avoid NullPointerException
    private MealsFilteredByCountriesPresenter mealsPresenter;

    // Constructor is removed to avoid passing the mealList. Instead, initialize it here.
    public CountriesMealsFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.categories_meals_recycle_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Setup RecyclerView
        mealsRecyclerView = view.findViewById(R.id.categoriesMealsRecycleViewID);
        mealsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize adapter with empty meal list
        mealAdapter = new CountriesMealsAdapter(getContext(), mealList);
        mealsRecyclerView.setAdapter(mealAdapter);

        // Fetch meals by category (pass category name from the adapter or an activity)
        String selectedCountry = getArguments().getString("COUNTRY_NAME");
        mealsPresenter = new MealsFilteredByCountriesPresenter(MealRepository.getInstance(MealRemoteDataSource.getMealRemoteDataSourceInstance(), MealLocalDataSource.getInstance(getContext())), this);
        mealsPresenter.getMealsFilteredByCountry(selectedCountry);
    }


    @Override
    public void displayMealsFilteredByCountry(List<MealPojo> countriesMeals) {
        mealList.clear();
        mealList.addAll(countriesMeals);
        mealAdapter.notifyDataSetChanged();
    }

    @Override
    public void displyError(String errMsg) {
        Toast.makeText(getContext(), errMsg, Toast.LENGTH_SHORT).show();
    }

}

