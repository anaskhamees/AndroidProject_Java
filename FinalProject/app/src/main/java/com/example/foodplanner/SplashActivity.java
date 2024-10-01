package com.example.foodplanner;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

public class SplashActivity extends AppCompatActivity {

    private LottieAnimationView lottieAnimationView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash); // Make sure to create the corresponding layout file

        // Initialize the LottieAnimationView
        lottieAnimationView = findViewById(R.id.lottieAnimationView);

        // Start the Lottie animation
        lottieAnimationView.setVisibility(View.VISIBLE);
        lottieAnimationView.playAnimation(); // Play the Lottie animation

        // Delay for the splash screen
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // After delay, start MainActivity and finish this activity
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Finish this activity so it can't be returned to
            }
        }, 10000); // 10000 milliseconds = 10 seconds
    }
}
