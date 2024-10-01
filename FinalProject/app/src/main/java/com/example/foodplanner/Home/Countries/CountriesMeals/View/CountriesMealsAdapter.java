package com.example.foodplanner.Home.Countries.CountriesMeals.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
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

