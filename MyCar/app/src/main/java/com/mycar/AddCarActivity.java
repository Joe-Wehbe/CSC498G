package com.mycar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AddCarActivity extends AppCompatActivity {

    private EditText etBrand, etModel, etColor, etPlate;
    private String brand, model, color, plate;

    private String URL = "http://192.168.1.103/MyCar/addCar.php";
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.black));

        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.dark_gray)));
        getSupportActionBar().setTitle("Add Car");

        etBrand = findViewById(R.id.et_brand);
        etModel = findViewById(R.id.et_model);
        etColor = findViewById(R.id.et_color);
        etPlate = findViewById(R.id.et_plate);

        Intent intent = getIntent();
        id = intent.getStringExtra("user_id");
    }

    public void addCar(View v){

        brand = etBrand.getText().toString().trim();
        model = etModel.getText().toString().trim();
        color = etColor.getText().toString().trim();
        plate = etPlate.getText().toString().trim();

        if(!brand.equals("") && !model.equals("") && !color.equals("") && !plate.equals("")){

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    if (response.trim().equals("success")) {
                        Intent intent = new Intent(AddCarActivity.this, UserCarsActivity.class);
                        intent.putExtra("user_id", id);
                        startActivity(intent);

                    } else if(response.trim().equals("exists")){
                        Toast.makeText(AddCarActivity.this, "This car is already registered", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(AddCarActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();

                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> data = new HashMap<>();
                    data.put("brand", brand);
                    data.put("model", model);
                    data.put("color", color);
                    data.put("plate", plate);
                    data.put("user_id", id);
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);

        }else{
            Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
        }

    }
}