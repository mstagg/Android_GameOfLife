package com.example.usiandroid.gameoflife;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.usiandroid.gameoflife.Challenges.ChallengeMenu;
import com.example.usiandroid.gameoflife.Logic.MusicManager;

// Main Menu Activity
public class MainMenu extends AppCompatActivity {
    // Constructor
    boolean continueBGMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        continueBGMusic=true;
        setContentView(R.layout.activity_main_menu);
    }

    @Override
    public void onPause(){
        super.onPause();
        if(!continueBGMusic){
            MusicManager.pause();
        }
    }

    public void onResume(){
        super.onResume();
        continueBGMusic = false;
        MusicManager.start(this, R.raw.synth);
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

    public void gotoLearnMore(View view){
        startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life")));
    }
}
