package com.example.thesupremeuser.hangman;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Score extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        TextView tvScore = (TextView) findViewById(R.id.scorelist);
        SharedPreferences pref = getSharedPreferences("The_Pref", MODE_PRIVATE);
        String scorelist = pref.getString("SCORES","Well, nothing here yet :/");

        tvScore.setText(scorelist);
    }

}
