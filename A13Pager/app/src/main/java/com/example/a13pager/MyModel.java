package com.example.a13pager;

public class MyModel {
    String mv_city;
    String mv_cond;
    int mv_color;

    public MyModel(String city, String cond, int color) {
        mv_city = city;
        mv_cond = cond;
        mv_color = color;
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
}
