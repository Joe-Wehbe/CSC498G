package com.mycar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class SplashScreenActivity extends AppCompatActivity {

    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.black));

        getSupportActionBar().hide();

        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashScreenActivity.this, SignInActivity.class));
            finish();
        }, 5000);

        img = findViewById(R.id.logo);

        TranslateAnimation animation = new TranslateAnimation(-1000, 1000, 0, 0);
        animation.setDuration(2000);
        animation.setRepeatCount(5);
        animation.setRepeatMode(1);
        img.startAnimation(animation);
    }
}