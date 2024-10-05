package com.example.foodplanner.MealDetails.View;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.foodplanner.R;

public class MealCalendarFragment extends DialogFragment {

    private OnDateSelectedListener listener;

    // Interface for communicating with the parent fragment
    public interface OnDateSelectedListener {
        void onDateSelected(String date);
    }

    // This method allows the parent fragment to set the listener
    public void setOnDateSelectedListener(OnDateSelectedListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.meal_calendar_fragment, null);

        // Get references to the views
        DatePicker datePicker = view.findViewById(R.id.datePickerID);
        Button btnSelectDate = view.findViewById(R.id.btnSelectDateID);

        // Create dialog
        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(view);

        // Handle button click to select date
        btnSelectDate.setOnClickListener(v -> {
            int day = datePicker.getDayOfMonth();
            int month = datePicker.getMonth() + 1; // Month is 0-indexed
            int year = datePicker.getYear();

            String selectedDate = day + "/" + month + "/" + year;

            // Call the listener to notify the parent fragment
            if (listener != null) {
                listener.onDateSelected(selectedDate);
            }

            // Dismiss dialog
            dialog.dismiss();
            Toast.makeText(getContext(), "The Meal Scheduled at " + selectedDate, Toast.LENGTH_SHORT).show();

        });

        return dialog;

    }
}
