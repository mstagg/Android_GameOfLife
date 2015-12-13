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
import android.view.View;
import android.view.Window;

/**
 * Created by matthew on 12/12/15.
 */
public class DrawingView extends View {

    private int w, h;
    private float blockWidth, blockHeight;
    private int NUM_BLOCKS_ACROSS = 15, NUM_BLOCKS_TALL = 30;
    private Paint paint;

    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setColor(Color.rgb(0, 0, 0));
        paint.setStrokeWidth(5);

        w = getWidth();
        h = getHeight();

        blockHeight = h / NUM_BLOCKS_TALL;
        blockWidth = w / NUM_BLOCKS_ACROSS;

        float x = 0;
        for(int i = 0; i < NUM_BLOCKS_ACROSS - 1; i++){
            x += blockWidth;
            canvas.drawLine(x, 0, x, h, paint);
        }

        float y = 0;
        for(int i = 0; i < NUM_BLOCKS_TALL - 1; i++){
            y += blockHeight;
            canvas.drawLine(0, y, w, y, paint);
        }
    }
}
