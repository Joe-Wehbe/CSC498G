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

public class SignInActivity extends AppCompatActivity {

    private final String URL = "http://10.31.210.7/MyCar/signIn.php";
    private EditText etEmail, etPassword;
    private String email, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.dark_gray)));
        getSupportActionBar().setTitle("Sign In");

        email = password = "";
        etEmail = findViewById(R.id.email_address1);
        etPassword = findViewById(R.id.password1);
    }

    public void login(View view){
        email = etEmail.getText().toString().trim();
        password = etPassword.getText().toString().trim();

        //.makeText(SignInActivity.this, "test0", Toast.LENGTH_SHORT).show();

        if(!email.equals("") && !password.equals("")){

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    if (response.trim().equals("success")) {
                        Toast.makeText(SignInActivity.this, "test3", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignInActivity.this, NoCarsActivity.class);
                        startActivity(intent);
                        //finish();
                    } else if (response.trim().equals("failure")) {
                        Toast.makeText(SignInActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(SignInActivity.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String, String> data = new HashMap<>();
                    data.put("email", email);
                    data.put("password", password);
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }else{
            Toast.makeText(this, "Fields cannot be empty!", Toast.LENGTH_SHORT).show();

        }
    }

    public void signIn(View v){
        login(v);
    }


    public void goToSignUp(View v){

        Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
        startActivity(intent);

    }

}