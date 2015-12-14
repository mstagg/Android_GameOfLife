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
}
