package com.example.powerpal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class WeatherAPI extends AppCompatActivity {


    private RelativeLayout homeRl;
    private ProgressBar loadingPB;
    private TextView cityNameTV,temperatureTV,conditionTV;
    private RecyclerView weatherRV;
    private TextInputEditText cityEdit;
    private ImageView iconIV, backIV, searchIV;
    private ArrayList<WeatherRVModel> weatherRVModelArrayList;
    private WeatherRVAdapter weatherRVAdapter;
    private LocationManager locationManager;
    private int PERMISSION_CODE = 1;
    private String cityName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS); //full screen
        setContentView(R.layout.activity_weather_api);
        homeRl = findViewById(R.id.idRLHome);
        loadingPB = findViewById(R.id.idPBLoading);
        cityNameTV = findViewById(R.id.idTVCityName);
        temperatureTV = findViewById(R.id.idTVTemperature);
        conditionTV = findViewById(R.id.idTVCondition);
        weatherRV = findViewById(R.id.idRvWeather);
        cityEdit = findViewById(R.id.idEditCity);
        iconIV = findViewById(R.id.idIVIcon);
        backIV = findViewById(R.id.idIVBack);
        searchIV = findViewById(R.id.idIVSearch);
        weatherRVModelArrayList = new ArrayList<>();
        weatherRVAdapter = new WeatherRVAdapter(this, weatherRVModelArrayList);
        weatherRV.setAdapter(weatherRVAdapter);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED ){
            ActivityCompat.requestPermissions( WeatherAPI.this, new String [] { Manifest.permission.ACCESS_FINE_LOCATION , Manifest.permission.ACCESS_COARSE_LOCATION },PERMISSION_CODE) ;
        }
        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        cityName = getCityName(location.getLongitude(),location.getAltitude());
        getWeatherInfo(cityName);

        searchIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = cityEdit.getText().toString();
                if (city.isEmpty()) {
                    Toast.makeText(WeatherAPI.this, "Please Enter City Name", Toast.LENGTH_SHORT).show();
                } else {
                    cityNameTV.setText(cityName);
                    getWeatherInfo(city);
                }
            }
        });


        }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == PERMISSION_CODE){
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Permission Granted ", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Permission Granted ", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    private String getCityName(double lon, double lat){
        String cityName = "NotFound";
        Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());
        try {
            List<Address> addresses = gcd.getFromLocation(lat,lon, 10);
            for(Address adr : addresses){
                if(adr!=null){
                    String city = adr.getLocality();
                    if(city!=null &&!city.equals("")){
                        cityName = city;
                    }else{
                        Log.d("TAG","CITY NOT FOUND");
                    }
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return cityName;
    }

     private void getWeatherInfo(String cityName){
        String url = "http://api.weatherapi.com/v1/current.json?key=c413b3292b3742fdb9240630212409&q=" + cityName +"&aqi=no";//get url
         cityNameTV.setText(cityName);
         RequestQueue requestQueue = Volley.newRequestQueue(WeatherAPI.this);
         JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
             @Override
             public void onResponse(JSONObject response) {
                 loadingPB.setVisibility(View.GONE);
                 homeRl.setVisibility(View.VISIBLE);
                 weatherRVModelArrayList.clear();
                 try{
                     String temp = response.getJSONObject("current").getString("temp_c");
                     temperatureTV.setText(temp+"c");
                     int isDay = response.getJSONObject("current").getInt("is_day");
                     String condition = response.getJSONObject("current").getJSONObject("condition").getString("text");
                     String conditionIcon = response.getJSONObject("current").getJSONObject("condition").getString("icon");
                     Picasso.get().load("http:".concat(conditionIcon)).into(iconIV);
                     if(isDay ==1){
                         Picasso.get().load("https://images.unsplash.com/photo-1505322022379-7c3353ee6291?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=687&q=80").into(backIV);
                     }else {
                         Picasso.get().load("https://images.unsplash.com/photo-1561540537-6b12ec38096b?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTN8fGRheXxlbnwwfHwwfHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60").into(backIV);
                     }

                     JSONObject forecast = response.getJSONObject("forecast");
                     JSONObject forecastArr = forecast.getJSONArray("forecastday").getJSONObject(0);
                     JSONArray hourArr = forecastArr.getJSONArray("hour");


                     for(int i = 0; i < hourArr.length(); i++){
                         JSONObject hourObj = hourArr.getJSONObject(i);
                         String time = hourObj.getString("time");
                         String temper = hourObj.getString("temp_c");
                         String img = hourObj.getJSONObject("condition").getString("icon");
                         String wind = hourObj.getString("wind_kph");
                        weatherRVModelArrayList.add(new WeatherRVModel(time, temper, img, wind));
                     }

                     weatherRVAdapter.notifyDataSetChanged();




                 }catch (JSONException e){
                        e.printStackTrace();
                 }
             }}, new Response.ErrorListener() {
                 @Override
                         public void onErrorResponse(VolleyError error){
                     Toast.makeText(WeatherAPI.this, "Please Enter Valid City.. ", Toast.LENGTH_SHORT).show();
                 }
             });
            requestQueue.add(jsonObjectRequest);
     }

}