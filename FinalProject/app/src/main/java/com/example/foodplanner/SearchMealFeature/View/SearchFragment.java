package com.example.foodplanner.SearchMealFeature.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.DataBase.MealLocalDataSource;
import com.example.foodplanner.Model.MealPojo;
import com.example.foodplanner.NetworkPkg.MealRemoteDataSource;
import com.example.foodplanner.R;
import com.example.foodplanner.Repository.MealRepository;
import com.example.foodplanner.SearchMealFeature.Presenter.SearchPresenter;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment implements SearchViewInterface {

    private SearchAdapter searchAdapter;
    private RecyclerView mealsRecyclerView;
    private SearchView searchView;
    private Spinner searchTypeSpinner;
    private List<MealPojo> mealList = new ArrayList<>();
    private SearchPresenter searchPresenter;

    private String[] searchOptions = {"Meal", "Countries", "Categories", "Ingredients"};

    public SearchFragment() {
        // Required empty public constructor
    }

    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_fragment, container, false);

        mealsRecyclerView = view.findViewById(R.id.MealsRecyclerViewID);
        searchView = view.findViewById(R.id.searchViewID);
        searchTypeSpinner = view.findViewById(R.id.searchTypeSpinner);

        mealsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        searchAdapter = new SearchAdapter(getContext(), mealList);
        mealsRecyclerView.setAdapter(searchAdapter);

        searchPresenter = new SearchPresenter(MealRepository.getInstance(MealRemoteDataSource.getMealRemoteDataSourceInstance(), MealLocalDataSource.getInstance(getContext())), this);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, searchOptions);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        searchTypeSpinner.setAdapter(spinnerAdapter);

        searchTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedSearchType = searchOptions[position];
                updateSearchHint(selectedSearchType);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query.isEmpty()) {
                    Toast.makeText(getContext(), "Please enter a query", Toast.LENGTH_SHORT).show();
                    return false;
                }
                String selectedSearchType = searchTypeSpinner.getSelectedItem().toString();
                if (selectedSearchType.equals("Meal")) {
                    searchPresenter.getMealsByName(query);
                } else if (selectedSearchType.equals("Countries")) {
                    searchPresenter.getMealsByCountry(query);
                } else if (selectedSearchType.equals("Categories")) {
                    searchPresenter.getMealsByCategory(query);
                } else if (selectedSearchType.equals("Ingredients")) {
                    searchPresenter.getMealsByIngredient(query);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return view;
    }

    private void updateSearchHint(String selectedSearchType) {
        switch (selectedSearchType) {
            case "Meal":
                searchView.setQueryHint("Search By Meal Name . . .");
                break;
            case "Countries":
                searchView.setQueryHint("Search By Cuisine Name . . .");
                break;
            case "Categories":
                searchView.setQueryHint("Search By Category Name . . .");
                break;
            case "Ingredients":
                searchView.setQueryHint("Search By Ingredient Name . . .");
                break;
        }
    }

    @Override
    public void displayMeals(List<MealPojo> meals) {
        if (meals != null) {
            mealList.clear();
            mealList.addAll(meals);
            searchAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(getContext(), "No Meals Found", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void displayError(String errMsg) {
        Toast.makeText(getContext(), "Error: " + errMsg, Toast.LENGTH_SHORT).show();
    }
}
