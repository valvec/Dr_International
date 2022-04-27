package com.example.drinternational;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.SupportStreetViewPanoramaFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewSource;

public class Ac2Quiz extends AppCompatActivity implements OnStreetViewPanoramaReadyCallback {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ac2_quiz);
        SupportStreetViewPanoramaFragment streetViewPanoramaFragment =
                (SupportStreetViewPanoramaFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.streetviewpanorama);
        streetViewPanoramaFragment.getStreetViewPanoramaAsync(this);

    }
    @Override
    public void onStreetViewPanoramaReady(StreetViewPanorama streetViewPanorama) {
        //LatLng sanFrancisco = new LatLng(46.16915530536032, 14.4793833352353);
        LatLng repnje = new LatLng(46.168984555149, 14.479322643771296);
        streetViewPanorama.setPosition(repnje, StreetViewSource.OUTDOOR);
    }


}

//wazzzupps