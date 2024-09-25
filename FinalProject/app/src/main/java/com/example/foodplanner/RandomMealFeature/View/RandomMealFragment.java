package com.example.foodplanner.RandomMealFeature.View;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import android.widget.MediaController;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.RandomMealFeature.Model.RandomMealPojo;
import com.example.foodplanner.RandomMealFeature.Presenter.RandomMealPresenter;

import java.util.List;

public class RandomMealFragment extends Fragment implements RandomMealViewInterface {

    private RandomMealPresenter presenter;
    private TextView mealNameTextView;
    private TextView mealInstructionsTextView;
    private ImageView mealImageView;
    private VideoView videoView;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the fragment layout
        View view = inflater.inflate(R.layout.fragment_random_meal, container, false);

        // Initialize UI components
        mealNameTextView = view.findViewById(R.id.mealNameTextView);
        mealInstructionsTextView = view.findViewById(R.id.mealInstructionsTextView);
        mealImageView = view.findViewById(R.id.mealImageView);
        videoView=view.findViewById(R.id.mealVideoID);
        // Initialize the presenter
        presenter = new RandomMealPresenter(this);

        // Fetch the random meal data
        presenter.fetchRandomMeal();

        return view;
    }

    @Override
    public void displayRandomMeal(List<RandomMealPojo> mealList) {
        if (mealList != null && !mealList.isEmpty()) {
            // Assuming we get a single meal in the list
            RandomMealPojo meal = mealList.get(0);

            mealNameTextView.setText(meal.strMeal);
            mealInstructionsTextView.setText(meal.strInstructions);

            // Use Glide or Picasso to load the image
            Glide.with(requireContext()).load(meal.strMealThumb).into(mealImageView);

            // Check if a YouTube video URL is provided and play the video
            if (meal.strYoutube != null && !meal.strYoutube.isEmpty()) {
                videoView.setVisibility(View.VISIBLE);

                // Extract the video ID from the YouTube URL
                String videoUrl = meal.strYoutube;
                // Load the YouTube video in a VideoView or Intent
                videoView.setVideoPath(videoUrl);
                videoView.start();

                // Optionally, handle playback controls if needed
                videoView.setOnCompletionListener(mp -> {
                    // Logic for completion of video playback
                });
            } else {
                videoView.setVisibility(View.GONE);
                Log.i("Video  : ", "displayRandomMeal: Video Not Work  ");
            }
        } else {
            Log.i("RandomMealFragment", "Meal list is empty or null");
            displayError("No meals available");
        }
    }

    @Override
    public void displayError(String errorMessage) {
        Log.e("RandomMealFragment", "Error: " + errorMessage);
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_LONG).show();
    }
}
