package com.example.usiandroid.gameoflife.Challenges;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.usiandroid.gameoflife.CustomViews.DrawingView_Challenge;
import com.example.usiandroid.gameoflife.Logic.MusicManager;
import com.example.usiandroid.gameoflife.R;

public class Chevron extends Activity {

    boolean continueBGMusic;
    private boolean pausedState;
    private ImageButton imgBtn;
    private TextView percentView, blocksView;
    private DrawingView_Challenge drawSpace;
    private UILoop updateLoop;

    // Constructor
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chevron);
        pausedState = true;
        imgBtn = (ImageButton)findViewById(R.id.btn_play);
        drawSpace = (DrawingView_Challenge)findViewById(R.id.drawing);
        percentView = (TextView)findViewById(R.id.percent);
        blocksView = (TextView)findViewById(R.id.blocks);
        updateLoop = new UILoop();
        updateLoop.start();
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

    // Destructor
    @Override
    protected void onDestroy(){
        super.onDestroy();
        drawSpace.killThreads();
        updateLoop.interrupt();
    }

    // Clears board and restores it to default state
    public void clearButton(View view){
        drawSpace.clearBoard();
        drawSpace.setInitial(true);
        if(!pausedState){
            imgBtn.setImageResource(R.drawable.play);
            drawSpace.pauseLogic();
            pausedState = true;
        }
    }

    // Pause/play button, also starts and stops logic thread
    public void toggleButton(View view){
        drawSpace.setInitial(false);
        if(pausedState) {
            imgBtn.setImageResource(R.drawable.stop);
            drawSpace.startLogic();
            pausedState = false;
        } else{
            imgBtn.setImageResource(R.drawable.play);
            drawSpace.pauseLogic();
            pausedState = true;
        }
    }

    // Used by thread to update UI elements along bottom of screen
    public void updateUI(){
        double percent = drawSpace.board.getPercentagePainted();
        percentView.setText(Double.toString(Math.round(percent)) + "%   ");
        int remaining = drawSpace.board.getRemainingBlocks();
        blocksView.setText("   " + Integer.toString(remaining));
    }

    // Threa that updates UI elements at 60fps
    public class UILoop extends Thread {
        private long frameTime;

        UILoop(){
        }

        public void run() {
            while(!Thread.currentThread().isInterrupted()) {
                long frameTimeDelta = System.currentTimeMillis() - frameTime;
                if (frameTimeDelta > 16){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            updateUI();
                        }
                    });
                    frameTime = System.currentTimeMillis();
                }
            }
        }
    }
}
