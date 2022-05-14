package com.example.drinternational;

import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.SupportStreetViewPanoramaFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;
import com.google.android.gms.maps.model.StreetViewSource;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class Ac2Quiz<streetViewPanoramaCamera> extends AppCompatActivity implements OnStreetViewPanoramaReadyCallback, View.OnClickListener, StreetViewPanorama.OnStreetViewPanoramaChangeListener {
    String countryName;
    int currentIndex=0;
    int totalQuestion = map.koordinate.length;
    Button ansA, ansB, ansC, ansD;
    String selectedAnswer = "";
    int score=0;
    StreetViewPanorama streetViewPanorama1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ac2_quiz);
        SupportStreetViewPanoramaFragment streetViewPanoramaFragment = (SupportStreetViewPanoramaFragment) getSupportFragmentManager().findFragmentById(R.id.street_view_panorama);
        assert streetViewPanoramaFragment != null;
        streetViewPanoramaFragment.getStreetViewPanoramaAsync(this);

        ansA = findViewById(R.id.button1);
        ansB = findViewById(R.id.button2);
        ansC = findViewById(R.id.button3);
        ansD = findViewById(R.id.button4);


        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
    }

    @Override
    public void onStreetViewPanoramaReady(StreetViewPanorama streetViewPanorama) {
        streetViewPanorama1=streetViewPanorama;
        naloziLokacijo();
    }



    void naloziLokacijo(){
        int randomNum;
        int u = 0;
        int selectionIndexesABCD[] = {-1,-1,-1,-1};


        randomNum = (int)(Math.random() * (map.allCountries.length-1));
        selectionIndexesABCD[0] = randomNum;

        while (selectionIndexesABCD[1]==-1 || selectionIndexesABCD[2]==-1 || selectionIndexesABCD[3]==-1) {
            randomNum = (int)(Math.random() * (map.allCountries.length-1));
            if (selectionIndexesABCD[0] != randomNum) { selectionIndexesABCD[1] = randomNum; }

            randomNum = (int)(Math.random() * (map.allCountries.length-1));
            if(selectionIndexesABCD[1]!=randomNum && selectionIndexesABCD[0]!=randomNum){selectionIndexesABCD[2]=randomNum;}

            randomNum = (int)(Math.random() * (map.allCountries.length-1));
            if(selectionIndexesABCD[2]!=randomNum && selectionIndexesABCD[1]!=randomNum && selectionIndexesABCD[0]!=randomNum){selectionIndexesABCD[3]=randomNum;}

        }





        //Toast.makeText(this, Integer.toString(randomNum), Toast.LENGTH_LONG).show();




        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);
        ansA.setVisibility(View.VISIBLE);
        ansB.setVisibility(View.VISIBLE);
        ansC.setVisibility(View.VISIBLE);
        ansD.setVisibility(View.VISIBLE);

        if(currentIndex == totalQuestion ){
            generateMap();
            return;
        }

        // Ime drzave iz koordinat
        Geocoder gcd = new Geocoder(this, Locale.US);
        List<Address> addresses = null;
        try {
            addresses = gcd.getFromLocation(map.koordinate[currentIndex].latitude, map.koordinate[currentIndex].longitude, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (addresses.size() > 0)
        {
            countryName=addresses.get(0).getCountryName();
            //Toast.makeText(this, countryName, Toast.LENGTH_LONG).show();
        }
        //

        streetViewPanorama1.setPosition(map.koordinate[currentIndex],10000,StreetViewSource.OUTDOOR);
        streetViewPanorama1.setStreetNamesEnabled(false);
        streetViewPanorama1.setUserNavigationEnabled(false);
        ansA.setText(map.allCountries[selectionIndexesABCD[0]]);
        ansB.setText(map.allCountries[selectionIndexesABCD[1]]);
        ansC.setText(map.allCountries[selectionIndexesABCD[2]]);
        ansD.setText(map.allCountries[selectionIndexesABCD[3]]);

        double randomNum2 = Math.round(Math.random() * 4);
        if (randomNum2 >= 0 && randomNum2 < 1) ansA.setText(countryName);
        if (randomNum2 >= 1 && randomNum2 < 2) ansB.setText(countryName);
        if (randomNum2 >= 2 && randomNum2 < 3) ansC.setText(countryName);
        if (randomNum2 >= 3 && randomNum2 <= 4) ansD.setText(countryName);
        streetViewPanorama1.setOnStreetViewPanoramaChangeListener(this);
    }

    void finishQuiz(){
        Intent intent= new Intent(this,End_game.class);
        intent.putExtra("score",score);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        Button clickedButton = (Button) view;
        selectedAnswer  = clickedButton.getText().toString();
        if (selectedAnswer==countryName){
            clickedButton.setBackgroundColor(Color.GREEN);
            currentIndex++;
            score+=100;
            (new Handler()).postDelayed(this::naloziLokacijo, 2000);

        }
        else{
            clickedButton.setBackgroundColor(Color.RED);
            (new Handler()).postDelayed(this::finishQuiz, 2000);
            currentIndex++;
        }


    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    public void onClickNewView(View view) {

        LatLng newPos =new LatLng(map.koordinate[currentIndex].latitude+0.001,map.koordinate[currentIndex].longitude+0.001);
        streetViewPanorama1.setPosition(newPos,1000,StreetViewSource.OUTDOOR);
        streetViewPanorama1.setStreetNamesEnabled(false);
        streetViewPanorama1.setUserNavigationEnabled(false);
        score-=50;
        Toast toast =Toast.makeText(this,"-50", Toast.LENGTH_SHORT);
        toast.show();

    }

    public void onClickPolovicka(View view) {
        score-=50;
        int removed=0;
        int rm=0;

        while(removed<2) {
            double randomNum2 = Math.round(Math.random() * 4);
            if (randomNum2 >= 0 && randomNum2 < 1 && ansA.getText() != countryName && rm!=1) {
                ansA.setVisibility(View.GONE);
                removed++;
                rm=1;
            }
            if (randomNum2 >= 1 && randomNum2 < 2 && ansB.getText() != countryName && rm!=2) {
                ansB.setVisibility(View.GONE);
                removed++;
                rm=2;
            }
            if (randomNum2 >= 2 && randomNum2 < 3 && ansC.getText() != countryName && rm!=3) {
                removed++;
                ansC.setVisibility(View.GONE);
                rm=3;
            }
            if (randomNum2 >= 3 && randomNum2 <= 4 && ansD.getText() != countryName && rm!=4) {
                ansD.setVisibility(View.GONE);
                removed++;
                rm=4;
            }
        }
        findViewById(R.id.polovicka).setVisibility(View.GONE);
        Toast toast =Toast.makeText(this,"-50", Toast.LENGTH_SHORT);
        toast.show();
    }


    @Override
    public void onStreetViewPanoramaChange(@NonNull StreetViewPanoramaLocation streetViewPanoramaLocation) {
        if (streetViewPanoramaLocation != null && streetViewPanoramaLocation.links != null) {
            Toast.makeText(Ac2Quiz.this, "StreetView Camera Found", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(Ac2Quiz.this, "StreetView Camera not Found", Toast.LENGTH_SHORT).show();
            naloziLokacijo();
        }

    }

    void generateMap(){

        for(currentIndex=0;currentIndex<4;currentIndex++){
            double lat=(Math.random() * 2)+ 47;
            double longi=(Math.random() * 2) +14;

        map.koordinate[currentIndex]=new LatLng(lat,longi);
        Toast.makeText(Ac2Quiz.this, String.valueOf(lat) + "  "+String.valueOf(longi), Toast.LENGTH_SHORT).show();

        }
        currentIndex=0;

    }




}



//wazzzupps