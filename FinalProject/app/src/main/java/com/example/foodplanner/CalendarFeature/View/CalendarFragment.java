package com.example.foodplanner.CalendarFeature.View;

import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.CalendarFeature.Presenter.CalendarPresenter;
import com.example.foodplanner.DataBase.MealLocalDataSource;
import com.example.foodplanner.Model.MealPojoPlan;
import com.example.foodplanner.NetworkPkg.MealRemoteDataSource;
import com.example.foodplanner.R;
import com.example.foodplanner.Repository.MealRepository;

import java.util.ArrayList;
import java.util.List;

public class CalendarFragment extends Fragment implements CalendarViewInterface,OnPlannedClickListener {

    private CalendarPresenter calendarPresenter;
    private RecyclerView plannedMealRecycleView;
    private CalendarAdapter calendarAdapter;
    LinearLayoutManager layoutManager;

    LiveData<List<MealPojoPlan>> plannedMeals;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.calendar_fragment, container, false);
        DatePicker datePicker=view.findViewById(R.id.datePickerID);
        Calendar calendar=Calendar.getInstance();
        plannedMealRecycleView=view.findViewById(R.id.PlannedRecycleViewID);
        calendarPresenter=new CalendarPresenter(this, MealRepository.getInstance(MealRemoteDataSource.getMealRemoteDataSourceInstance(), MealLocalDataSource.getInstance(getContext())));

        calendarAdapter=new CalendarAdapter(getContext(),new ArrayList<MealPojoPlan>(),this);
        plannedMealRecycleView.setAdapter(calendarAdapter);

        layoutManager=new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        plannedMealRecycleView.setLayoutManager(layoutManager);

        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Format the selected date
                        String selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;

                        plannedMeals=calendarPresenter.getPlannedFood(selectedDate);
                        plannedMeals.observe(getViewLifecycleOwner(), new Observer<List<MealPojoPlan>>() {
                            @Override
                            public void onChanged(List<MealPojoPlan> foodPlans) {
                                // Update the adapter with new data
                                if(foodPlans.isEmpty() ||foodPlans==null)
                                {
                                    Toast.makeText(getContext(), "No Meals Scheduled in this date", Toast.LENGTH_SHORT).show();
                                    foodPlans.clear();
                                    calendarAdapter.setList(foodPlans);
                                    calendarAdapter.notifyDataSetChanged();

                                }
                                else
                                {
                                    calendarAdapter.setList(foodPlans);
                                    calendarAdapter.notifyDataSetChanged();                                }
                            }
                        });

                        // Send the selected date to another fragment or handle it
                        Bundle result = new Bundle();
                        result.putString("selectedDate", selectedDate);
                        getParentFragmentManager().setFragmentResult("DATE", result);

                        // Show selected date in a Toast (for testing purposes)
                        //Toast.makeText(getContext(), "Selected Date: " + selectedDate, Toast.LENGTH_SHORT).show();
                    }
                });

        return view;
    }

    @Override
    public void displayPlannedMeals(List<MealPojoPlan> mealList) {
        calendarAdapter.setList(mealList);
        calendarAdapter.notifyDataSetChanged();

    }

    @Override
    public void displayError(String errorMessage) {

    }

    @Override
    public void onPlannedMealClick(MealPojoPlan meal) {

    }
}
