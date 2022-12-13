package com.mycar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CarsActivity extends AppCompatActivity {

    private static String baseURL = "http://192.168.1.104/MyCar/";

    ListView listView;
    private Object CarAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars);

        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.dark_gray)));
        getSupportActionBar().setTitle("Your Cars");

        listView = findViewById(R.id.listView);

        getUserCars();
    }
    
    private void getUserCars(){

        Intent intent = getIntent();
        String id = intent.getStringExtra("user_id");

        Toast.makeText(this, id, Toast.LENGTH_SHORT).show();

        String URL = String.format(baseURL + "getCars.php?param1=%1$s", id);

        ArrayList<Car> arrayList = new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(CarsActivity.this, response, Toast.LENGTH_SHORT).show();

                    try{
                        JSONArray array = new JSONArray(response);
                        for(int i = 0; i < array.length(); i++){
                            JSONObject object = array.getJSONObject(i);

                            String brand = object.getString("brand");
                            String plate = object.getString("plate") ;

                            arrayList.add(new Car(brand, plate));
                        }

                    }catch (Exception e){

                    }
                    CarAdapter = new CarAdapter(CarsActivity.this, R.layout.custom_list_view, arrayList);
                    listView.setAdapter((ListAdapter) CarAdapter);
                }
            }, error -> Toast.makeText(CarsActivity.this, error.toString(), Toast.LENGTH_SHORT).show());

            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);

    }

}