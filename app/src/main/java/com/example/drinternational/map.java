package com.example.drinternational;

import com.google.android.gms.maps.model.LatLng;

public class map {
    public static  LatLng koordinate[] ={
            new LatLng(37.754130, -122.447129),
            new LatLng(46.050932, 14.510609),
            new LatLng(37.563229, 126.985752),
            new LatLng(61.0792072,8.9942215)
    };


    public static String[] allCountries;



    public static double Europe[][][]={
            {{21,36},{70,-11}}, //po vrsti zemljepisna širina -range in začetna točka, nato zemljepisna dolđina range in nato začetna točka
            {{13,57},{23,5}},

    };

    public static double[][][] World ={
            {{21,36},{70,-11}}, //po vrsti zemljepisna širina -range in začetna točka, nato zemljepisna dolđina range in nato začetna točka
            {{13,57},{23,5}},
            {{15.0,32.0},{60.0,-123.0}},
            {{16.0,14.0},{10.0,-110.0}},
            {{33.0,-30.0},{50.0,-80.0}},
            {{12.0,-42.0},{25.0,-75.0}},

    };

    public static double[][][] Americas ={
            {{25.0,32.0},{60.0,-123.0}}, //USA
            {{16.0,14.0},{10.0,-110.0}},
            {{33.0,-30.0},{50.0,-80.0}},
            {{12.0,-42.0},{25.0,-75.0}},

    };

    public static double[][][] selected=World;

}
