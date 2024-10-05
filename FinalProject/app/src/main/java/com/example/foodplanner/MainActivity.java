package com.example.foodplanner;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.foodplanner.CalendarFeature.View.CalendarFragment;
import com.example.foodplanner.RandomMealFeature.View.RandomMealFragment;
import com.example.foodplanner.Home.ListMealCategoriesFragment;
import com.example.foodplanner.FavoritesFeature.View.FavoritesFragment;
import com.example.foodplanner.SearchMealFeature.View.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Load the default fragment (ListMealCategoriesFragment)
        loadFragment(new ListMealCategoriesFragment());

        // Bottom navigation setup
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationID);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                if (item.getItemId() == R.id.nav_homeID) {
                    selectedFragment = new ListMealCategoriesFragment();
                } else if (item.getItemId() == R.id.calendarMealID) {
                    selectedFragment = new CalendarFragment();
                } else if (item.getItemId() == R.id.nav_search_mealID) {
                    selectedFragment = new SearchFragment();
                    Toast.makeText(MainActivity.this, "Search for Meals", Toast.LENGTH_LONG).show();
                } else if (item.getItemId() == R.id.nav_favoritesID) {
                    selectedFragment = new FavoritesFragment();
                    Toast.makeText(MainActivity.this, "Favorite Meals", Toast.LENGTH_LONG).show();
                 }

                return loadFragment(selectedFragment);
            }
        });
    }

    // Method to load a fragment into the container
    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainerID, fragment) // Ensure this ID matches your layout
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        // Handle the back press gracefully
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            loadFragment(new ListMealCategoriesFragment());
        } else {
            super.onBackPressed();
        }
    }
}
