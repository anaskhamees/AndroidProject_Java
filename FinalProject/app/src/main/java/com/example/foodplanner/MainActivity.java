package com.example.foodplanner;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.airbnb.lottie.LottieAnimationView;
import com.example.foodplanner.RandomMealFeature.View.RandomMealFragment;
import com.example.foodplanner.SearchMealFeature.SearchMealFragment; // Ensure you have this
import com.example.foodplanner.FavoritesFeature.View.FavoritesFragment; // Ensure you have this
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    // Declare the LottieAnimationView for the animation
    private LottieAnimationView lottieAnimationView;
    Animation animation; // To use custom animation if needed

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the LottieAnimationView
        lottieAnimationView = findViewById(R.id.lottieAnimationView);

        // Initially show the Lottie animation
        lottieAnimationView.setVisibility(View.VISIBLE);

        // Optional: Load a custom animation (you can skip this if not needed)
       // animation = AnimationUtils.loadAnimation(this, R.anim.anima);
        //lottieAnimationView.startAnimation(animation);

        // Bottom navigation setup
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationID); // Ensure this ID matches your layout
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                if (item.getItemId() == R.id.nav_homeID) {
                    // Hide all fragments and show the Lottie animation when Home is clicked
                    hideAllFragments();
                    lottieAnimationView.setVisibility(View.VISIBLE);
                    lottieAnimationView.playAnimation(); // Play the Lottie animation
                    return true; // No fragment to load for Home
                } else if (item.getItemId() == R.id.nav_random_mealID) {
                    selectedFragment = new RandomMealFragment();
                    lottieAnimationView.setVisibility(View.GONE); // Hide the Lottie animation when "Random Meal" is clicked
                } else if (item.getItemId() == R.id.nav_search_mealID) {
                    selectedFragment = new SearchMealFragment(); // Uncomment and use this when ready
                    lottieAnimationView.setVisibility(View.GONE); // Hide the Lottie animation for this fragment
                    Toast.makeText(MainActivity.this, "Search for Meals", Toast.LENGTH_LONG).show();
                } else if (item.getItemId() == R.id.nav_favoritesID) {
                    selectedFragment = new FavoritesFragment(); // Uncomment and use this when ready
                    Toast.makeText(MainActivity.this, "Favorite Meals ", Toast.LENGTH_LONG).show();
                    lottieAnimationView.setVisibility(View.GONE); // Hide the Lottie animation for this fragment
                } else if (item.getItemId() == R.id.nav_categoryID) {
                    Toast.makeText(MainActivity.this, "Meals Category", Toast.LENGTH_LONG).show();
                    selectedFragment = new Fragment(); // Uncomment and use this when ready
                    lottieAnimationView.setVisibility(View.GONE); // Hide the Lottie animation for this fragment
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
