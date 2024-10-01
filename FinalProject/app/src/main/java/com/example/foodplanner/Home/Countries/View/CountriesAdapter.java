package com.example.foodplanner.Home.Countries.View;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.Home.Categories.CategoriesMeals.View.CategoriesMealsFragment;
import com.example.foodplanner.Home.Countries.CountriesMeals.View.CountriesMealsFragment;
import com.example.foodplanner.Model.MealPojo;
import com.example.foodplanner.R;

import java.util.List;

public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.CountryViewHolder> {
    private List<MealPojo> countries;
    private Context context;

    // Update constructor to accept a context
    public CountriesAdapter(Context context, List<MealPojo> countries) {
        this.context = context; // Set context here
        this.countries = countries;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.countries_cell_contents, parent, false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        if (countries != null && !countries.isEmpty()) {
            MealPojo country = countries.get(position);
            if (country != null) {
                holder.countryName.setText(country.getStrArea() != null ? country.getStrArea() : "Unknown Area");
                String flagUrl = country.getFlagUrl();
                if (flagUrl != null) {
                    Glide.with(holder.itemView.getContext())
                            .load(flagUrl)
                            .apply(RequestOptions.circleCropTransform())
                            .into(holder.countryImage);

                    holder.itemView.setOnClickListener(v -> {
                        // Create a new instance of CategoriesMealsFragment
                        CountriesMealsFragment fragment = new CountriesMealsFragment();

                        // Create a bundle to pass the selected category to the new fragment
                        Bundle bundle = new Bundle();
                        bundle.putString("COUNTRY_NAME", country.getStrArea()); // Pass the selected Area name
                        fragment.setArguments(bundle);

                        // Replace the current fragment with the new one using FragmentManager
                        ((FragmentActivity) context).getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragmentContainerID, fragment) // Adjust to your fragment container ID
                                .addToBackStack(null)  // Add the transaction to the back stack for navigation
                                .commit();
                    });
                } else {
                    holder.countryImage.setImageResource(R.drawable.loading); // Use a placeholder image
                }
            } else {
                Log.e("CountryAdapter", "Country object is null at position: " + position);
            }
        } else {
            Log.e("CountryAdapter", "Country list is null or empty");
        }
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    static class CountryViewHolder extends RecyclerView.ViewHolder {
        TextView countryName;
        ImageView countryImage;

        CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            countryName = itemView.findViewById(R.id.countryNameTextView);
            countryImage = itemView.findViewById(R.id.countryFlagImageView);
        }
    }
}
