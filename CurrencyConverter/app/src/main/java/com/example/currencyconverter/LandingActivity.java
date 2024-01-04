package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class LandingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.blue)));

    }

    public void lbpInput(View v){
        EditText extractingAmount = findViewById(R.id.lbpAmount);
        String amount = extractingAmount.getText().toString();
        double lbpAmount = Double.parseDouble(amount);
        double usdAmount = lbpAmount/40000;
        amount = Double.toString(usdAmount);
        Toast.makeText(getApplicationContext(), amount + " USD", Toast.LENGTH_LONG).show();
    }

    public void usdInput(View v){
        EditText extractingAmount = findViewById(R.id.usdAmount);
        String amount = extractingAmount.getText().toString();
        double usdAmount = Double.parseDouble(amount);
        double lbpAmount = usdAmount*40000;
        amount = Double.toString(lbpAmount);
        Toast.makeText(getApplicationContext(), amount + " LBP", Toast.LENGTH_LONG).show();
    }

    public void logout(View v){
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }

}