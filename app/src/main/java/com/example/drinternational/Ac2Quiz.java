package com.example.drinternational;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.StreetViewPanoramaOptions;
import com.google.android.gms.maps.SupportStreetViewPanoramaFragment;
import com.google.android.gms.maps.model.StreetViewSource;
import com.google.android.material.tabs.TabLayout;


public class Ac2Quiz extends AppCompatActivity implements OnStreetViewPanoramaReadyCallback, View.OnClickListener {
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

        if(currentIndex == totalQuestion ){
            finishQuiz();
            return;
        }

        streetViewPanorama1.setPosition(map.koordinate[currentIndex],StreetViewSource.OUTDOOR);
        streetViewPanorama1.setStreetNamesEnabled(false);
        streetViewPanorama1.setUserNavigationEnabled(false);
        ansA.setText(map.allCountries[selectionIndexesABCD[0]]);
        ansB.setText(map.allCountries[selectionIndexesABCD[1]]);
        ansC.setText(map.allCountries[selectionIndexesABCD[2]]);
        ansD.setText(map.allCountries[selectionIndexesABCD[3]]);

        double randomNum2 = Math.round(Math.random() * 4);
        if (randomNum2 >= 0 && randomNum2 < 1) ansA.setText(map.correctAnswers[currentIndex]);
        if (randomNum2 >= 1 && randomNum2 < 2) ansB.setText(map.correctAnswers[currentIndex]);
        if (randomNum2 >= 2 && randomNum2 < 3) ansC.setText(map.correctAnswers[currentIndex]);
        if (randomNum2 >= 3 && randomNum2 <= 4) ansD.setText(map.correctAnswers[currentIndex]);

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