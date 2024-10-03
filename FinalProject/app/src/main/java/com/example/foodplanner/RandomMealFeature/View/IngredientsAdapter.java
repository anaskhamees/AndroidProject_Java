package com.example.foodplanner.RandomMealFeature.View;

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
    List<String> ingredients;
    List<String>measures;

    public IngredientsAdapter(List<String> ingredients/*, List<String> measures*/) {
        this.ingredients = new ArrayList<>();
        this.measures = new ArrayList<>();
    }
    public void setList(List<String> ingredients,List<String>measures)
    {
        this.ingredients.addAll(ingredients);
        this.measures.addAll(measures);
        notifyDataSetChanged();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView ingredientName;
        private TextView ingredientMeasureName;
        private ImageView ingredientImg;

        public ViewHolder(View view)
        {
            super(view);
            ingredientName=view.findViewById(R.id.ingredientTextView);
            ingredientMeasureName=view.findViewById(R.id.measuresTextView);
            ingredientImg=view.findViewById(R.id.ingredientImageView);
        }
    }

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient_cell_content,parent,false);
        return new IngredientsAdapter.ViewHolder(view);
    }
    public void onBindViewHolder(@NonNull IngredientsAdapter.ViewHolder holder, int position)
    {
        String ingredient=ingredients.get(position);
        String measure=measures.get(position);
        holder.ingredientName.setText(ingredient);
        holder.ingredientMeasureName.setText(measure);
        Glide.with(holder.itemView.getContext()).load("https://www.themealdb.com/images/ingredients/" + ingredient + ".png").into(holder.ingredientImg);
    }
    public int getItemCount(){
        return ingredients.size();
    }

}
