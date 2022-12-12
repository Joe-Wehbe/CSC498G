package com.mycar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

public class SignUpActivity extends AppCompatActivity {

    private EditText etFirstName, etLastName, etCountry, etEmail, etPassword, etConfirm;
    private String first_name, last_name, country, email, password, confirm;

    private String URL = "http://10.31.210.7/MyCar/signUp.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.dark_gray)));
        getSupportActionBar().setTitle("Sign Up");

        etFirstName = findViewById(R.id.first_name);
        etLastName = findViewById(R.id.last_name);
        etCountry = findViewById(R.id.country);
        etEmail = findViewById(R.id.email_address1);
        etPassword = findViewById(R.id.password);
        etConfirm = findViewById(R.id.confirm_your_password);

        first_name = last_name = country = email = password = confirm = "";

    }

    public void signUp(View v){
        first_name = etFirstName.getText().toString().trim();
        last_name = etLastName.getText().toString().trim();
        country = etCountry.getText().toString().trim();
        email = etEmail.getText().toString().trim();
        password = etPassword.getText().toString().trim();
        confirm = etConfirm.getText().toString().trim();

        if(!password.equals(confirm)){
            Toast.makeText(this, "Incorrect password confirmation", Toast.LENGTH_SHORT).show();

        }else if(!first_name.equals("") && !last_name.equals("") && !country.equals("")
        && !email.equals("") && !password.equals("")){

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Toast.makeText(SignUpActivity.this, response, Toast.LENGTH_SHORT).show();

                    if (response.trim().equals("")) {
                        Intent intent = new Intent(SignUpActivity.this, NoCarsActivity.class);
                        startActivity(intent);

                    } else if (response.trim().equals("failure")) {
                        Toast.makeText(SignUpActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
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
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("firstName", first_name);
                    data.put("lastName", last_name);
                    data.put("country", country);
                    data.put("email", email);
                    data.put("password", password);
                    data.put("confirm", confirm);
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