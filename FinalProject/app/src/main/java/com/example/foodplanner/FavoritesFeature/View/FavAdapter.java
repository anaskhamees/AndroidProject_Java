package com.example.foodplanner.FavoritesFeature.View;

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
import com.example.foodplanner.Home.Countries.View.CountriesAdapter;
import com.example.foodplanner.Model.MealPojo;
import com.example.foodplanner.R;

import java.util.List;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.ViewHolder> {

    private Context context;
    private List<MealPojo> meals;
    private FavOnMealClick favOnMealClick;
    public FavAdapter(Context context, List<MealPojo> meals,FavOnMealClick favOnMealClick) {
        this.context = context;
        this.meals = meals;
        this.favOnMealClick=favOnMealClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_cell_contents, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MealPojo meal = meals.get(position);


        holder.mealName.setText(meal.getStrMeal());
        Glide.with(context)
                .load(meal.getStrMealThumb()) // Load image from URL
                .placeholder(R.drawable.loading) // Placeholder while loading
                .into(holder.mealImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                favOnMealClick.onMealClick(meal);
            }
        });
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mealName;
        public ImageView mealImage;
        public CardView cardView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mealName = itemView.findViewById(R.id.category_name);
            mealImage = itemView.findViewById(R.id.categoryImageID);
            cardView = itemView.findViewById(R.id.card_viewID);
        }
    }
}
