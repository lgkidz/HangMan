package com.example.thesupremeuser.hangman;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.content.SharedPreferencesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import static android.R.attr.name;

public class Gameover extends AppCompatActivity {

    int plscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameover);

        int score = getIntent().getIntExtra("score",0);

        TextView tvScore = (TextView)findViewById(R.id.score);
        tvScore.setText(String.valueOf(score));

        plscore = score;

    }

    public void saveScore(View v){
        SharedPreferences pref = getSharedPreferences("The_Pref", Context.MODE_PRIVATE);

        EditText et = (EditText) findViewById(R.id.playername);
        String playername = et.getText().toString();

        String PrevScores = pref.getString("SCORES","");

        SharedPreferences.Editor editor = pref.edit();


        editor.putString("SCORES",playername + " : " + plscore +"\n" + PrevScores);
        editor.commit();

        finish();
    }

}
