package com.example.usiandroid.gameoflife.CustomViews;

/**
 * Created by matthew on 12/12/15.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.usiandroid.gameoflife.Logic.BoardState;
import com.example.usiandroid.gameoflife.Logic.Cell;

// Custom view to draw board state
public class DrawingView extends View {

    protected int canvasWidth, canvasHeight;
    protected int NUM_BLOCKS_ACROSS = 15, NUM_BLOCKS_TALL = 30;
    public int debounce = 0;
    protected float blockWidth, blockHeight;
    protected boolean initial;

    public BoardState board;
    protected Paint paint;
    protected GraphicsLoop gThread;
    private LogicLoop lThread;

    // Constructor
    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    // Initialize all variables, objects, and settings
    protected void init(){
        // Initialize class variables and objects
        initial = true;
        paint = new Paint();
        board = new BoardState(NUM_BLOCKS_ACROSS, NUM_BLOCKS_TALL);

        // Initialize graphics and canvas variables
        paint.setStrokeWidth(5);

        // Start graphic rendering thread
        startGraphics();
    }

    // Clears the board, sets all cells to dead
    public void clearBoard(){
        board.clearCells();
    }

    // Starts graphics rendering thread at 60fps
    protected void startGraphics(){
        gThread = new GraphicsLoop();
        gThread.start();
    }

    // Interrupts the logic thread, causing to to stop
    public void pauseLogic(){
        lThread.interrupt();
    }

    // Restarts the logic thread
    public void startLogic(){
        lThread = new LogicLoop(board);
        lThread.start();
    }

    public void killThreads(){
        gThread.interrupt();
        lThread.interrupt();
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
                    paint.setColor(Color.rgb(0, 0, 0));
                    canvas.drawRect(x, y, x + blockWidth, y + blockHeight, paint);
                } else if (c.isAlive()) {
                    paint.setColor(Color.rgb(0, 255, 0));
                    canvas.drawRect(x, y, x + blockWidth, y + blockHeight, paint);
                }
                y += blockHeight;
            }
            x += blockWidth;
        }

        // Draw grid to canvas
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.rgb(50, 50, 50));
        // Horizontal lines
        x = 0;
        for (int i = 0; i < NUM_BLOCKS_ACROSS - 1; i++) {
            x += blockWidth;
            canvas.drawLine(x, 0, x, canvasHeight, paint);
        }
        // Vertical lines
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

        // Handle touch
        switch(e.getAction()){
            // Finger touches screen
            case MotionEvent.ACTION_DOWN:
                break;
            // Finger moves on screen
            case MotionEvent.ACTION_MOVE:
                debounce++;
                if(debounce > 10) {
                    if (!cl.isWall() && !cl.isAlive()) {
                        cl.setAlive(true);
                    }
                }
                break;
            // Finger lifts up from screen
            case MotionEvent.ACTION_UP:
                if(!cl.isWall()){
                    if(cl.isAlive()){
                        cl.setAlive(false);
                    }else {
                        cl.setAlive(true);
                    }
                    debounce = 0;
                }
                break;
            default:
                return false;
        }
        return true;
    }

    // Thread to draw graphics to screen
    // Refreshes canvas at 60fps
    public class GraphicsLoop extends Thread {
        private long frameTime;

        GraphicsLoop(){
            frameTime = System.currentTimeMillis();
        }

        @Override
        public void run() {
            while(!Thread.currentThread().isInterrupted()) {
                long frameTimeDelta = System.currentTimeMillis() - frameTime;
                if (frameTimeDelta > 16) {
                    frameTime = System.currentTimeMillis();
                    postInvalidate();
                }
            }
        }
    }

    // Thread to handle game logic
    // Calculates current cell status once every .5 seconds
    public class LogicLoop extends Thread {
        private long frameTime;
        private BoardState board;

        LogicLoop(BoardState b){
            board = b;
        }

        public void run() {
            while(!Thread.currentThread().isInterrupted()) {
                long frameTimeDelta = System.currentTimeMillis() - frameTime;
                if (frameTimeDelta > 500){
                    board.updateCells();
                    frameTime = System.currentTimeMillis();
                }
            }
        }
    }
}