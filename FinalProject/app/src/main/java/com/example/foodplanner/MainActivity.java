package com.example.foodplanner;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.foodplanner.RandomMealFeature.View.RandomMealFragment;
import com.example.foodplanner.SearchMealFeature.View.SearchMealFragment; // Ensure you have this
import com.example.foodplanner.FavoritesFeature.View.FavoritesFragment; // Ensure you have this
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private ImageView gifImageView; // Declare the ImageView for the GIF

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gifImageView = findViewById(R.id.gifImageID); // Initialize the ImageView

        // Initially show the GIF
        gifImageView.setVisibility(View.VISIBLE);

        // Bottom navigation setup
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationID); // Ensure this ID matches your layout
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                if (item.getItemId() == R.id.nav_homeID) {
                    // Hide all fragments and show the GIF when Home is clicked
                    hideAllFragments();
                    gifImageView.setVisibility(View.VISIBLE);
                    return true; // No fragment to load for Home
                } else if (item.getItemId() == R.id.nav_random_mealID) {
                    selectedFragment = new RandomMealFragment();
                    gifImageView.setVisibility(View.GONE); // Hide the GIF when "Random Meal" is clicked
                } else if (item.getItemId() == R.id.nav_search_mealID) {
                    selectedFragment = new SearchMealFragment(); // Uncomment and use this when ready
                    gifImageView.setVisibility(View.GONE); // Hide the GIF for this fragment
                } else if (item.getItemId() == R.id.nav_favoritesID) {
                    selectedFragment = new FavoritesFragment(); // Uncomment and use this when ready
                    gifImageView.setVisibility(View.GONE); // Hide the GIF for this fragment
                }

                // Load the selected fragment if one is selected
                return loadFragment(selectedFragment);
            }
        });
    }

    private boolean loadFragment(Fragment fragment) {
        // Load the selected fragment into the fragment container
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainerID, fragment) // Ensure this ID matches your layout
                    .commit();
            return true;
        }
        return false;
    }

    private void hideAllFragments() {
        // Hide all fragments in the fragment manager
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            transaction.hide(fragment);
        }
        transaction.commit();
    }
}
