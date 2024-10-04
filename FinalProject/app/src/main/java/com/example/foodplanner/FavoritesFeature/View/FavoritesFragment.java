package com.example.foodplanner.FavoritesFeature.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.DataBase.MealLocalDataSource;
import com.example.foodplanner.FavoritesFeature.Presenter.FavPresenter;
import com.example.foodplanner.Model.MealPojo;
import com.example.foodplanner.NetworkPkg.MealRemoteDataSource;
import com.example.foodplanner.R;
import com.example.foodplanner.Repository.MealRepository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment implements FavViewInterface,FavOnMealClick{

   private FavAdapter favAdapter;
   private FavPresenter favPresenter;
   private RecyclerView favRecycleView;
   private List<MealPojo> mealList=new ArrayList<>();

    public FavoritesFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize the presenter with the repository and the view (this fragment)
        favPresenter = new FavPresenter(this, MealRepository.getInstance(MealRemoteDataSource.getMealRemoteDataSourceInstance(), MealLocalDataSource.getInstance(getContext())));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.categories_recycle_view, container, false);

        // Initialize RecyclerView and set up the adapter
        favRecycleView = view.findViewById(R.id.categoriesRecycleViewID);
        favAdapter = new FavAdapter(getContext(), mealList, this);
        favRecycleView.setAdapter(favAdapter);
        favRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Fetch and display favorite meals
        favPresenter.getFavMeals(getViewLifecycleOwner());
         return view;
    }

    @Override
    public void onMealClick(MealPojo meal) {
        FavMealDetailsFragment fragment=new FavMealDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("meal", (Serializable) meal);
        fragment.setArguments(bundle);
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainerID, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }

    @Override
    public void displayFavMeals(List<MealPojo> meals) {
        mealList.clear();
        mealList.addAll(meals);
        favAdapter.notifyDataSetChanged();
        if(meals==null||meals.isEmpty())
        {
            Toast.makeText(getContext(), "No favorite meals found", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void displayError(String errMsg) {

        android.widget.Toast.makeText(getContext(), errMsg, android.widget.Toast.LENGTH_SHORT).show();

    }
}
