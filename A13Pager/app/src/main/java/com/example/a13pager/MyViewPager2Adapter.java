package com.example.a13pager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewPager2Adapter extends RecyclerView.Adapter<MyViewPager2Adapter.MyViewHolder>  {
    // Constructor of our ViewPager2Adapter class
    private MyModel[] cv_models;
    MyViewPager2Adapter(MyModel[] models) {
        this.cv_models = models;
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
        //// HERE
        holder.cv_city.setText(cv_models[position].mf_getCity());
        holder.cv_cond.setText(cv_models[position].mf_getCond());
        holder.constraintlayout.setBackgroundResource(cv_models[position].mf_getColor());
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
