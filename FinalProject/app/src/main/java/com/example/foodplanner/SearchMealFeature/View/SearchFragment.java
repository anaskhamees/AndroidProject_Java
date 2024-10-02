package com.example.foodplanner.SearchMealFeature.View;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.SearchView;
import android.widget.Toast;

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
    private CheckBox mealCheckBox, countriesCheckBox, categoriesCheckBox, ingredientsCheckBox;
    private List<MealPojo> mealList = new ArrayList<>();
    private SearchPresenter searchPresenter;

    // Variables to track the selected search type
    private boolean isMealSelected = false;
    private boolean isCountrySelected = false;
    private boolean isCategorySelected = false;
    private boolean isIngredientSelected = false;

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
        mealCheckBox = view.findViewById(R.id.mealCheckBox1ID);
        countriesCheckBox = view.findViewById(R.id.countriesCheckBox2ID);
        categoriesCheckBox = view.findViewById(R.id.categoriesCheckBox3ID);
        ingredientsCheckBox = view.findViewById(R.id.ingretientCheckBox4ID);

        mealsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        searchAdapter = new SearchAdapter(getContext(), mealList);
        mealsRecyclerView.setAdapter(searchAdapter);

        searchPresenter = new SearchPresenter(MealRepository.getInstance(MealRemoteDataSource.getMealRemoteDataSourceInstance()), this);

        // Set up checkbox listeners to update the search mode
        mealCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                resetCheckBoxesExcept(mealCheckBox);
                isMealSelected = true;
                searchView.setQueryHint("Search By Meal Name ...");
            } else {
                isMealSelected = false;
            }
        });

        countriesCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                resetCheckBoxesExcept(countriesCheckBox);
                isCountrySelected = true;
                searchView.setQueryHint("Search By Country Name ...");
            } else {
                isCountrySelected = false;
            }
        });

        categoriesCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                resetCheckBoxesExcept(categoriesCheckBox);
                isCategorySelected = true;
                searchView.setQueryHint("Search By Category Name ...");
            } else {
                isCategorySelected = false;
            }
        });

        ingredientsCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                resetCheckBoxesExcept(ingredientsCheckBox);
                isIngredientSelected = true;
                searchView.setQueryHint("Search By Ingredient Name ...");
            } else {
                isIngredientSelected = false;
            }
        });

        // Handle search queries based on selected checkbox
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mealList.clear();
                searchAdapter.notifyDataSetChanged();
                if (isMealSelected) {
                   // searchPresenter.searchMealsByName(query);
                } else if (isCountrySelected) {
                    searchPresenter.getMealsByCountry(query); //Enter the Country
                } else if (isCategorySelected) {
                    searchPresenter.getMealsByCategory(query);
                } else if (isIngredientSelected) {
                   // searchPresenter.searchMealsByIngredient(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return view;
    }

    // Method to reset other checkboxes when one is selected
    private void resetCheckBoxesExcept(CheckBox selectedCheckBox) {
        if (selectedCheckBox != mealCheckBox) mealCheckBox.setChecked(false);
        if (selectedCheckBox != countriesCheckBox) countriesCheckBox.setChecked(false);
        if (selectedCheckBox != categoriesCheckBox) categoriesCheckBox.setChecked(false);
        if (selectedCheckBox != ingredientsCheckBox) ingredientsCheckBox.setChecked(false);
    }

    @Override
    public void displayMeals(List<MealPojo> meals) {
        if(meals!=null)
        {
            mealList.clear();
            mealList.addAll(meals);
            searchAdapter.notifyDataSetChanged();
        }
        else {
            Toast.makeText(getContext(), "No Meals Found", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void displayError(String errMsg) {
        // Implement error display (e.g., Toast, Snackbar)
    }
}
