package com.mycar;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Objects;

public class CarsActivity extends AppCompatActivity {

    //private final String URL = "http://192.168.1.110/MyCar/getCars.php";

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

        arrayList.add(new Car("Jeep", "2020"));
        arrayList.add(new Car("Nissan", "2016"));
        arrayList.add(new Car("Kia", "2018"));

        CarAdapter = new CarAdapter(this, R.layout.custom_list_view, arrayList);
        listView.setAdapter((ListAdapter) CarAdapter);
    }

}