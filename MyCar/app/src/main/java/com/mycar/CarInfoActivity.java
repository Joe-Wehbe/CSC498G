package com.mycar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;

import java.util.Objects;

public class CarInfoActivity extends AppCompatActivity {

    private int progress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_info);

        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.black));

        Intent intent = getIntent();
        String brand = intent.getStringExtra("carBrand");

        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.background)));
        getSupportActionBar().setTitle(brand);
    }

    public void goToAddDistance(View v){
        Intent intent = new Intent(CarInfoActivity.this, AddDistanceActivity.class);
        startActivity(intent);
    }

    public void goToMoreInfo(View v){
        Intent intent = new Intent(CarInfoActivity.this, MoreInfoActivity.class);
        startActivity(intent);
    }
}