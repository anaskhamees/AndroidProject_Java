package com.example.foodplanner.CalendarFeature.Presenter;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.Model.MealPojoPlan;

import java.util.List;

public interface CalendarPresenterInterface {

    public LiveData<List<MealPojoPlan>> getPlannedFood(String date);
    public void removeMealFromCalendar(MealPojoPlan plannedMeal);

}
