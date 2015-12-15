package com.example.usiandroid.gameoflife.Challenges;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.usiandroid.gameoflife.R;

public class ChallengeMenu extends Activity {

    // Constructor
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge_menu);
    }

    // Starts The Cross challenge activity
    public void gotoTheCross(View view){
        Intent cross = new Intent(this, TheCross.class);
        startActivity(cross);
    }

    public void gotoYandroid(View view){
        Intent yan = new Intent(this, Yandroid.class);
        startActivity(yan);
    }

    public void gotoLayers(View view){
        Intent layers = new Intent(this, Layers.class);
        startActivity(layers);
    }

    public void gotoChevron(View view){
        Intent chevron = new Intent(this, Chevron.class);
        startActivity(chevron);
    }

    public void gotoHalfHalf(View view){
        Intent half = new Intent(this, HalfHalf.class);
        startActivity(half);
    }
}
