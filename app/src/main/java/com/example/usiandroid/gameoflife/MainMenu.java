package com.example.usiandroid.gameoflife;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.usiandroid.gameoflife.Challenges.ChallengeMenu;

// Main Menu Activity
public class MainMenu extends AppCompatActivity {

    // Constructor
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    // Start sandbox activity
    public void gotoSandbox(View view){
        Intent s = new Intent(this, SandBox.class);
        startActivity(s);
    }

    // Start challenges menu activity
    public void gotoChallenges(View view){
        Intent c = new Intent(this, ChallengeMenu.class);
        startActivity(c);
    }

}
