package com.example.drinternational;

import com.google.android.gms.maps.model.LatLng;

public class map {
    public static  LatLng koordinate[] ={
            new LatLng(37.754130, -122.447129),
            new LatLng(46.050932, 14.510609),
            new LatLng(37.563229, 126.985752),
            new LatLng(61.0792072,8.9942215)
    };

    public static String choices[][] = {
            {"Slovenia","California","Germany","Croatia"},
            {"Slovenia","Spain","Bosnia","China"},
            {"Afganistan","Japan","Singapoor","South Korea"},
            {"Canada","Russia","Norway","New Zeland"}
    };

    public static String correctAnswers[] = {
            "California",
            "Slovenia",
            "South Korea",
            "Norway"
    };


}
