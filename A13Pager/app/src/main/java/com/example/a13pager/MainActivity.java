package com.example.a13pager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator;

//ver 01 simple, static
//ver 02 multiple layout xmls
//ver 03 simple layout, callback

//ver 11 combine with ver05 indicator + Weather swipe

public class MainActivity extends AppCompatActivity {
    // https://www.journaldev.com/26148/android-viewpager2
    private MyModel[] cv_pages = {
            new MyModel("Ann Arbor", "Clear", android.R.color.holo_orange_light,"48105","16°C","30°C","0°C","","","",""),
            new MyModel("Yuma", "Cloud", android.R.color.holo_green_light,"85364", "16°C","30°C","0°C","","","",""),
            new MyModel("Orlando", "Rain", android.R.color.holo_blue_light,"32801", "16°C","30°C","0°C","","","",""),
            new MyModel("Ypsilanti","Rain", android.R.color.holo_blue_light,"48197", "16°C","30°C","0°C","","","",""),
            new MyModel("Fort Wainwright","Rain", android.R.color.holo_blue_light,"99703", "16°C","30°C","0°C","","","",""),
    };
    private ViewPager2 viewPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager2 = findViewById(R.id.viewpager);
        viewPager2.setAdapter(new MyViewPager2Adapter(cv_pages,this));

        SpringDotsIndicator springDotsIndicator = (SpringDotsIndicator) findViewById(R.id.spring_dots_indicator);
        springDotsIndicator.setViewPager2(viewPager2);
    }
}

