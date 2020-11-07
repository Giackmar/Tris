package com.example.tris;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class Cerchio extends View {

    private final int angoloInizio = 270;
    private final Paint paint;
    private RectF rect;
    private int larghezzaLinea;
    private float angolo;
    private int dimensione;



    public Cerchio(Context context, AttributeSet attrs) {
        super(context, attrs);

        larghezzaLinea = 20;
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(larghezzaLinea);
        paint.setColor(Color.WHITE);
        angolo = 0;
    }

    public void setDimensione(int dimensione)
    {
        this.dimensione = dimensione;
        larghezzaLinea = dimensione/20;
        paint.setStrokeWidth(larghezzaLinea);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        rect = new RectF(larghezzaLinea, larghezzaLinea, dimensione + larghezzaLinea, dimensione + larghezzaLinea);
        canvas.drawArc(rect, angoloInizio, angolo, false, paint);
    }


    public float getAngolo() {
        return angolo;
    }

    public void setAngolo(float angolo) {
        this.angolo = angolo;
    }
}