package com.example.foodplanner.Home.Countries.CountriesMeals.View;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.foodplanner.MealDetails.View.MealDetailsFragment;
import com.example.foodplanner.Model.MealPojo;
import com.example.foodplanner.R;
import java.util.List;

public class CountriesMealsAdapter extends RecyclerView.Adapter<CountriesMealsAdapter.MealViewHolder> {

    private Context context;
    private List<MealPojo> mealList;

    public CountriesMealsAdapter(Context context, List<MealPojo> mealList) {
        this.context = context;
        this.mealList = mealList;
    }

    @NonNull
    @Override
    public MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each meal item
        View view = LayoutInflater.from(context).inflate(R.layout.country_meals, parent, false);
        return new MealViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealViewHolder holder, int position) {
        MealPojo meal = mealList.get(position);

        // Set meal name
        holder.mealNameTextView.setText(meal.getStrMeal());

        // Load meal image using Glide
        Glide.with(context)
                .load(meal.getStrMealThumb())
                .placeholder(R.drawable.loading) // Optional: Placeholder while loading
                .error(R.drawable.loading) // Optional: Error image if loading fails
                .into(holder.mealImageView);

        // Set up the click listener for the item
        holder.itemView.setOnClickListener(v -> {
            // Create a new instance of MealDetailsFragment
            MealDetailsFragment fragment = new MealDetailsFragment();

            // Create a bundle to pass the selected meal name to the new fragment
            Bundle bundle = new Bundle();
            bundle.putString("MEAL_NAME", meal.getStrMeal()); // Pass the selected meal name
            fragment.setArguments(bundle);

            // Replace the current fragment with the new one using FragmentManager
            ((FragmentActivity) context).getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainerID, fragment) // Adjust to your fragment container ID
                    .addToBackStack(null)  // Add the transaction to the back stack for navigation
                    .commit();
        });
    }

    @Override
    public int getItemCount() {
        return mealList != null ? mealList.size() : 0; // Ensure safe access
    }

    // ViewHolder class to bind UI elements
    static class MealViewHolder extends RecyclerView.ViewHolder {
        TextView mealNameTextView;
        ImageView mealImageView;

        public MealViewHolder(@NonNull View itemView) {
            super(itemView);
            // Bind views
            mealNameTextView = itemView.findViewById(R.id.mealNameTextView);
            mealImageView = itemView.findViewById(R.id.mealImageView);
        }
    }
}

