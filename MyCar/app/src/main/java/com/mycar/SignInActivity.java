package com.mycar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SignInActivity extends AppCompatActivity {

    private final String URL = "http://192.168.1.103/MyCar/signIn.php";
    private EditText etEmail, etPassword;
    private String email, password;
    private int id;
    private int hasCar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.black));

        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.dark_gray)));
        getSupportActionBar().setTitle("Sign In");

        TextView tv = findViewById(R.id.sign_up);
        SpannableString text = new SpannableString("Sign up");
        text.setSpan(new UnderlineSpan(), 0, text.length(), 0);
        tv.setText(text);

        email = password = "";
        etEmail = findViewById(R.id.email_address1);
        etPassword = findViewById(R.id.password);
    }


    public void signIn(View view){
        email = etEmail.getText().toString().trim();
        password = etPassword.getText().toString().trim();

        if(!email.equals("") && !password.equals("")){
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, response -> {

                try{
                    hasCar = Integer.parseInt(response.trim().substring(0,1));
                    id = Integer.parseInt(response.trim().substring(1));

                    if(id > 0 && hasCar == 1) {
                        Intent intent = new Intent(SignInActivity.this, UserCarsActivity.class);
                        intent.putExtra("user_id", Integer.toString(id));
                        startActivity(intent);
                    }

                    if(id > 0 && hasCar == 0){
                        Intent intent = new Intent(SignInActivity.this, NoCarsActivity.class);
                        intent.putExtra("user_id", Integer.toString(id));
                        startActivity(intent);
                    }

                }catch (NumberFormatException e){

                    if(response.trim().equals("failure")) {
                        Toast.makeText(SignInActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(SignInActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }

            }, error -> Toast.makeText(SignInActivity.this, error.toString().trim(), Toast.LENGTH_SHORT).show()){
                @Nullable
                @Override
                protected Map<String, String> getParams() {

                    Map<String, String> data = new HashMap<>();
                    data.put("email", email);
                    data.put("password", password);
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }else{
            Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
        }
    }

    public void goToSignUp(View v){
        Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
        startActivity(intent);

    }

}