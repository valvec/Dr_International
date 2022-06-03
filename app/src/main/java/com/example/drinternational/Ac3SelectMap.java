package com.example.drinternational;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Ac3SelectMap extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ac3_select_map);
    }

    public void onClickA(View view) {
        map.selected=map.Americas;
        Intent mainMenu= new Intent(this,MainActivity.class);
        startActivity(mainMenu);
    }

    public void onClickE(View view) {
        map.selected=map.Europe;
        Intent mainMenu= new Intent(this,MainActivity.class);
        startActivity(mainMenu);

    }

    public void onClickW(View view) {
        map.selected=map.World;
        Intent mainMenu= new Intent(this,MainActivity.class);
        startActivity(mainMenu);

    }
}