package com.example.a13pager;

public class MyModel {
    String mv_city;
    String mv_cond;
    int mv_color;
    String mv_zipCode;
    String mv_currentTemp;

    public MyModel(String city, String cond, int color, String zipCode, String tempNow) {
        mv_city = city;
        mv_cond = cond;
        mv_color = color;
        mv_zipCode = zipCode;
        mv_currentTemp = tempNow;

    }

    public String mf_getCity() {
        return mv_city;
    }

    public String mf_getCond() {
        return mv_cond;
    }

    public int mf_getColor() {
        return mv_color;
    }

    public String mf_getZipCode() {
        return mv_zipCode;
    }

    public String mf_getCurrentTemp() {
        return mv_currentTemp;
    }
}
