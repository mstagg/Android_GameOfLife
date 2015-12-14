package com.example.usiandroid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.example.usiandroid.DrawingView;
import com.example.usiandroid.gameoflife.BoardState_TheCross;
import com.example.usiandroid.gameoflife.Cell;

/**
 * Created by matthew on 12/13/15.
 */
public class DrawingView_TheCross extends DrawingView {
    public DrawingView_TheCross(Context context, AttributeSet attrs) {
        super(context, attrs);
        initial = true;
        paint = new Paint();
        board = new BoardState_TheCross(NUM_BLOCKS_ACROSS, NUM_BLOCKS_TALL);
        startGraphics();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // INIT
        this.paint.setStrokeWidth(5);

        this.w = getWidth();
        this.h = getHeight();

        this.blockHeight = h / NUM_BLOCKS_TALL;
        this.blockWidth = w / NUM_BLOCKS_ACROSS;

        canvas.drawColor(Color.WHITE);

        //DRAW BLOCKS
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
                    this.paint.setColor(Color.rgb(0, 255, 0));
                    canvas.drawRect(x, y, x + blockWidth, y + blockHeight, paint);
                } else if(c.isVisited()){
                    this.paint.setColor(Color.rgb(210, 255, 210));
                    canvas.drawRect(x, y, x + blockWidth, y + blockHeight, paint);
                }
                y += blockHeight;
            }
            x += blockWidth;
        }

        // DRAW GRID
        paint.setStyle(Paint.Style.STROKE);
        this.paint.setColor(Color.rgb(0, 0, 0));
        x = 0;
        for (int i = 0; i < NUM_BLOCKS_ACROSS - 1; i++) {
            x += blockWidth;
            canvas.drawLine(x, 0, x, h, paint);
        }

        y = 0;
        for (int i = 0; i < NUM_BLOCKS_TALL - 1; i++) {
            y += blockHeight;
            canvas.drawLine(0, y, w, y, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent e){
        float touchX = e.getX();
        float touchY = e.getY();

        int xp = (int)(touchX / (w / NUM_BLOCKS_ACROSS));
        int yp = (int)(touchY / (h / NUM_BLOCKS_TALL));
        Cell cl = board.getCellAtPos(xp, yp);

        switch(e.getAction()){
            // Finger presses down on screen
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

        // Calling invalidate will cause onDraw() to execute
        //invalidate();
        return true;
    }
}
