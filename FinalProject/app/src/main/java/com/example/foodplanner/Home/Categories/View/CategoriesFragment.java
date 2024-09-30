package com.example.foodplanner.Home.Categories.View;

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

import com.example.foodplanner.Home.Categories.CategoriesMeals.Presenter.MealsFilteredByCategoryPresenter;
import com.example.foodplanner.NetworkPkg.MealRemoteDataSource;
import com.example.foodplanner.R;
import com.example.foodplanner.Repository.MealRepository;
import com.example.foodplanner.Home.Categories.Model.CategoryPojo;
import com.example.foodplanner.Home.Categories.Presenter.CategoryPresenter;

import java.util.ArrayList;
import java.util.List;

public class CategoriesFragment extends Fragment implements CategoriesViewInterface {

    private RecyclerView recyclerView;
    private CategoryAdapter categoryAdapter;
    private List<CategoryPojo> categoryList;
    private CategoryPresenter categoryPresenter;
    private MealsFilteredByCategoryPresenter mealsPresenter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the fragment layout containing the RecyclerView
        return inflater.inflate(R.layout.categories_recycle_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize the list before passing it to the adapter
        categoryList = new ArrayList<>();

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.categoriesRecycleViewID);

        // Set up RecyclerView with LayoutManager and Adapter
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        categoryAdapter = new CategoryAdapter(getContext(), categoryList);  // Pass the initialized list
        recyclerView.setAdapter(categoryAdapter);
       // categoryAdapter.setOnCategoryClickListener(this);
        // Fetch categories
        categoryPresenter = new CategoryPresenter(this, MealRepository.getInstance(MealRemoteDataSource.getMealRemoteDataSourceInstance()));
        categoryPresenter.getCategories();
    }

    @Override
    public void displayCategories(List<CategoryPojo> categories) {
        // Update the list in the adapter
        categoryList.clear();  // Clear the old data
        categoryList.addAll(categories);  // Add the new data
        categoryAdapter.notifyDataSetChanged();  // Notify the adapter of the data change
    }

    @Override
    public void displayError(String errMsg) {
        // Display error message to the user
        Toast.makeText(getContext(), errMsg, Toast.LENGTH_SHORT).show();
    }

}
