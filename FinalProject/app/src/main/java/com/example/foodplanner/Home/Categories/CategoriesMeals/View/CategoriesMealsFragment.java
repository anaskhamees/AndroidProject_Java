package com.example.foodplanner.Home.Categories.CategoriesMeals.View;

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
import com.example.foodplanner.Home.Categories.CategoriesMeals.Presenter.MealsFilteredByCategoryPresenter;
import com.example.foodplanner.Model.MealPojo;
import com.example.foodplanner.NetworkPkg.MealRemoteDataSource;
import com.example.foodplanner.R;
import com.example.foodplanner.Repository.MealRepository;

import java.util.ArrayList;
import java.util.List;

public class CategoriesMealsFragment extends Fragment implements MealsFilteredByCategoryViewInterface {

    private RecyclerView mealsRecyclerView;
    private CategoriesMealsAdapter mealAdapter;
    private List<MealPojo> mealList = new ArrayList<>(); // Initialize to avoid NullPointerException
    private MealsFilteredByCategoryPresenter mealsPresenter;

    // Constructor is removed to avoid passing the mealList. Instead, initialize it here.
    public CategoriesMealsFragment() {
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
        mealAdapter = new CategoriesMealsAdapter(getContext(), mealList);
        mealsRecyclerView.setAdapter(mealAdapter);

        // Fetch meals by category (pass category name from the adapter or an activity)
        String selectedCategory = getArguments().getString("CATEGORY_NAME");
        mealsPresenter = new MealsFilteredByCategoryPresenter(MealRepository.getInstance(MealRemoteDataSource.getMealRemoteDataSourceInstance(), MealLocalDataSource.getInstance(getContext())), this);
        mealsPresenter.getMealsFilteredByCategory(selectedCategory);
    }

    @Override
    public void displayMealsFilteredByCategory(List<MealPojo> categoriesMeals) {
        if (categoriesMeals != null) {
            mealList.clear(); // Clear the current list
            mealList.addAll(categoriesMeals); // Add new data to the list
            mealAdapter.notifyDataSetChanged(); // Notify adapter for UI update
        } else {
            // Handle the case when categoriesMeals is null
            displyError("No meals found for this category."); // Optional: show an error message
        }
    }


    @Override
    public void displyError(String errMsg) {
        Toast.makeText(getContext(), errMsg, Toast.LENGTH_SHORT).show();
    }
}
