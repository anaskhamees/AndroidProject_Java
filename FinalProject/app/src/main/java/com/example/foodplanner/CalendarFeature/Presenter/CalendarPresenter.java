package com.example.foodplanner.CalendarFeature.Presenter;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.CalendarFeature.View.CalendarViewInterface;
import com.example.foodplanner.Model.MealPojoPlan;
import com.example.foodplanner.Repository.MealRepository;

import java.util.List;

public class CalendarPresenter implements CalendarPresenterInterface{

    private CalendarViewInterface calendarIview;
    private MealRepository plannedMealRepo;

    public CalendarPresenter(CalendarViewInterface calendarIview, MealRepository plannedMealRepo) {
        this.calendarIview = calendarIview;
        this.plannedMealRepo = plannedMealRepo;
    }

    @Override
    public LiveData<List<MealPojoPlan>> getPlannedFood(String date) {
        return plannedMealRepo.getPlannedMeals(date);
    }

    @Override
    public void removeMealFromCalendar(MealPojoPlan plannedMeal) {
        plannedMealRepo.deletePlannedMeal(plannedMeal);
    }
}
