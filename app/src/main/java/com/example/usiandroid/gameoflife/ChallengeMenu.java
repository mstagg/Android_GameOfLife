package com.example.usiandroid.gameoflife;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ChallengeMenu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge_menu);
    }

    public void gotoTheCross(View view){
        Intent cross = new Intent(this, TheCross.class);
        startActivity(cross);
    }
}
