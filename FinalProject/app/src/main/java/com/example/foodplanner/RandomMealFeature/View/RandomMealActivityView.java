package com.example.foodplanner.RandomMealFeature.View;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.RandomMealFeature.Model.RandomMealPojo;
import com.example.foodplanner.RandomMealFeature.Presenter.RandomMealPresenter;
import com.example.foodplanner.RandomMealFeature.View.RandomMealViewInterface;

import java.util.List;

public class RandomMealActivityView extends AppCompatActivity implements RandomMealViewInterface {

    private RandomMealPresenter presenter;
    private TextView mealNameTextView;
    private TextView mealInstructionsTextView;
    private ImageView mealImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_meal);

        // Initialize UI components
        mealNameTextView = findViewById(R.id.mealNameTextView);
        mealInstructionsTextView = findViewById(R.id.mealInstructionsTextView);
        mealImageView = findViewById(R.id.mealImageView);

        // Initialize the presenter
        presenter = new RandomMealPresenter(this);

        // Fetch the random meal data
        presenter.fetchRandomMeal();
    }

    @Override
    public void displayRandomMeal(List<RandomMealPojo> mealList) {
        if (mealList != null && !mealList.isEmpty()) {
            // Assuming we get a single meal in the list
            RandomMealPojo meal = mealList.get(0);

            mealNameTextView.setText(meal.strMeal);
            mealInstructionsTextView.setText(meal.strInstructions);

            // Use Glide or Picasso to load the image
            Glide.with(this).load(meal.strMealThumb).into(mealImageView);
        } else {
            Log.i("RandomMealActivity", "Meal list is empty or null");
            displayError("No meals available");
        }
    }


    @Override
    public void displayError(String errorMessage) {
        Log.e("RandomMealActivity", "Error: " + errorMessage);
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();

    }
}
