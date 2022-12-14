package com.mycar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;
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

    private static String baseURL = "http://192.168.1.104/MyCar/";
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_info);

        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.black));

        Intent intent = getIntent();
        String brand = intent.getStringExtra("carBrand");
        id = intent.getStringExtra("user_id");

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


        String URL = String.format(baseURL + "getCarFluids.php?id=%1$s", id);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(CarInfoActivity.this, response, Toast.LENGTH_SHORT).show();

                        try{
                            JSONArray array = new JSONArray(response);
                            for(int i = 0; i < array.length(); i++){
                                JSONObject object = array.getJSONObject(i);

                                tvEo.setText(object.getString("engine_oil").trim());
                                tvEc.setText(object.getString("engine_coolant").trim());
                                tvTf.setText(object.getString("transmission_fluid").trim());
                                tvPsf.setText(object.getString("power_steering_fluid").trim());
                                tvBf.setText(object.getString("breaks_fluid").trim());

                                pbEo.setProgress(Integer.parseInt(object.getString("engine_oil").trim()));
                                pbEc.setProgress(Integer.parseInt(object.getString("engine_coolant").trim()));
                                pbTf.setProgress(Integer.parseInt(object.getString("transmission_fluid").trim()));
                                pbPsf.setProgress(Integer.parseInt(object.getString("power_steering_fluid").trim()));
                                pbBf.setProgress(Integer.parseInt(object.getString("breaks_fluid").trim()));

                            }

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }, error -> Toast.makeText(CarInfoActivity.this, error.toString(), Toast.LENGTH_SHORT).show());

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);


    }

    public void refillEo(View view) {
        if (pbEo.getProgress() >= 10){
            pbEo.setProgress(pbEo.getProgress() - 10);
            tvEo.setText(pbEo.getProgress() + "%");
        }
    }

    public void refillEc(View view) {
        if (pbEc.getProgress() >= 10){
            pbEc.setProgress(pbEc.getProgress() - 10);
            tvEc.setText(pbEc.getProgress() + "%");
        }
    }

    public void refillTf(View view) {
        if (pbTf.getProgress() >= 10){
            pbTf.setProgress(pbTf.getProgress() - 10);
            tvTf.setText(pbTf.getProgress() + "%");
        }
    }

    public void refillPsf(View view) {
        if (pbPsf.getProgress() >= 10){
            pbPsf.setProgress(pbPsf.getProgress() - 10);
            tvPsf.setText(pbPsf.getProgress() + "%");
        }
    }

    public void refillBf(View view) {
        if (pbBf.getProgress() >= 10){
            pbBf.setProgress(pbBf.getProgress() - 10);
            tvBf.setText(pbBf.getProgress() + "%");
        }
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