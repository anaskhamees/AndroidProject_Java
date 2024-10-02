package com.example.foodplanner.Home.Categories.CategoriesMeals.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.webkit.WebView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.foodplanner.Model.MealPojo;
import com.example.foodplanner.R;
import java.util.List;

public class CategoriesMealsAdapter extends RecyclerView.Adapter<CategoriesMealsAdapter.MealViewHolder> {

    private Context context;
    private List<MealPojo> mealList;

    public CategoriesMealsAdapter(Context context, List<MealPojo> mealList) {
        this.context = context;
        this.mealList = mealList;
    }

    @NonNull
    @Override
    public MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each meal item
        View view = LayoutInflater.from(context).inflate(R.layout.single_meal_general, parent, false);
        return new MealViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealViewHolder holder, int position) {
        MealPojo meal = mealList.get(position);

        // Set meal name
        holder.mealNameTextView.setText(meal.getStrMeal());

        // Load meal image
        Glide.with(context)
                .load(meal.getStrMealThumb())
                .into(holder.mealImageView);


    }

    @Override
    public int getItemCount() {
        return mealList.size();
    }

    // ViewHolder class to bind UI elements
    static class MealViewHolder extends RecyclerView.ViewHolder {
        TextView mealNameTextView, mealInstructionsTextView, ingredientsTextView, areaTextView, categoryTextView;
        ImageView mealImageView;
        WebView mealVideoWebView; // WebView for showing YouTube video

        public MealViewHolder(@NonNull View itemView) {
            super(itemView);
            // Bind views
            mealNameTextView = itemView.findViewById(R.id.mealNameTextView);
            mealImageView = itemView.findViewById(R.id.mealImageView);
            mealInstructionsTextView = itemView.findViewById(R.id.mealInstructionsTextView);

        }
    }

    // Helper method to format ingredients from MealPojo
    /*private String formatIngredients(MealPojo meal) {
        StringBuilder ingredients = new StringBuilder();

        if (meal.getStrIngredient1() != null && !meal.getStrIngredient1().isEmpty()) {
            ingredients.append("1. ").append(meal.getStrIngredient1()).append("\n");
        }
        if (meal.getStrIngredient2() != null && !meal.getStrIngredient2().isEmpty()) {
            ingredients.append("2. ").append(meal.getStrIngredient2()).append("\n");
        }
        if (meal.getStrIngredient3() != null && !meal.getStrIngredient3().isEmpty()) {
            ingredients.append("3. ").append(meal.getStrIngredient3()).append("\n");
        }
        // Add more ingredients as per MealPojo fields

        return ingredients.toString().trim();
    }*/
}
