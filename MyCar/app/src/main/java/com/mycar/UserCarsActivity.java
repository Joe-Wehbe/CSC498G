package com.mycar;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Objects;

public class UserCarsActivity extends AppCompatActivity {

    private static String baseURL = "http://192.168.1.104/MyCar/";

    ListView listView;
    private Object CarAdapter;

    String brand;
    String plate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_cars);

        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.dark_gray)));
        getSupportActionBar().setTitle("Your Cars");

        listView = findViewById(R.id.listView);

        getUserCars();
    }
    
    private void getUserCars(){

        Intent intent = getIntent();
        String id = intent.getStringExtra("user_id");

        String URL = String.format(baseURL + "getCars.php?id=%1$s", id);

        ArrayList<Car> arrayList = new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try{
                        JSONArray array = new JSONArray(response);
                        for(int i = 0; i < array.length(); i++){
                            JSONObject object = array.getJSONObject(i);

                            brand = object.getString("brand");
                            plate = object.getString("plate") ;

                            arrayList.add(new Car(brand, plate));
                        }

                    }catch (Exception e){

                    }
                    CarAdapter = new CarAdapter(UserCarsActivity.this, R.layout.custom_list_view, arrayList);
                    listView.setAdapter((ListAdapter) CarAdapter);

                    listView.setOnItemClickListener((adapterView, view, i, l) -> {
                        Intent intent1 = new Intent(getApplicationContext(), CarInfoActivity.class);
                        intent1.putExtra("carBrand", brand);
                        startActivity(intent1);
                    });
                }
            }, error -> Toast.makeText(UserCarsActivity.this, error.toString(), Toast.LENGTH_SHORT).show());

            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);

    }

}