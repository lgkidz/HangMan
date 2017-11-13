package com.example.thesupremeuser.hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startSinglePlayerGame(View v){
        Intent singleplayergame = new Intent(this,game.class);

        startActivity(singleplayergame);
    }
    /*
    public void startmultiPlayerGame(View v){
        //Intent singleplayergame = new Intent(this,game.class);

       // startActivity(singleplayergame);

        Toast.makeText(this, "Sorry, Multiplayer is still under development :(", Toast.LENGTH_SHORT).show();
    }
    */
    public void scoreboard(View v){
        Intent Scoreboard = new Intent(this,Score.class);
        startActivity(Scoreboard);
    }

    public void contactme(View v){
        ImageView cont = (ImageView) findViewById(R.id.contact);
        //Toast.makeText(this, "okay", Toast.LENGTH_SHORT).show();
        cont.setVisibility(View.VISIBLE);
    }
}
