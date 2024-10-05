package com.example.foodplanner.RandomMealFeature.View;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.DataBase.MealLocalDataSource;
import com.example.foodplanner.MealDetails.View.MealCalendarFragment;
import com.example.foodplanner.Model.Converter;
import com.example.foodplanner.Model.MealPojoPlan;
import com.example.foodplanner.R;
import com.example.foodplanner.Model.MealPojo;
import com.example.foodplanner.Repository.MealRepository;
import com.example.foodplanner.NetworkPkg.MealRemoteDataSource;
import com.example.foodplanner.RandomMealFeature.Presenter.RandomMealPresenter;

import java.util.ArrayList;
import java.util.List;

public class RandomMealFragment extends Fragment implements RandomMealViewInterface {

    private RandomMealPresenter randomMealPresenter;
    private TextView mealNameTextView;
    private TextView mealInstructionsTextView;
    private ImageView mealImageView;
    private WebView videoWebView;
    private Button addToFavoritesButton; // Add this for favorite button
    private RecyclerView ingredientsRecyclerView;
    private IngredientsAdapter ingredientsAdapter;
    private Button addToCalendarButton; // Add this for favorite button
    MealPojoPlan plannedMeal;
    MealPojo meal;
    private List<String> ingredients;
    private List<String> measures;
    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the fragment layout
        View view = inflater.inflate(R.layout.meal_details_fragment, container, false);

        // Initialize UI components
        mealNameTextView = view.findViewById(R.id.mealNameTextView);
        mealInstructionsTextView = view.findViewById(R.id.mealInstructionsTextView);
        mealImageView = view.findViewById(R.id.mealImageView);
        videoWebView = view.findViewById(R.id.mealVideoWebView);
        addToFavoritesButton = view.findViewById(R.id.addToFavoritesButton); // Initialize the button
        addToCalendarButton=view.findViewById(R.id.addToCalendarButton);
        ingredientsRecyclerView = view.findViewById(R.id.ingredientsRecyclerViewID); // Initialize RecyclerView

        //ingredientsRecyclerView.setLayoutManager(gridLayoutManager);
        ingredientsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        ingredientsAdapter = new IngredientsAdapter(ingredients);
        ingredientsRecyclerView.setAdapter(ingredientsAdapter);

        // Initialize the presenter
        randomMealPresenter = new RandomMealPresenter(this, MealRepository.getInstance(MealRemoteDataSource.getMealRemoteDataSourceInstance(), MealLocalDataSource.getInstance(getContext())));


        // Configure WebView settings
        WebSettings webSettings = videoWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);  // Enable JavaScript for YouTube video playback
        videoWebView.setWebViewClient(new WebViewClient());  // Ensure the video opens within the WebView

        // get the random meal data
        randomMealPresenter.getRandomMeal();

        return view;
    }

    @Override
    public void displayRandomMeal(List<MealPojo> mealList) {
        if (mealList != null && !mealList.isEmpty()) {
             meal = mealList.get(0);
            // Handle Add to Favorite button click
            addToFavoritesButton.setOnClickListener(v -> {
                // Assuming mealNameTextView contains the current meal name or you can pass meal directly
                String mealName = mealNameTextView.getText().toString();
                randomMealPresenter.addMealToFavorites(meal);
                Toast.makeText(getContext(), mealName + " added to Favorites !", Toast.LENGTH_SHORT).show();

            });
            addToCalendarButton.setOnClickListener(v->{
                MealCalendarFragment dialogFragment=new MealCalendarFragment();
                dialogFragment.setOnDateSelectedListener(selectedDate ->{
                    Toast.makeText(getContext(), "Selected date: " + selectedDate, Toast.LENGTH_SHORT).show();
                    plannedMeal= Converter.convertToMealPojoPlan(meal,selectedDate);
                    randomMealPresenter.addMealToCalendar(plannedMeal);

                });

                // Toast.makeText(getContext(), mealName + " added to Calendar !", Toast.LENGTH_SHORT).show();
                dialogFragment.show(getParentFragmentManager(), "calendarDialog");

            });



            mealNameTextView.setText(meal.strMeal);
            mealInstructionsTextView.setText(meal.strInstructions);
            Glide.with(requireContext()).load(meal.strMealThumb).into(mealImageView);

            ingredients=meal.getIngredients();
            measures=meal.getMeasures();
            ingredientsAdapter.setList(ingredients,measures);


            // Check if a YouTube video URL is provided and display it in WebView
            if (meal.strYoutube != null && !meal.strYoutube.isEmpty()) {
                videoWebView.setVisibility(View.VISIBLE);

                // Load the YouTube video
                String videoUrl = meal.strYoutube.replace("watch?v=", "embed/");
                String iframe = "<iframe width=\"100%\" height=\"100%\" src=\"" + videoUrl + "\" frameborder=\"0\" allowfullscreen></iframe>";
                videoWebView.loadData(iframe, "text/html", "utf-8");

            } else {
                videoWebView.setVisibility(View.GONE);
                Log.i("Video", "displayRandomMeal: No Video Available");
            }
            // You could pass the meal object for favorites functionality
        } else {
            Log.i("RandomMealFragment", "Meal list is empty or null");
            displayError("No meals available");
        }
    }

    // Handle Add to Favorites logic
        // Add your logic to store this meal to favorites (e.g., in a database or shared preferences)

    @Override
    public void displayError(String errorMessage) {
        Log.e("RandomMealFragment", "Error: " + errorMessage);
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_LONG).show();
    }
}