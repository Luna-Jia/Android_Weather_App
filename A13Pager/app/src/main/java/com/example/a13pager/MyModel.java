package com.example.a13pager;

public class MyModel {
    String mv_city;
    String mv_cond;
    int mv_color;
    String mv_zipCode;

    public MyModel(String city, String cond, int color, String zipCode) {
        mv_city = city;
        mv_cond = cond;
        mv_color = color;
        mv_zipCode = zipCode;
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
}
