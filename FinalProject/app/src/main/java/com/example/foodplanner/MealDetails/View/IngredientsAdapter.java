package com.example.foodplanner.MealDetails.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;

import java.util.ArrayList;
import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.ViewHolder> {
    private List<String> ingredients;
    private List<String> measures;

    public IngredientsAdapter() {
        this.ingredients = new ArrayList<>();
        this.measures = new ArrayList<>();
    }

    public void setList(List<String> ingredients, List<String> measures) {
        this.ingredients.clear(); // Clear existing items
        this.measures.clear(); // Clear existing measures
        this.ingredients.addAll(ingredients);
        this.measures.addAll(measures);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView ingredientName;
        TextView ingredientMeasureName;
        ImageView ingredientImg;

        public ViewHolder(View view) {
            super(view);
            ingredientName = view.findViewById(R.id.ingredientTextView);
            ingredientMeasureName = view.findViewById(R.id.measuresTextView);
            ingredientImg = view.findViewById(R.id.ingredientImageView);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient_cell_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String ingredient = ingredients.get(position);
        String measure = measures.get(position);
        holder.ingredientName.setText(ingredient);
        holder.ingredientMeasureName.setText(measure);

        // Load the image using Glide
        Glide.with(holder.itemView.getContext())
                .load("https://www.themealdb.com/images/ingredients/" + ingredient + ".png")
                .into(holder.ingredientImg);
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }
}
