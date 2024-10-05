package com.example.foodplanner.CalendarFeature.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.FavoritesFeature.View.FavAdapter;
import com.example.foodplanner.Model.MealPojo;
import com.example.foodplanner.Model.MealPojoPlan;
import com.example.foodplanner.R;

import java.util.List;
//
public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.ViewHolder> {
    private Context context;
    private List<MealPojoPlan> plannedMeals;
private OnPlannedClickListener plannedOnClickListener;

    public CalendarAdapter(Context context, List<MealPojoPlan> plannedMeals, OnPlannedClickListener listener) {
        this.context = context;
        this.plannedMeals = plannedMeals;
        this.plannedOnClickListener = listener;
    }

    public void setList(List<MealPojoPlan> UpdatedPlannedMeals)
    {
        if(UpdatedPlannedMeals!=null || !(UpdatedPlannedMeals.isEmpty()))
        {
            this.plannedMeals=UpdatedPlannedMeals;
        }
        else {
            Toast.makeText(context, "No Meals Scheduled in this date", Toast.LENGTH_SHORT).show();
        }
    }
    @NonNull
    @Override
    public CalendarAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.meal_name_img_cell, parent, false);
        return new CalendarAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarAdapter.ViewHolder holder, int position) {
        MealPojoPlan plannedMeal = plannedMeals.get(position);


        holder.mealName.setText(plannedMeal.getStrMeal());
        Glide.with(context)
                .load(plannedMeal.getStrMealThumb()) // Load image from URL
                .placeholder(R.drawable.loading) // Placeholder while loading
                .into(holder.mealImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                plannedOnClickListener.onPlannedMealClick(plannedMeal);
            }
        });
    }
    public int getItemCount() {
        return plannedMeals.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mealName;
        public ImageView mealImage;
        public CardView cardView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mealName = itemView.findViewById(R.id.mealNameTextView);
            mealImage = itemView.findViewById(R.id.mealImageView);
            cardView = itemView.findViewById(R.id.CardViewID);
        }
    }

}
