package com.example.drinternational;

import static java.lang.Thread.sleep;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.SupportStreetViewPanoramaFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewSource;

public class Ac2Quiz extends AppCompatActivity implements OnStreetViewPanoramaReadyCallback, View.OnClickListener {
    private StreetViewPanorama streetViewPanorama;
    private boolean secondlocation=false;
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

        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);

        if(currentIndex == totalQuestion ){
            finishQuiz();
            return;
        }
        streetViewPanorama1.setPosition(map.koordinate[currentIndex],StreetViewSource.OUTDOOR);
        ansA.setText(map.choices[currentIndex][0]);
        ansB.setText(map.choices[currentIndex][1]);
        ansC.setText(map.choices[currentIndex][2]);
        ansD.setText(map.choices[currentIndex][3]);
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
        if (selectedAnswer==map.correctAnswers[currentIndex]){
            clickedButton.setBackgroundColor(Color.GREEN);
            currentIndex++;
            score++;
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
}

//wazzzupps