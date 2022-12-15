package com.mycar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CarInfoActivity extends AppCompatActivity {

    private ProgressBar pbEo;
    private ProgressBar pbEc;
    private ProgressBar pbTf;
    private ProgressBar pbPsf;
    private ProgressBar pbBf;

    private TextView tvEo;
    private TextView tvEc;
    private TextView tvTf;
    private TextView tvPsf;
    private TextView tvBf;

    private TextView tvBrand;
    private TextView tvColor;
    private TextView tvModel;
    private TextView tvPlate;

    private static String baseURL = "http://192.168.1.101/MyCar/";
    private String id;
    private String car_id;
    private String brand;

    AlertDialog.Builder builder;
    AlertDialog.Builder builder1;
    AlertDialog dialog;

    EditText distance;
    Button button;
    int distanceDriven;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_info);

        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.black));

        Intent intent = getIntent();
        brand = intent.getStringExtra("carBrand");
        id = intent.getStringExtra("user_id");
        car_id = intent.getStringExtra("car_id");

        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.background)));
        getSupportActionBar().setTitle(brand);

        pbEo = findViewById(R.id.eo);
        pbEc = findViewById(R.id.ec);
        pbTf = findViewById(R.id.tf);
        pbPsf = findViewById(R.id.psf);
        pbBf = findViewById(R.id.bf);

        tvEo = findViewById(R.id.eo_progress);
        tvEc = findViewById(R.id.ec_progress);
        tvTf = findViewById(R.id.tf_progress);
        tvPsf = findViewById(R.id.psf_progress);
        tvBf = findViewById(R.id.bf_progress);

        tvBrand = findViewById(R.id.tv_brand);
        tvModel = findViewById(R.id.tv_model);
        tvColor = findViewById(R.id.tv_color);
        tvPlate = findViewById(R.id.tv_plate);

        builder = new AlertDialog.Builder(this);

        getCarInfo();
        getCarFluids();
    }

    public void getCarInfo(){
        String URL = String.format(baseURL + "getCarInfo.php?id=%1$s&car_id=%2$s", id, car_id);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, response -> {
            try{
                JSONArray array = new JSONArray(response);
                for(int i = 0; i < array.length(); i++){
                    JSONObject object = array.getJSONObject(i);

                    tvBrand.setText(object.getString("brand").trim());
                    tvModel.setText(object.getString("model").trim());
                    tvColor.setText(object.getString("color").trim());
                    tvPlate.setText(object.getString("plate").trim());
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }, error -> Toast.makeText(CarInfoActivity.this, error.toString(), Toast.LENGTH_SHORT).show());

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    public void getCarFluids(){
        String URL = String.format(baseURL + "getCarFluids.php?id=%1$s&car_id=%2$s", id, car_id);
        @SuppressLint("SetTextI18n") StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, response -> {
            try{
                JSONArray array = new JSONArray(response);
                for(int i = 0; i < array.length(); i++){
                    JSONObject object = array.getJSONObject(i);

                    pbEo.setProgress(Integer.parseInt(object.getString("engine_oil").trim()));
                    pbEc.setProgress(Integer.parseInt(object.getString("engine_coolant").trim()));
                    pbTf.setProgress(Integer.parseInt(object.getString("transmission_fluid").trim()));
                    pbPsf.setProgress(Integer.parseInt(object.getString("power_steering_fluid").trim()));
                    pbBf.setProgress(Integer.parseInt(object.getString("breaks_fluid").trim()));

                    tvEo.setText(object.getString("engine_oil").trim() + "%");
                    tvEc.setText(object.getString("engine_coolant").trim() + "%");
                    tvTf.setText(object.getString("transmission_fluid").trim() + "%");
                    tvPsf.setText(object.getString("power_steering_fluid").trim() + "%");
                    tvBf.setText(object.getString("breaks_fluid").trim() + "%");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }, error -> Toast.makeText(CarInfoActivity.this, error.toString(), Toast.LENGTH_SHORT).show());

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    public void refill(String fluidDB, String fluid){
        String URL = baseURL + "refill.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, response -> {

            if(response.trim().equals("success")){
                Toast.makeText(CarInfoActivity.this, fluid + " refilled", Toast.LENGTH_SHORT).show();
            }
            if(response.trim().equals("failure")){
                Toast.makeText(CarInfoActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }, error -> Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show()){
            @Nullable
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> data = new HashMap<>();
                data.put("column", fluidDB);
                data.put("user_id", id);
                data.put("car_id", car_id);
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    @SuppressLint("SetTextI18n")
    public void refillEo(View view) {
        if (pbEo.getProgress() < 100){
            builder.setTitle("Refill Engine Oil")
                    .setMessage("Are you sure you want to refill the engine oil?")
                    .setCancelable(true)
                    .setPositiveButton("Yes", (dialogInterface, i) -> {
                        pbEo.setProgress(100);
                        tvEo.setText(pbEo.getProgress() + "%");
                        refill("engine_oil", "Engine oil");
                    })
                    .setNegativeButton("No", (dialogInterface, i12) -> dialogInterface.cancel()).show();
        }
    }

    @SuppressLint("SetTextI18n")
    public void refillEc(View view) {
        if (pbEc.getProgress() < 100){
            builder.setTitle("Refill Engine Coolant")
                    .setMessage("Are you sure you want to refill the engine coolant fluid?")
                    .setCancelable(true)
                    .setPositiveButton("Yes", (dialogInterface, i) -> {
                        pbEc.setProgress(100);
                        tvEc.setText(pbEc.getProgress() + "%");
                        refill("engine_coolant", "Engine coolant");
                    })
                    .setNegativeButton("No", (dialogInterface, i12) -> dialogInterface.cancel()).show();
        }
    }

    @SuppressLint("SetTextI18n")
    public void refillTf(View view) {
        if (pbTf.getProgress() < 100){
            builder.setTitle("Refill Transmission Fluid")
                    .setMessage("Are you sure you want to refill the transmission fluid?")
                    .setCancelable(true)
                    .setPositiveButton("Yes", (dialogInterface, i) -> {
                        pbTf.setProgress(100);
                        tvTf.setText(pbTf.getProgress() + "%");
                        refill("transmission_fluid", "Transmission fluid");
                    })
                    .setNegativeButton("No", (dialogInterface, i12) -> dialogInterface.cancel()).show();

        }
    }

    @SuppressLint("SetTextI18n")
    public void refillPsf(View view) {
        if (pbPsf.getProgress() < 100){
            builder.setTitle("Refill Power Steering Fluid")
                    .setMessage("Are you sure you want to refill the power steering fluid?")
                    .setCancelable(true)
                    .setPositiveButton("Yes", (dialogInterface, i) -> {
                        pbPsf.setProgress(100);
                        tvPsf.setText(pbPsf.getProgress() + "%");
                        refill("power_steering_fluid", "Power steering fluid");
                    })
                    .setNegativeButton("No", (dialogInterface, i12) -> dialogInterface.cancel()).show();

        }
    }

    @SuppressLint("SetTextI18n")
    public void refillBf(View view) {
        if(pbBf.getProgress() < 100){
            builder.setTitle("Refill Breaks Fluid")
                    .setMessage("Are you sure you want to refill the breaks fluid?")
                    .setCancelable(true)
                    .setPositiveButton("Yes", (dialogInterface, i) -> {
                        pbBf.setProgress(100);
                        tvBf.setText(pbBf.getProgress() + "%");
                        refill("breaks_fluid", "Breaks fluid");
                    })
                    .setNegativeButton("No", (dialogInterface, i12) -> dialogInterface.cancel()).show();
        }
    }

    public void updateFluids(String eo, String ec, String tf, String psf, String bf){
        String URL = baseURL + "updateFluids.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, response -> {

            if(response.trim().equals("success")){
                Toast.makeText(CarInfoActivity.this, "Fluids updated", Toast.LENGTH_SHORT).show();
            }
            if(response.trim().equals("failure")){
                Toast.makeText(CarInfoActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }, error -> Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show()){
            @Nullable
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> data = new HashMap<>();
                data.put("eo", eo);
                data.put("ec", ec);
                data.put("tf", tf);
                data.put("psf", psf);
                data.put("bf", bf);
                data.put("user_id", id);
                data.put("car_id", car_id);
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    @SuppressLint("SetTextI18n")
    public void calculateAmountRemaining(){

        pbEo.setProgress(pbEo.getProgress() - 100 * distanceDriven/7500);
        tvEo.setText(pbEo.getProgress() + "%");
        String eo = Integer.toString(pbEo.getProgress());

        pbEc.setProgress(pbEc.getProgress() - 100 * distanceDriven/30000);
        tvEc.setText(pbEc.getProgress() + "%");
        String ec = Integer.toString(pbEc.getProgress());

        pbTf.setProgress(pbTf.getProgress() - 100 * distanceDriven/45000);
        tvTf.setText(pbTf.getProgress() + "%");
        String tf = Integer.toString(pbTf.getProgress());

        pbPsf.setProgress(pbPsf.getProgress() - 100 * distanceDriven/60000);
        tvPsf.setText(pbPsf.getProgress() + "%");
        String psf = Integer.toString(pbPsf.getProgress());

        pbBf.setProgress(pbBf.getProgress() - 100 * distanceDriven/30000);
        tvBf.setText(pbBf.getProgress() + "%");
        String bf = Integer.toString(pbBf.getProgress());

        updateFluids(eo, ec, tf, psf, bf);
    }

    public void addNewDistance(View v){

        builder1 = new AlertDialog.Builder(this);
        view = getLayoutInflater().inflate(androidx.customview.R.layout.custom_dialog, null);
        distance = view.findViewById(R.id.distance);
        button = view.findViewById(R.id.addDistanceBtn);
        builder1.setView(view);
        dialog = builder1.create();
        dialog.show();

        button.setOnClickListener(view -> {
            distanceDriven = Integer.parseInt(distance.getText().toString());
            dialog.dismiss();
            calculateAmountRemaining();
        });
    }

    public void deleteCar(){
        String URL = baseURL + "deleteCar.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, response -> {

            if(response.trim().equals("success")){
                Toast.makeText(CarInfoActivity.this, "Car removed", Toast.LENGTH_SHORT).show();
            }
            if(response.trim().equals("failure")){
                Toast.makeText(CarInfoActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }, error -> Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show()){
            @Nullable
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> data = new HashMap<>();
                data.put("car_id", car_id);
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    public void removeCar(View v){
        builder.setTitle("Remove " + brand)
                .setMessage("Are you sure you want to remove this car?")
                .setCancelable(true)
                .setPositiveButton("Yes", (dialogInterface, i) -> {
                    deleteCar();
                    Intent intent = new Intent(CarInfoActivity.this, UserCarsActivity.class);
                    intent.putExtra("user_id", id);
                    startActivity(intent);
                })
                .setNegativeButton("No", (dialogInterface, i12) -> dialogInterface.cancel()).show();
    }

    public void goToMoreInfo(View v){
        Intent intent = new Intent(CarInfoActivity.this, MoreInfoActivity.class);
        startActivity(intent);
    }
}