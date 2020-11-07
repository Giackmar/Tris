package com.example.tris;

import android.view.animation.Animation;
import android.view.animation.Transformation;

public class AnimazioneCroce extends Animation{

    private Croce croce;

    private float x1,y1,x2,y2;

    public AnimazioneCroce(Croce croce, int x, int y, int x1, int y1) {
        this.x1= x1;
        this.y1= y1;
        x2=x;
        y2=y;
        this.croce = croce;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation transformation) {
        float x = x1 + ((x2 - x1) * interpolatedTime*2);
        float y = y1 + ((y2 - y1) * interpolatedTime*2);

        croce.setPoint(x, y);
        croce.requestLayout();
    }

}
