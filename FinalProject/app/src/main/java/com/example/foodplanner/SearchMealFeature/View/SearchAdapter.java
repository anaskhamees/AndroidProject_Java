package com.example.foodplanner.SearchMealFeature.View;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.Model.MealPojo;
import com.example.foodplanner.R;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    private Context context;
    private List<MealPojo> meals;

    // Constructor
    public SearchAdapter(Context context, List<MealPojo> meals) {
        this.context = context;
        this.meals = meals;
    }

    // Inflates the item layout and returns the ViewHolder
    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_meal_general, parent, false);
        return new SearchViewHolder(view);
    }

    // Binds the data to the views in each item
    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        // Get the current meal from the list
        MealPojo meal = meals.get(position);

        // Set the meal name
        holder.mealNameTextView.setText(meal.getStrMeal());

        // Use Glide to load the meal image into the ImageView
        Glide.with(context)
                .load(meal.getStrMealThumb()) // Image URL or resource
                .into(holder.mealImageView);
    }

    // Returns the total number of items in the data set
    @Override
    public int getItemCount() {
        String count= String.valueOf(meals.size());
        Toast.makeText(context, "Meals Counts :"+count, Toast.LENGTH_SHORT).show();
        Log.i("SearchAdapter", "getItemCount: "+count);
        return meals.size();

    }

    // ViewHolder class that holds the views for each item in RecyclerView
    public static class SearchViewHolder extends RecyclerView.ViewHolder {

        TextView mealNameTextView;
        ImageView mealImageView;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);

            // Initialize the views
            mealNameTextView = itemView.findViewById(R.id.mealNameTextView);
            mealImageView = itemView.findViewById(R.id.mealImageView);
        }
    }
}
