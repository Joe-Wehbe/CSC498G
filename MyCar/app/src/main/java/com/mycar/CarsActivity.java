package com.mycar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.ListView;

import com.google.gson.JsonArray;
import com.google.gson.annotations.JsonAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.Buffer;
import java.util.Objects;

public class CarsActivity extends AppCompatActivity {

    private final String URLlink = "http://192.168.1.110/MyCar/getCars.php";
    private String[] brand;
    private String[] imagePath;

    ListView listView;
    BufferedInputStream bufferedInputStream;
    String line = null;
    String result = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars);

        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.dark_gray)));
        getSupportActionBar().setTitle("Your Cars");

        listView = findViewById(R.id.listView);

        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));

        collectData();



    }

    private void collectData(){

        try{
            URL url = new URL(URLlink);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            bufferedInputStream = new BufferedInputStream(con.getInputStream());

        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream));
            StringBuilder stringBuilder = new StringBuilder();

            while((line=bufferedReader.readLine()) != null){
                stringBuilder.append(line + "\n");
            }
            bufferedInputStream.close();
            result = stringBuilder.toString();

        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            JSONArray jsonArray = new JSONArray(result);
            JSONObject jsonObject = null;

            brand = new String[jsonArray.length()];
            imagePath = new String[jsonArray.length()];

            for(int i = 0; i <= jsonArray.length(); i++){
                jsonObject = jsonArray.getJSONObject(i);
                brand[i] = jsonObject.getString("brand");
                imagePath[i] = jsonObject.getString("image_url");
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}