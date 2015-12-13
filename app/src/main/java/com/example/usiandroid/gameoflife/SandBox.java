package com.example.usiandroid.gameoflife;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.usiandroid.DrawingView;

public class SandBox extends Activity {

    private boolean pausedState;
    private ImageButton imgBtn;
    private DrawingView drawSpace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sand_box);
        pausedState = true;
        imgBtn = (ImageButton)findViewById(R.id.btn_play);
        drawSpace = (DrawingView)findViewById(R.id.drawing);
    }

    public void clearButton(View view){
        drawSpace.clearBoard();
    }

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
