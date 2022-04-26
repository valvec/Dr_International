package com.example.drinternational;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button buttonNewGame;

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
    }

    public void openAc2Quiz(){
        Intent intentNewGame = new Intent(this, Ac2Quiz.class);
        startActivity(intentNewGame);
    }
}