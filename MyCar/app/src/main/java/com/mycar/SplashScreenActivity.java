package com.mycar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.black));

        // Hiding the action bar
        getSupportActionBar().hide();

        // Loading time
        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashScreenActivity.this, SignInActivity.class));
            finish();
        }, 5000);

        // Animating the logo in the splash screen
        ImageView iv = findViewById(R.id.logo);
        ObjectAnimator scaleDown = ObjectAnimator.ofPropertyValuesHolder(
                iv,
                PropertyValuesHolder.ofFloat("scaleX", 1.2f),
                PropertyValuesHolder.ofFloat("scaleY", 1.2f));
        scaleDown.setDuration(1000);
        scaleDown.setRepeatCount(ObjectAnimator.INFINITE);
        scaleDown.setRepeatMode(ObjectAnimator.REVERSE);
        scaleDown.start();
    }
}