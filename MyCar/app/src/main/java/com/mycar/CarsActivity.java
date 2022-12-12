package com.mycar;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.Objects;

public class CarsActivity extends AppCompatActivity {

    private static final String URL = "http://192.168.1.110/MyCar/getCars.php";

    ListView listView;
    private Object CarAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars);

        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.dark_gray)));
        getSupportActionBar().setTitle("Your Cars");

        listView = findViewById(R.id.listView);

        



        ArrayList<Car> arrayList = new ArrayList<>();

        arrayList.add(new Car("Jeep", "2432355"));
        arrayList.add(new Car("Nissan", "2233401"));
        arrayList.add(new Car("Kia", "2012434"));

        CarAdapter = new CarAdapter(this, R.layout.custom_list_view, arrayList);
        listView.setAdapter((ListAdapter) CarAdapter);

    }
    
    private void getCars(){
        
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CarsActivity.this, "", Toast.LENGTH_SHORT).show();
            }
        });
    }

}