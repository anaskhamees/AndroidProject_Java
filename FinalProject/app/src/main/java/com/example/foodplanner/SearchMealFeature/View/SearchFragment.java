package com.example.foodplanner.SearchMealFeature.View;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

    // Variables for search type
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

        // Set up RecyclerView
        mealsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        searchAdapter = new SearchAdapter(getContext(), mealList);
        mealsRecyclerView.setAdapter(searchAdapter);

        searchPresenter = new SearchPresenter(MealRepository.getInstance(MealRemoteDataSource.getMealRemoteDataSourceInstance()), this);

        // Set up Spinner with search options
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, searchOptions);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        searchTypeSpinner.setAdapter(spinnerAdapter);

        // Handle search queries
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mealList.clear();
                searchAdapter.notifyDataSetChanged();
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

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return view;
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
        // Implement error display (e.g., Toast, Snackbar)
    }
}
