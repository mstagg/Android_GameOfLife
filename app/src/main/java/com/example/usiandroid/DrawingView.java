package com.example.usiandroid;

/**
 * Created by matthew on 12/12/15.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;

import com.example.usiandroid.gameoflife.BoardState;
import com.example.usiandroid.gameoflife.Cell;

public class DrawingView extends View {

    private int w, h;
    private float blockWidth, blockHeight;
    private int NUM_BLOCKS_ACROSS = 15, NUM_BLOCKS_TALL = 30;
    private BoardState board;
    private Paint paint;
    private GraphicsLoop gThread;
    private LogicLoop lThread;

    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        board = new BoardState(NUM_BLOCKS_ACROSS, NUM_BLOCKS_TALL);
        gThread = new GraphicsLoop();
        gThread.start();
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
        this.paint.setColor(Color.rgb(0, 255, 0));
        float x = 0;
        float y = 0;
        for (int i = 0; i < NUM_BLOCKS_ACROSS; i++) {
            y = 0;
            for (int j = 0; j < NUM_BLOCKS_TALL; j++) {
                Cell c = board.getCellAtPos(i, j);
                if (c.isAlive()) {
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
                cl.setAlive(true);
                break;
            // Finger moves on screen
            case MotionEvent.ACTION_MOVE:
                cl.setAlive(true);
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                return false;
        }

        // Calling invalidate will cause onDraw() to execute
        invalidate();
        return true;
    }

    public class GraphicsLoop extends Thread {
        private long frameTime;

        GraphicsLoop(){
        }

        @Override
        public void run() {
            long frameTimeDelta = System.currentTimeMillis() - frameTime;
            if (frameTimeDelta > 16){
                frameTime = System.currentTimeMillis();
                invalidate();
            }
        }

        @Override
        public void start ()
        {
            frameTime = System.currentTimeMillis();
        }
    }
}

class LogicLoop extends Thread {
    private long frameTime;
    private BoardState board;

    LogicLoop(BoardState b){
        board = b;
    }

    public void run() {
        long frameTimeDelta = System.currentTimeMillis() - frameTime;
        //if (frameTimeDelta > 1000){
            board.updateCells();
            frameTime = System.currentTimeMillis();
        //}
    }

    public void start ()
    {
        frameTime = System.currentTimeMillis();
    }
}