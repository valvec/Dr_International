package com.example.drinternational;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button buttonNewGame, buttonSelectMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonNewGame = (Button) findViewById(R.id.btnNewGame);
        buttonNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAc2Quiz();
            }
        });

        buttonSelectMap = (Button) findViewById(R.id.btnSelectMap);
        buttonSelectMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAc3SelectMap();
            }
        });
        SharedPreferences shramba = PreferenceManager.getDefaultSharedPreferences(this);
        String kljucTopScore = getResources().getString(R.string.klucTopScore);
        int topScore = shramba.getInt(kljucTopScore, 0);
        TextView tvTopScore=findViewById(R.id.tvTopScore);
        tvTopScore.setText(getString(R.string.HighScoreText)+ topScore);

    }

    public void openAc2Quiz(){
        Intent intentNewGame = new Intent(this, Ac2Quiz.class);
        startActivity(intentNewGame);
    }

    public void openAc3SelectMap(){
        Intent intentSelectMap = new Intent(this, Ac3SelectMap.class);
        startActivity(intentSelectMap);
    }
}