package com.example.tris;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class Croce extends View {

    private Paint paint;
    private int larghezzaLinea;
    private float x1,y1,x2,y2, x3, x4;
    private float dimensione;


    public Croce(Context context, AttributeSet attrs) {
        super(context, attrs);

        larghezzaLinea = 20;
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(larghezzaLinea);
        paint.setColor(Color.BLACK);
    }

    public void setDimensione(float dimensione)
    {
        this.dimensione = dimensione;
        larghezzaLinea = (int)dimensione/20;
        paint.setStrokeWidth(larghezzaLinea);
    }


    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        canvas.drawLine(x1,y1,x2,y2,paint);
        canvas.drawLine(x3,y1,x4,y2,paint);
    }

    public void setPoint(float x2,float y2)
    {
        this.x2=x2;
        this.y2=y2;
        x1 = 0;
        x3 = dimensione;
        y1 = 0;
        x4 = x3-x2;
    }
}
