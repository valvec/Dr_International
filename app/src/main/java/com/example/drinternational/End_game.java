package com.example.drinternational;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class End_game extends AppCompatActivity {

int score;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);
        TextView scoreTv=findViewById(R.id.ScoreText);
        score=getIntent().getExtras().getInt("score");
        scoreTv.setText(getString(R.string.ScoreText)+ score);

        SharedPreferences shramba = getPreferences(Context.MODE_PRIVATE);
        String kljucTopScore = getResources().getString(R.string.klucTopScore);
        int topScore = shramba.getInt(kljucTopScore,0);

        if (score>topScore) topScore=score;

        TextView topTv=findViewById(R.id.HighScoreText);
        topTv.setText(getString(R.string.HighScoreText)+ topScore);

        SharedPreferences.Editor editor = shramba.edit();
        editor.putInt(kljucTopScore,topScore);
        editor.commit();
    }

    @Override
    public void onBackPressed() {
        //preprecim uporabo gumba za nazaj za vrnite v končano igro
    }

    public void onClickNG(View view) {
        Intent Ac2Quiz=new Intent(this, Ac2Quiz.class);
        startActivity(Ac2Quiz);
    }

    public void onClickRM(View view) {
        Intent mainMenu= new Intent(this,MainActivity.class);
        startActivity(mainMenu);
    }

    public void onClickShare(View view) {
        String message = "I just scored " + score+ " points on Dr. International. Download here: www.placeholder.link";
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_TEXT, message);

        startActivity(Intent.createChooser(share, "Share"));
    }
}


