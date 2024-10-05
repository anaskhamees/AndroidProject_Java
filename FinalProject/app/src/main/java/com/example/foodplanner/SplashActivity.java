package com.example.foodplanner;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

public class SplashActivity extends AppCompatActivity {

    private LottieAnimationView lottieAnimationView;
    private TextView authorNameTextView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash); // Ensure activity_splash is the layout you updated

        // Initialize the LottieAnimationView and Author Name TextView
        lottieAnimationView = findViewById(R.id.lottieAnimationView);
        authorNameTextView = findViewById(R.id.myName);

        // Set the initial visibility of the author name to invisible
        authorNameTextView.setVisibility(View.INVISIBLE);  // Initially invisible

        // Start the Lottie animation
        lottieAnimationView.setVisibility(View.VISIBLE);
        lottieAnimationView.playAnimation();

        // Animate the author name to appear after 1 second (1000 ms)
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                fadeInTextView(authorNameTextView);  // Fade in the author name
            }
        }, 1000); // Delay in milliseconds

        // Delay for the splash screen (10 seconds)
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start MainActivity and finish this activity
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Finish this activity
            }
        }, 10000); // 10000 milliseconds = 10 seconds
    }

    // Method to animate the fade-in effect for the TextView
    private void fadeInTextView(View view) {
        view.setVisibility(View.VISIBLE);  // Make the TextView visible
        AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f);  // From transparent to fully visible
        fadeIn.setDuration(2000);  // Duration of the fade-in (2 seconds)
        view.startAnimation(fadeIn);  // Start the fade-in animation
    }
}
