package com.example.thesupremeuser.hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ThreadLocalRandom;

import static android.R.attr.max;
import static android.R.attr.min;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static com.example.thesupremeuser.hangman.R.id.fails;
import static com.example.thesupremeuser.hangman.R.id.hint;
import static com.example.thesupremeuser.hangman.R.id.text;

public class game extends AppCompatActivity {

    String Currentword = "";
    boolean matchedguess = false;
    int failedguesscounter = 0;
    int matchedletters = 0;

    int score = 0;
    TextView scoring;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        SetNewWord();
        createWordlayout(Currentword);
        scoring = (TextView) findViewById(R.id.game_scoring);
        scoring.setText("Score: " +score);
        Button hint = (Button)findViewById(R.id.hint);
        hint.setVisibility(View.INVISIBLE);
        if(Currentword.length() >=5){
            hint.setVisibility(View.VISIBLE);
        }

    }

    public void SetNewWord(){
        String Words = "obey alcoholic stop mouth aboriginal amused tiresome questionable serve add annoyed prevent suffer regret place type bear underwear forgetful erratic haircut edge needy parched popcorn knotty tasteless wicked deceive tired finicky overt craven lighten helpless count refuse minister attraction worthless lethal towering shaky join quince comparison base borrow fang thirsty station tight man puffy overflow time temporary grey flagrant reason bumpy fit smash carpenter hunt dust superficial caption new cats disappear concentrate record provide possessive obscene tumble unsightly inconclusive wrathful half public anxious mushy three book spray match spill occur sore draconian watery cactus pleasure boring sneeze sign glove grateful nimble skin harm button warm sneaky announce decorous crow wide copper shake hall grate eggs underwear tramp knee deadpan simplistic internal choke tenuous common paltry rush craven truculent joke heavy concerned inconclusive keen fuzzy descriptive icky nutty hallowed harbor fill unruly normal insurance sordid mixed shrug painful present sister reflect fanatical telephone sudden abortive thirsty witty motion tall bang square elite twist curtain comfortable succeed rule balance thank abrasive remarkable boy economic proud haircut arrogant aftermath ossified needy need wave crib door glib ambiguous middle steadfast dysfunctional untidy detect naive outrageous lopsided unaccountable bright relation wrist punish root peace abundant";

        String[] arrayofwords = Words.split(" ");

        //Log.d("loggggg","all: "+ arrayofwords.length);
        int randomNum =(int) (Math.random() * arrayofwords.length);
        String Word = arrayofwords[randomNum];
        Currentword = Word;
    }

    public void createWordlayout(String currentword){
        LinearLayout layout = (LinearLayout) findViewById(R.id.letters);
        for(int i=0; i<currentword.length();i++){
            TextView eachletter = (TextView) getLayoutInflater().inflate(R.layout.eachletter,null);
            layout.addView(eachletter);
        }
    }

    public void getletter(View v){
        EditText edittext = (EditText) findViewById(R.id.editText);



        String letter = edittext.getText().toString();
        edittext.setText("");

        Log.d("Loggg", "letter is " + letter);
        if(letter.length() == 1){
            checkletter(letter);
        }
        else{
            Toast.makeText(this, "Please insert a letter", Toast.LENGTH_SHORT).show();
        }

    }

    public void checkletter(String letter){
        for(int i = 0;i < Currentword.length();i++){
            if(Currentword.charAt(i) == letter.charAt(0) && alreadytrue(i, letter) == false){
                //Toast.makeText(this, "matched!", Toast.LENGTH_SHORT).show();
                matchedguess = true;

                showLetterAtIndex(i, letter.charAt(0));
                matchedletters++;
            }
            else if(alreadytrue(i, letter)){
                matchedguess = true;
            }
        }
        if(matchedguess == false){
            falseletter(letter);
        }
        matchedguess = false;
        if(matchedletters == Currentword.length()){
            score++;
            clearscreen();
            createWordlayout(Currentword);
        }
    }

    public void clearscreen(){
        Button hint = (Button)findViewById(R.id.hint);
        hint.setVisibility(View.INVISIBLE);
        TextView fails = (TextView) findViewById(R.id.fails);
        fails.setText("");
        failedguesscounter = 0;
        matchedletters = 0;
        scoring = (TextView) findViewById(R.id.game_scoring);
        scoring.setText("Score: " +score);

        LinearLayout lay = (LinearLayout) findViewById(R.id.letters);
        for(int i = 0; i < lay.getChildCount();i++){
            TextView pos = (TextView) lay.getChildAt(i);
            pos.setText("_");
        }
        ImageView hanger = (ImageView) findViewById(R.id.the_hanger);
        hanger.setImageResource(R.drawable.hang0);
        LinearLayout layout = (LinearLayout) findViewById(R.id.letters);
        if(((LinearLayout)layout).getChildCount() >0){
            ((LinearLayout)layout).removeAllViews();
        }
        SetNewWord();
        if(Currentword.length() >=5){
            hint.setVisibility(View.VISIBLE);
        }
    }

    public void falseletter(String failedletter){

        TextView fails = (TextView) findViewById(R.id.fails);

        String failedLetters = fails.getText().toString();
        fails.setText(failedLetters + " " + failedletter);

        failedguesscounter++;
        ImageView hanger = (ImageView) findViewById(R.id.the_hanger);
        if(failedguesscounter == 1){
            hanger.setImageResource(R.drawable.hang1);
        }
        else if(failedguesscounter == 2){
            hanger.setImageResource(R.drawable.hang2);
        }
        else if(failedguesscounter == 3){
            hanger.setImageResource(R.drawable.hang3);
        }
        else if(failedguesscounter == 4){
            hanger.setImageResource(R.drawable.hang4);
        }
        else if(failedguesscounter == 5){
            hanger.setImageResource(R.drawable.hang5);
        }
        else if(failedguesscounter == 6){
            Intent gameover = new Intent(this,Gameover.class);
            gameover.putExtra("score", score);
            startActivity(gameover);
            finish();
        }
    }

    public void showLetterAtIndex(int position, char letter){
        LinearLayout layoutletters = (LinearLayout)findViewById(R.id.letters);
        TextView textview = (TextView) layoutletters.getChildAt(position);
        textview.setText(Character.toString(letter));
    }

    public boolean alreadytrue(int position, String letter){
        LinearLayout layout = (LinearLayout) findViewById(R.id.letters);
        TextView tv = (TextView) layout.getChildAt(position);
            String l = tv.getText().toString();
            if(letter.equals(l)){
                return true;
            }
        return false;
    }

    public void gethint(View v){
        //Toast.makeText(this, "Hint working",Toast.LENGTH_SHORT).show();
        Button hint = (Button)findViewById(R.id.hint);
        LinearLayout layout = (LinearLayout) findViewById(R.id.letters);
        int randomNum = ThreadLocalRandom.current().nextInt(0, Currentword.length());
        String hintletter = Character.toString(Currentword.charAt(randomNum));
        for(int i=0;i<Currentword.length();i++){
            TextView textview = (TextView) layout.getChildAt(i);
            String randletter = textview.getText().toString();
            if(randletter.equals("_") && (Character.toString(Currentword.charAt(i)).equals(hintletter))){
                textview.setText(hintletter);
                matchedletters++;
            }
        }
        hint.setVisibility(View.INVISIBLE);
    }
}
