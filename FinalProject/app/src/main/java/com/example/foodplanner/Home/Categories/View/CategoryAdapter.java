package com.example.foodplanner.Home.Categories.View;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.Home.Categories.Model.CategoryPojo;
import com.example.foodplanner.R;
import com.example.foodplanner.Home.Categories.CategoriesMeals.View.CategoriesMealsFragment;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private Context context;
    private List<CategoryPojo> categoryList;

    // Constructor for the adapter, passing context and the list of categories
    public CategoryAdapter(Context context, List<CategoryPojo> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    // Inflates the single cell layout
    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_cell_contents, parent, false);
        return new CategoryViewHolder(view);
    }

    // Binds data to each ViewHolder
    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        // Get the current category
        CategoryPojo category = categoryList.get(position);

        // Bind data to the ViewHolder views
        holder.categoryName.setText(category.getStrCategory());

        // Load image using Glide library
        Glide.with(context)
                .load(category.getStrCategoryThumb()) // Load image from URL
                .placeholder(R.drawable.loading) // Placeholder while loading
                .into(holder.categoryImage);

        // Handle click event to pass category name and replace fragment
        holder.itemView.setOnClickListener(v -> {
            // Create a new instance of CategoriesMealsFragment
            CategoriesMealsFragment fragment = new CategoriesMealsFragment();

            // Create a bundle to pass the selected category to the new fragment
            Bundle bundle = new Bundle();
            bundle.putString("CATEGORY_NAME", category.getStrCategory()); // Pass the selected category name
            fragment.setArguments(bundle);

            // Replace the current fragment with the new one using FragmentManager
            ((FragmentActivity) context).getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainerID, fragment) // Adjust to your fragment container ID
                    .addToBackStack(null)  // Add the transaction to the back stack for navigation
                    .commit();
        });
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    // ViewHolder class that holds references to the UI components of the single cell layout
    public static class CategoryViewHolder extends RecyclerView.ViewHolder {

        public TextView categoryName;
        public ImageView categoryImage;
        public CardView cardView;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            // Initialize views from the single cell layout
            categoryName = itemView.findViewById(R.id.category_name);
            categoryImage = itemView.findViewById(R.id.categoryImageID);
            cardView = itemView.findViewById(R.id.card_viewID);
        }
    }
}
