package com.example.tris;

import android.widget.Button;

public class Cella{

    private Button bottone;
    private Cerchio cerchio;
    private Croce croce;
    private String simbolo;
    public boolean occupato;


    public Cella(Button bottone, Cerchio cerchio, Croce croce)
    {
        this.bottone = bottone;
        this.cerchio = cerchio;
        this.croce = croce;
        occupato = false;
        simbolo = "";
    }

    public Button getButton()
    {
        return bottone;
    }

    public String getPlayer()
    {
        return simbolo;
    }

    public void scrivi(String simbolo)
    {
        this.simbolo = simbolo;
        if(this.simbolo=="x")
        {
            AnimazioneCroce animazioneCroce = new AnimazioneCroce(croce, 150,150, 0 ,0);
            animazioneCroce.setDuration(300);
            croce.startAnimation(animazioneCroce);
            this.simbolo = "x";
        }
        else
        {
            AnimazioneCerchio animazioneCerchio = new AnimazioneCerchio(cerchio, 360);
            animazioneCerchio.setDuration(300);
            cerchio.startAnimation(animazioneCerchio);
            this.simbolo = "o";
        }
        occupato = true;
    }

    public void resetta()
    {
        if(simbolo=="x")
        {
            AnimazioneCroce animazioneCroce = new AnimazioneCroce(croce, 0,0,150,150);
            animazioneCroce.setDuration(300);
            croce.startAnimation(animazioneCroce);
            occupato = false;
            simbolo = "";
        }
        else if(simbolo=="o")
        {
            AnimazioneCerchio animazioneCerchio = new AnimazioneCerchio(cerchio, 0);
            animazioneCerchio.setDuration(300);
            cerchio.startAnimation(animazioneCerchio);
            occupato = false;
            simbolo = "";
        }
    }
}
