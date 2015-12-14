package com.example.usiandroid.gameoflife;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.usiandroid.gameoflife.CustomViews.DrawingView;

// Sandbox mode activity
public class SandBox extends Activity {

    private boolean pausedState;
    private ImageButton imgBtn;
    private DrawingView drawSpace;

    // Constructor
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sand_box);
        pausedState = true;
        imgBtn = (ImageButton)findViewById(R.id.btn_play);
        drawSpace = (DrawingView)findViewById(R.id.drawing);
    }

    // Destructor
    @Override
    protected void onDestroy(){
        super.onDestroy();
        drawSpace.killThreads();
    }

    // Clears board and restores it to default state
    public void clearButton(View view){
        drawSpace.clearBoard();
        if(!pausedState){
            imgBtn.setImageResource(R.drawable.play);
            drawSpace.pauseLogic();
            pausedState = true;
        }
    }

    // Pause/play button, also starts and stops logic thread
    public void toggleButton(View view){
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
}
