package com.example.usiandroid.gameoflife.CustomViews;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;

import com.example.usiandroid.gameoflife.Logic.BoardState_Yandroid;

/**
 * Created by matthew on 12/14/15.
 */
public class DrawingView_Yandroid extends DrawingView_Challenge{
    public DrawingView_Yandroid(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }



    // Initialize all variables, objects, and settings
    @Override
    protected void init(){
        // Initialize class variables and objects
        initial = true;
        paint = new Paint();
        board = new BoardState_Yandroid(NUM_BLOCKS_ACROSS, NUM_BLOCKS_TALL);

        // Initialize graphics and canvas variables
        paint.setStrokeWidth(5);

        // Start graphic rendering thread
        startGraphics();
    }
}
