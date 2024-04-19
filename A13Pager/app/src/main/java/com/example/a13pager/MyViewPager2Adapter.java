package com.example.a13pager;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Callback;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

import org.json.JSONArray;


public class MyViewPager2Adapter extends RecyclerView.Adapter<MyViewPager2Adapter.MyViewHolder>  {
    // Constructor of our ViewPager2Adapter class
    private MyModel[] cv_models;
    private Context context;
    MyViewPager2Adapter(MyModel[] models,Context context) {
        this.cv_models = models;
        this.context = context;
    }

    // This method returns our layout
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single, parent, false);
        return new MyViewHolder(view);
    }

    // This method binds the screen with the view
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String zipCode = cv_models[position].mf_getZipCode();

        // Read API key from config.properties file
        Properties properties = new Properties();
        try {
            InputStream inputStream = context.getResources().openRawResource(R.raw.config);
            properties.load(inputStream);
            String apiKey = properties.getProperty("api_key");

            fetchCityName(zipCode, apiKey, holder.cv_city, holder.cv_cond);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //// HERE
        holder.cv_city.setText(cv_models[position].mf_getCity());
        holder.cv_cond.setText(cv_models[position].mf_getCond());
        holder.constraintlayout.setBackgroundResource(cv_models[position].mf_getColor());

        // Set the next three days' names
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dayFormat = new SimpleDateFormat("E"); // "E" for the short name of the day

        calendar.add(Calendar.DATE, 1);
        holder.cv_nextDay1TextView.setText(dayFormat.format(calendar.getTime()));

        calendar.add(Calendar.DATE, 1);
        holder.cv_nextDay2TextView.setText(dayFormat.format(calendar.getTime()));

        calendar.add(Calendar.DATE, 1);
        holder.cv_nextDay3TextView.setText(dayFormat.format(calendar.getTime()));
    }

    private void fetchCityName(String zipCode, String apiKey, TextView CityTextView, TextView descriptionTextView) {
        String apiUrl = "https://api.openweathermap.org/geo/1.0/zip?zip=" + zipCode + "&limit=5&appid=" + apiKey;

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(apiUrl)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseJson = response.body().string();
                    try {
                        JSONObject jsonObject = new JSONObject(responseJson);
                        String cityName = jsonObject.getString("name");
                        double latitude = jsonObject.getDouble("lat");
                        double longitude = jsonObject.getDouble("lon");

                        // Log the fetched city name
                        Log.d("CityName", "Fetched City: " + cityName);
                        Log.d("APIResponse", "Response JSON: " + responseJson);

                        fetchWeatherDescription(latitude, longitude, apiKey, descriptionTextView);

                        ((Activity) context).runOnUiThread(() -> CityTextView.setText(cityName));
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.e("JSONError", "Error parsing JSON: " + e.getMessage());
                    }
                }
            }
        });
    }

    private void fetchWeatherDescription(double latitude, double longitude, String apiKey, TextView descriptionTextView) {
        String apiUrl = "https://api.openweathermap.org/data/3.0/onecall?lat=" + latitude + "&lon=" + longitude + "&appid=" + apiKey + "&exclude=minutely,hourly";

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(apiUrl)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                ((Activity) context).runOnUiThread(() -> {
                    Toast.makeText(context, "Network error occurred", Toast.LENGTH_SHORT).show();
                    Log.e("NetworkError", "Error: " + e.getMessage());
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseJson = response.body().string();
                    try {
                        JSONObject jsonObject = new JSONObject(responseJson);
                        JSONObject currentWeather = jsonObject.getJSONObject("current");
                        JSONArray weatherArray = currentWeather.getJSONArray("weather");
                        JSONObject weatherObject = weatherArray.getJSONObject(0);
                        String description = weatherObject.getString("description");

                        ((Activity) context).runOnUiThread(() -> descriptionTextView.setText(description));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }





    //@Override
    //public int getItemViewType(int position) {
    //    return cv_models[position].mf_getColor();
    //}

    // This Method returns the size of the Array
    @Override
    public int getItemCount() {
        return cv_models.length;
    }

    // The ViewHolder class holds the view
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView cv_city;
        TextView cv_cond;
        ConstraintLayout constraintlayout;
        TextView cv_weatherIcon;
        TextView cv_temperatureTextView;
        TextView cv_highTemperatureTextView;
        TextView cv_lowTemperatureTextView;
        TextView cv_nextDay1TextView;
        TextView cv_nextDay2TextView;
        TextView cv_nextDay3TextView;
        TextView cv_nextDay1WeatherIconTextView;
        TextView cv_nextDay2WeatherIconTextView;
        TextView cv_nextDay3WeatherIconTextView;
        TextView cv_switchDegreeTextView;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cv_city = itemView.findViewById(R.id.weatherLocation);
            cv_cond = itemView.findViewById(R.id.weatherDescription);
            constraintlayout = itemView.findViewById(R.id.relativeLayout);
            cv_weatherIcon = itemView.findViewById(R.id.weatherIcon);
            cv_temperatureTextView = itemView.findViewById(R.id.temperatureTextView);
            cv_highTemperatureTextView = itemView.findViewById(R.id.highTemperatureTextView);
            cv_lowTemperatureTextView = itemView.findViewById(R.id.lowTemperatureTextView);
            cv_nextDay1TextView = itemView.findViewById(R.id.nextDay1TextView);
            cv_nextDay2TextView = itemView.findViewById(R.id.nextDay2TextView);
            cv_nextDay3TextView = itemView.findViewById(R.id.nextDay3TextView);
            cv_nextDay1WeatherIconTextView = itemView.findViewById(R.id.nextDay1WeatherIconTextView);
            cv_nextDay2WeatherIconTextView = itemView.findViewById(R.id.nextDay2WeatherIconTextView);
            cv_nextDay3WeatherIconTextView = itemView.findViewById(R.id.nextDay3WeatherIconTextView);
            cv_switchDegreeTextView = itemView.findViewById(R.id.switchDegreeTextView);

        }
    }
}
// on click F