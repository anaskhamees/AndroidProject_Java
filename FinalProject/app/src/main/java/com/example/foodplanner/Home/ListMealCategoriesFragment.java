package com.example.foodplanner.Home;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.foodplanner.Home.Countries.View.CountriesFragment;
import com.example.foodplanner.R;
import com.example.foodplanner.Home.Categories.View.CategoriesFragment;  // Import your CategoriesFragment
import com.example.foodplanner.RandomMealFeature.View.RandomMealFragment;

public class ListMealCategoriesFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.home_search_fragment, container, false);

        // Set onClickListener for the Cards
        view.findViewById(R.id.categoriesImage).setOnClickListener(v -> {
           // showToast("Categories");
            openCategoriesFragment();  // Call method to switch to CategoriesFragment
        });

        view.findViewById(R.id.countriesImage).setOnClickListener(v -> {
           // showToast("Countries");
            openCountriesFragment();

        });

        view.findViewById(R.id.randomMealImage).setOnClickListener(v -> {
          //  showToast("Arbitrary Meal");
            openRandomMealFragment();

        });

        view.findViewById(R.id.ingredientsImage).setOnClickListener(v -> showToast("Ingredients"));

        return view;
    }

    private void showToast(String message) {
        Toast.makeText(getActivity(), message + " clicked", Toast.LENGTH_SHORT).show();
    }

    private void openCategoriesFragment() {
        // Create an instance of CategoriesFragment
        CategoriesFragment categoriesFragment = new CategoriesFragment();

        // Get FragmentManager and start a transaction
        FragmentManager fragmentManager = getParentFragmentManager();  // Use getParentFragmentManager() instead of getFragmentManager()
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Replace the current fragment with CategoriesFragment
        fragmentTransaction.replace(R.id.fragmentContainerID, categoriesFragment);

        // Optionally, add the transaction to the back stack if you want to allow back navigation
        fragmentTransaction.addToBackStack(null);

        // Commit the transaction
        fragmentTransaction.commit();
    }

    private void openCountriesFragment() {
        // Create an instance of CategoriesFragment
        CountriesFragment countriesFragment = new CountriesFragment();

        // Get FragmentManager and start a transaction
        FragmentManager fragmentManager = getParentFragmentManager();  // Use getParentFragmentManager() instead of getFragmentManager()
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Replace the current fragment with CategoriesFragment
        fragmentTransaction.replace(R.id.fragmentContainerID, countriesFragment);

        // Optionally, add the transaction to the back stack if you want to allow back navigation
        fragmentTransaction.addToBackStack(null);

        // Commit the transaction
        fragmentTransaction.commit();
    }

    private void openRandomMealFragment() {
        // Create an instance of CategoriesFragment
        RandomMealFragment randomMealFragment = new RandomMealFragment();

        // Get FragmentManager and start a transaction
        FragmentManager fragmentManager = getParentFragmentManager();  // Use getParentFragmentManager() instead of getFragmentManager()
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Replace the current fragment with CategoriesFragment
        fragmentTransaction.replace(R.id.fragmentContainerID, randomMealFragment);

        // Optionally, add the transaction to the back stack if you want to allow back navigation
        fragmentTransaction.addToBackStack(null);

        // Commit the transaction
        fragmentTransaction.commit();
    }
}
