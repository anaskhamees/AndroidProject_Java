package com.example.foodplanner.SearchMealFeature;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.foodplanner.R;

public class SearchMealFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.search_fragment, container, false);

        // Set onClickListener for the Cards
        view.findViewById(R.id.categoriesImage).setOnClickListener(v -> showToast("Categories"));
        view.findViewById(R.id.countriesImage).setOnClickListener(v -> showToast("Countries"));
        view.findViewById(R.id.ingredientsImage).setOnClickListener(v -> showToast("Ingredients"));

        return view;
    }

    private void showToast(String message) {
        Toast.makeText(getActivity(), message + " clicked", Toast.LENGTH_SHORT).show();
    }

}
