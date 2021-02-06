package com.example.weatherapp;

import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class WeatherDataService {

    public String getCityId(String cityName){
        String url = "https://www.metaweather.com/api/location/search/?query="+ cityName;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,url,null,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                String cityID = "";
                try {
                    JSONObject cityInfo = response.getJSONObject(0);
                    cityID = cityInfo.getString("woeid");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Toast.makeText(MainActivity.this,"City ID = "+ cityID,Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"Something wrong",Toast.LENGTH_SHORT).show();
            }
        });

        MySingleton.getInstance(MainActivity.this).addToRequestQueue(request);

    }

//    public List<WeatherReportModel>getCityForecastByID(String cityID){
//
//    }
//
//    public List<WeatherReportModel>getCityForecastByID(String cityName){
//
//    }

}
