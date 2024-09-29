package com.example.foodplanner;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.airbnb.lottie.LottieAnimationView;
import com.example.foodplanner.RandomMealFeature.View.RandomMealFragment;
import com.example.foodplanner.Home.ListMealCategoriesFragment;
import com.example.foodplanner.FavoritesFeature.View.FavoritesFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    // Declare the LottieAnimationView for the animation
    private LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the LottieAnimationView
        lottieAnimationView = findViewById(R.id.lottieAnimationView);

        // Start the Lottie animation when the activity loads
        lottieAnimationView.setVisibility(View.VISIBLE);
        lottieAnimationView.playAnimation(); // Play the Lottie animation initially

        // Delay of 6 seconds for the splash screen
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Hide the Lottie animation after 6 seconds
                lottieAnimationView.setVisibility(View.GONE);
                lottieAnimationView.cancelAnimation(); // Stop the Lottie animation

                // Load the ListMealCategoriesFragment after the splash screen
                loadFragment(new ListMealCategoriesFragment());
            }
        }, 10000); // 6000 milliseconds = 6 seconds

        // Bottom navigation setup
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationID);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                if (item.getItemId() == R.id.nav_homeID) {
                    hideAllFragments();
                    lottieAnimationView.setVisibility(View.VISIBLE);
                    lottieAnimationView.playAnimation();
                    return true; // No fragment to load for Home
                } else if (item.getItemId() == R.id.nav_random_mealID) {
                    selectedFragment = new RandomMealFragment();
                } else if (item.getItemId() == R.id.nav_search_mealID) {
                    selectedFragment = new ListMealCategoriesFragment();
                    Toast.makeText(MainActivity.this, "Search for Meals", Toast.LENGTH_LONG).show();
                } else if (item.getItemId() == R.id.nav_favoritesID) {
                    selectedFragment = new FavoritesFragment();
                    Toast.makeText(MainActivity.this, "Favorite Meals", Toast.LENGTH_LONG).show();
                } else if (item.getItemId() == R.id.nav_categoryID) {
                    selectedFragment = new Fragment(); // Update this with the actual fragment
                    Toast.makeText(MainActivity.this, "Meals Category", Toast.LENGTH_LONG).show();
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

    // Method to hide all fragments in the fragment manager
    private void hideAllFragments() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            transaction.hide(fragment);
        }
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        // Handle the back press gracefully by loading the default fragment (ListMealCategoriesFragment)
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            // Load ListMealCategoriesFragment if no fragment is in the back stack
            loadFragment(new ListMealCategoriesFragment());
        } else {
            // Otherwise, let the default back behavior handle
            super.onBackPressed();
        }
    }
}
