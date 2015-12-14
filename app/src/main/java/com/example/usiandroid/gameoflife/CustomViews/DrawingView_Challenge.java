package com.example.usiandroid.gameoflife.CustomViews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.example.usiandroid.gameoflife.Challenges.BoardState_TheCross;
import com.example.usiandroid.gameoflife.Logic.BoardState;
import com.example.usiandroid.gameoflife.Logic.Cell;

/**
 * Created by matthew on 12/13/15.
 */

// Custom view specifically for challenges, inherits from DrawingView
public class DrawingView_Challenge extends DrawingView {

    // Constructor
    public DrawingView_Challenge(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    // Initialize all variables, objects, and settings
    @Override
    protected void init(){
        // Initialize class variables and objects
        initial = true;
        paint = new Paint();
        board = new BoardState_TheCross(NUM_BLOCKS_ACROSS, NUM_BLOCKS_TALL);

        // Initialize graphics and canvas variables
        paint.setStrokeWidth(5);

        // Start graphic rendering thread
        startGraphics();
    }

    // Draws cells and grid to screen
    @Override
    protected void onDraw(Canvas canvas) {
        canvasWidth = getWidth();
        canvasHeight = getHeight();
        blockHeight = canvasHeight / NUM_BLOCKS_TALL;
        blockWidth = canvasWidth / NUM_BLOCKS_ACROSS;

        // Clears the canvas
        canvas.drawColor(Color.WHITE);

        // Draws blocks to canvas
        paint.setStyle(Paint.Style.FILL);
        float x = 0;
        float y = 0;
        for (int i = 0; i < NUM_BLOCKS_ACROSS; i++) {
            y = 0;
            for (int j = 0; j < NUM_BLOCKS_TALL; j++) {
                Cell c = board.getCellAtPos(i, j);
                if(c.isWall()){
                    this.paint.setColor(Color.rgb(0, 0, 0));
                    canvas.drawRect(x, y, x + blockWidth, y + blockHeight, paint);
                } else if (c.isAlive()) {
                    this.paint.setColor(Color.rgb(255, 0, 0));
                    canvas.drawRect(x, y, x + blockWidth, y + blockHeight, paint);
                } else if(c.isVisited()){
                    this.paint.setColor(Color.rgb(210, 255, 210));
                    canvas.drawRect(x, y, x + blockWidth, y + blockHeight, paint);
                }
                y += blockHeight;
            }
            x += blockWidth;
        }

        // Draw grid to canvas
        paint.setStyle(Paint.Style.STROKE);
        this.paint.setColor(Color.rgb(50, 50, 50));
        x = 0;
        for (int i = 0; i < NUM_BLOCKS_ACROSS - 1; i++) {
            x += blockWidth;
            canvas.drawLine(x, 0, x, canvasHeight, paint);
        }

        y = 0;
        for (int i = 0; i < NUM_BLOCKS_TALL - 1; i++) {
            y += blockHeight;
            canvas.drawLine(0, y, canvasWidth, y, paint);
        }
    }

    // Manages screen touches
    @Override
    public boolean onTouchEvent(MotionEvent e){
        float touchX = e.getX();
        float touchY = e.getY();

        // Calculate cell that touch occurred in
        int xp = (int)(touchX / (canvasWidth / NUM_BLOCKS_ACROSS));
        int yp = (int)(touchY / (canvasHeight / NUM_BLOCKS_TALL));
        Cell cl = board.getCellAtPos(xp, yp);

        switch(e.getAction()){
            // Finger touches screen
            case MotionEvent.ACTION_DOWN:
                break;
            // Finger moves on screen
            case MotionEvent.ACTION_MOVE:
                debounce++;
                if(debounce > 10) {
                    if (!cl.isWall() && !cl.isAlive()) {
                        if(board.getRemainingBlocks() > 0) {
                            cl.setAlive(true);
                            board.incTotalPlaced();
                        }
                    }
                }
                break;
            // Finger lifts up from screen
            case MotionEvent.ACTION_UP:
                if(!cl.isWall()){
                    if(cl.isAlive()){
                        cl.setAlive(false);
                        board.decTotalPlaced();
                    }else {
                        if(board.getRemainingBlocks() > 0) {
                            cl.setAlive(true);
                            board.incTotalPlaced();
                        }
                    }
                    debounce = 0;
                }
                break;
            default:
                return false;
        }
        return true;
    }
}
