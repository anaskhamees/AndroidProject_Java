package com.example.foodplanner.CalendarFeature.View;

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

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.CalendarFeature.Presenter.CalendarPresenter;
import com.example.foodplanner.DataBase.MealLocalDataSource;
import com.example.foodplanner.Model.MealPojoPlan;
import com.example.foodplanner.R;
import com.example.foodplanner.Model.MealPojo;
import com.example.foodplanner.RandomMealFeature.View.IngredientsAdapter;
import com.example.foodplanner.Repository.MealRepository;
import com.example.foodplanner.NetworkPkg.MealRemoteDataSource;

import java.util.List;

public class CalendarMealDetailsFragment extends Fragment implements CalendarViewInterface ,OnPlannedClickListener{

    private CalendarPresenter calendarPresenter;
    private TextView mealNameTextView;
    private TextView mealInstructionsTextView;
    private ImageView mealImageView;
    private WebView videoWebView;
    private Button removeFromCalendarButton; // Add this for favorite button
    private RecyclerView ingredientsRecyclerView;
    private IngredientsAdapter ingredients_Adapter;
    MealPojoPlan plannedMeal;
    private List<String> ingredients;
    private List<String> measures;
    String date;
    //public static FavMealDetailsFragment getInstance(MealPojo meal)
    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the fragment layout
        View view = inflater.inflate(R.layout.calendar_meal_details, container, false);

        // Initialize UI components
        mealNameTextView = view.findViewById(R.id.mealNameTextView);
        mealInstructionsTextView = view.findViewById(R.id.mealInstructionsTextView);
        mealImageView = view.findViewById(R.id.mealImageView);
        videoWebView = view.findViewById(R.id.mealVideoWebView);
        removeFromCalendarButton = view.findViewById(R.id.removeFromCalendarButtonID); // Initialize the button
        ingredientsRecyclerView = view.findViewById(R.id.ingredientsRecyclerViewID); // Initialize RecyclerView


        //ingredientsRecyclerView.setLayoutManager(gridLayoutManager);
        ingredientsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        ingredients_Adapter = new IngredientsAdapter(ingredients);
        ingredientsRecyclerView.setAdapter(ingredients_Adapter);

        // Initialize the presenter
        calendarPresenter = new CalendarPresenter(this,MealRepository.getInstance(MealRemoteDataSource.getMealRemoteDataSourceInstance(), MealLocalDataSource.getInstance(getContext())));
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Pop the current fragment and go back to the previous one (FavoritesFragment)
                getParentFragmentManager().popBackStack();
            }
        });


        // Configure WebView settings
        WebSettings webSettings = videoWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);  // Enable JavaScript for YouTube video playback
        videoWebView.setWebViewClient(new WebViewClient());  // Ensure the video opens within the WebView

        if(getArguments()!=null)
        {
            plannedMeal = (MealPojoPlan) getArguments().getSerializable("meal");
            if (plannedMeal != null) {
                displayPlannedMeals(plannedMeal);
            } else {
                Log.e("CalendarMealDetailsFragment", "Received meal object is null");
            }
        }
        // get the  meal data
        calendarPresenter.getPlannedFood(date);
        // Handle Add to Favorite button click
        removeFromCalendarButton.setOnClickListener(v -> {
            // Call the presenter method to remove the meal from favorites
            if (plannedMeal != null) {
                calendarPresenter.removeMealFromCalendar(plannedMeal);
                Toast.makeText(getContext(), plannedMeal.getStrMeal()+" Removed from Calendar", Toast.LENGTH_SHORT).show();
                getParentFragmentManager().popBackStack();  // This will navigate back to the previous fragment

            } else {
                Toast.makeText(getContext(), "No meal to remove", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
    public void displayPlannedMeals(MealPojoPlan mealList) {
        if (mealList != null ) {
            //meal = mealList.get(0);

            mealNameTextView.setText(plannedMeal.strMeal);
            mealInstructionsTextView.setText(plannedMeal.strInstructions);
            Glide.with(requireContext()).load(plannedMeal.strMealThumb).into(mealImageView);

            ingredients= plannedMeal.getIngredients();
            measures= plannedMeal.getMeasures();
            ingredients_Adapter.setList(ingredients,measures);


            // Check if a YouTube video URL is provided and display it in WebView
            if (plannedMeal.strYoutube != null && !plannedMeal.strYoutube.isEmpty()) {
                videoWebView.setVisibility(View.VISIBLE);

                // Load the YouTube video
                String videoUrl = plannedMeal.strYoutube.replace("watch?v=", "embed/");
                String iframe = "<iframe width=\"100%\" height=\"100%\" src=\"" + videoUrl + "\" frameborder=\"0\" allowfullscreen></iframe>";
                videoWebView.loadData(iframe, "text/html", "utf-8");

            } else {
                videoWebView.setVisibility(View.GONE);
                Log.i("Video", "displayRandomMeal: No Video Available");
            }
            // You could pass the meal object for favorites functionality
            //addToFavoritesButton.setOnClickListener(v -> addMealToFavorites(meal.strMeal));
        } else {
            Log.i("RandomMealFragment", "Meal list is empty or null");
            displayError("No meals available");
        }
    }


    @Override
    public void displayPlannedMeals(List<MealPojoPlan> mealList) {

    }

    @Override
    public void displayError(String errorMessage) {
        Log.e("RandomMealFragment", "Error: " + errorMessage);
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_LONG).show();
    }


    @Override
    public void onPlannedMealClick(MealPojoPlan meal) {

    }
}