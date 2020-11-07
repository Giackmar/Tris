package com.example.tris;

import android.view.animation.Animation;
import android.view.animation.Transformation;

public class AnimazioneCerchio extends Animation {

    private  Cerchio cerchio;

    private float angoloVecchio;
    private float angoloNuovo;

    public AnimazioneCerchio(Cerchio cerchio, int angoloNuovo) {
        this.angoloVecchio = cerchio.getAngolo();
        this.angoloNuovo = angoloNuovo;
        this.cerchio = cerchio;
    }


    @Override
    protected void applyTransformation(float interpolatedTime, Transformation transformation) {
        float angle = angoloVecchio + ((angoloNuovo - angoloVecchio) * interpolatedTime);

        cerchio.setAngolo(angle);
        cerchio.requestLayout();
    }
}
