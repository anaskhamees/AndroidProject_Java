package com.example.foodplanner.Home.Countries.View;

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
import com.example.foodplanner.Model.MealPojo;
import com.example.foodplanner.NetworkPkg.MealRemoteDataSource;
import com.example.foodplanner.R;
import com.example.foodplanner.Repository.MealRepository;
import com.example.foodplanner.Home.Countries.Presenter.CountriesPresenter;
import com.example.foodplanner.Home.Countries.View.CountriesAdapter;

import java.util.ArrayList;
import java.util.List;

public class CountriesFragment extends Fragment implements CountriesViewInterface {

    private RecyclerView recyclerView;
    private CountriesAdapter countriesAdapter;
    private List<MealPojo> countryList;
    private CountriesPresenter countryPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the fragment layout containing the RecyclerView
        return inflater.inflate(R.layout.countries_recycle_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize the list before passing it to the adapter
        countryList = new ArrayList<>();

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.countriesRecycleViewID);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Set up RecyclerView with Adapter
        countriesAdapter = new CountriesAdapter(getContext(),countryList);
        recyclerView.setAdapter(countriesAdapter);

        // Initialize the presenter and fetch countries
        countryPresenter = new CountriesPresenter(MealRepository.getInstance(MealRemoteDataSource.getMealRemoteDataSourceInstance(), MealLocalDataSource.getInstance(getContext())),this);
        countryPresenter.getCountries();
    }

    @Override
    public void displayCountries(List<MealPojo> countries) {
        // Update the list in the adapter
        countryList.clear();  // Clear the old data
        countryList.addAll(countries);  // Add the new data
        countriesAdapter.notifyDataSetChanged();  // Notify the adapter of the data change
    }

    @Override
    public void showError(String errMsg) {
        Toast.makeText(getContext(), errMsg, Toast.LENGTH_SHORT).show();

    }
}

