package com.example.tris;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class Singleplayer extends AppCompatActivity {

    Gioco gioco;


    View.OnClickListener clickBottoneTris;
    Button btnNuovaPartita;
    Button btnDifficolta;
    Button btnAzzeraStats;
    TextView tabellaInfo;
    View view;
    Button btnMenu;
    Button btnStats;
    RelativeLayout statsLayout;
    TextView textViewStats;
    TextView statsTitle;
    View imageView;
    int difficolta = 1;

    Button[][] matriceBottoni;
    Cerchio[][] matriceCerchi;
    Croce[][] matriceCroci;

    Button btn00;
    Button btn01;
    Button btn02;
    Button btn10;
    Button btn11;
    Button btn12;
    Button btn20;
    Button btn21;
    Button btn22;

    Cerchio cerchio00;
    Cerchio cerchio01;
    Cerchio cerchio02;
    Cerchio cerchio10;
    Cerchio cerchio11;
    Cerchio cerchio12;
    Cerchio cerchio20;
    Cerchio cerchio21;
    Cerchio cerchio22;

    Croce croce00;
    Croce croce01;
    Croce croce02;
    Croce croce10;
    Croce croce11;
    Croce croce12;
    Croce croce20;
    Croce croce21;
    Croce croce22;


    @Override
    public void onBackPressed() {
        if(statsLayout.getVisibility()==View.VISIBLE)
        {
            Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_up);
            statsLayout.setAnimation(animation);
            animation.setDuration(350);
            animation.start();
            statsLayout.setVisibility(View.INVISIBLE);
        }
        else
        {
            Intent intent = new Intent(Singleplayer.this, StartActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivityIfNeeded(intent,0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_gioco);

        tabellaInfo = findViewById(R.id.txt_Info);
        imageView = findViewById(R.id.imageView);
        btnStats = findViewById(R.id.btn_stats);
        statsLayout = findViewById(R.id.layout_stats);
        textViewStats = findViewById(R.id.textView_stats);
        statsTitle = findViewById(R.id.statsTitle);
        statsLayout.setVisibility(View.INVISIBLE);
        btnMenu = findViewById(R.id.btn_back);
        view = findViewById(R.id.view);
        btnNuovaPartita = findViewById(R.id.btn_nuovaPartita);
        btnAzzeraStats = findViewById(R.id.btn_azzeraStatistiche);
        btnDifficolta = findViewById(R.id.btn_difficolta);
        btnDifficolta.setVisibility(View.VISIBLE);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        final float rateo = metrics.xdpi / 254;




        btnNuovaPartita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gioco.resettaGioco();
                aggiornaTabella();
                btnDifficolta.setEnabled(true);
                finePartitaGrafica(1,0);
            }
        });


        btnAzzeraStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gioco.azzeraStats();
                btnAzzeraStats.setEnabled(false);
                impostaStats();
                statsLayout.setVisibility(View.VISIBLE);
                statsLayout.callOnClick();
            }
        });

        btnStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                impostaStats();
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_up);
                statsLayout.setAnimation(animation);
                statsLayout.setVisibility(View.VISIBLE);
                animation.setDuration(200);
                animation.start();
                statsLayout.setVisibility(View.VISIBLE);
            }
        });

        statsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_up);
                statsLayout.setAnimation(animation);
                animation.setDuration(350);
                animation.start();
                statsLayout.setVisibility(View.INVISIBLE);
            }
        });

        btnDifficolta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (difficolta == 1) {
                    difficolta++;
                    btnDifficolta.setText("DIFFICILE");
                }
                else if (difficolta == 2) {
                    difficolta++;
                    btnDifficolta.setText("IMPOSSIBILE");
                }
                else {
                    difficolta = 1;
                    btnDifficolta.setText("FACILE");
                }
            }
        });

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Singleplayer.this, StartActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivityIfNeeded(intent,0);
            }
        });





        clickBottoneTris = new View.OnClickListener() {
            @Override
            public void onClick(View btn) {
                if (gioco.inGioco) {
                    gioco.posizionaSimbolo(dammiBottone(btn.getId()), difficolta);
                    aggiornaTabella();
                    btnDifficolta.setEnabled(false);
                }
            }

        };

        btn00 = findViewById(R.id.btn_00);
        btn01 = findViewById(R.id.btn_01);
        btn02 = findViewById(R.id.btn_02);
        btn10 = findViewById(R.id.btn_10);
        btn11 = findViewById(R.id.btn_11);
        btn12 = findViewById(R.id.btn_12);
        btn20 = findViewById(R.id.btn_20);
        btn21 = findViewById(R.id.btn_21);
        btn22 = findViewById(R.id.btn_22);

        cerchio00 = findViewById(R.id.cerchio_00);
        cerchio01 = findViewById(R.id.cerchio_01);
        cerchio02 = findViewById(R.id.cerchio_02);
        cerchio10 = findViewById(R.id.cerchio_10);
        cerchio11 = findViewById(R.id.cerchio_11);
        cerchio12 = findViewById(R.id.cerchio_12);
        cerchio20 = findViewById(R.id.cerchio_20);
        cerchio21 = findViewById(R.id.cerchio_21);
        cerchio22 = findViewById(R.id.cerchio_22);

        croce00 = findViewById(R.id.croce_00);
        croce01 = findViewById(R.id.croce_01);
        croce02 = findViewById(R.id.croce_02);
        croce10 = findViewById(R.id.croce_10);
        croce11 = findViewById(R.id.croce_11);
        croce12 = findViewById(R.id.croce_12);
        croce20 = findViewById(R.id.croce_20);
        croce21 = findViewById(R.id.croce_21);
        croce22 = findViewById(R.id.croce_22);


        matriceBottoni = new Button[3][3];

        matriceBottoni[0][0] = btn00;
        matriceBottoni[0][1] = btn01;
        matriceBottoni[0][2] = btn02;
        matriceBottoni[1][0] = btn10;
        matriceBottoni[1][1] = btn11;
        matriceBottoni[1][2] = btn12;
        matriceBottoni[2][0] = btn20;
        matriceBottoni[2][1] = btn21;
        matriceBottoni[2][2] = btn22;

        matriceCerchi = new Cerchio[3][3];

        matriceCerchi[0][0] = cerchio00;
        matriceCerchi[0][1] = cerchio01;
        matriceCerchi[0][2] = cerchio02;
        matriceCerchi[1][0] = cerchio10;
        matriceCerchi[1][1] = cerchio11;
        matriceCerchi[1][2] = cerchio12;
        matriceCerchi[2][0] = cerchio20;
        matriceCerchi[2][1] = cerchio21;
        matriceCerchi[2][2] = cerchio22;

        matriceCroci = new Croce[3][3];

        matriceCroci[0][0] = croce00;
        matriceCroci[0][1] = croce01;
        matriceCroci[0][2] = croce02;
        matriceCroci[1][0] = croce10;
        matriceCroci[1][1] = croce11;
        matriceCroci[1][2] = croce12;
        matriceCroci[2][0] = croce20;
        matriceCroci[2][1] = croce21;
        matriceCroci[2][2] = croce22;


        gioco = new Gioco(matriceBottoni, matriceCerchi, matriceCroci, rateo, difficolta);

        impostoAzioneBottoniTris();
        aggiornaTabella();
    }

    void impostaStats()
    {
        textViewStats.setText("");
        textViewStats.setText(gioco.ottieniStats());
        statsTitle.setText("\nSTATISTICHE");
    }




    void impostoAzioneBottoniTris() {
        for (Button[] btnMatrice : matriceBottoni
        ) {
            for (Button btn : btnMatrice
            ) {
                btn.setText("");
                btn.setOnClickListener(clickBottoneTris);
            }
        }
    }

    Button dammiBottone(int id) {
        for (Button[] listaBtn : matriceBottoni
        ) {
            for (Button btn : listaBtn
            ) {
                if (btn.getId() == id) {
                    return btn;
                }
            }
        }
        return null;
    }

    void animazioneTestoFinePartita(boolean animate)
    {
        if(animate)
        {
            Animation animation = new AlphaAnimation(1, 0);
            animation.setDuration(500);
            animation.setInterpolator(new LinearInterpolator());
            animation.setRepeatCount(20);
            animation.setRepeatMode(Animation.REVERSE);
            btnNuovaPartita.setAnimation(animation);
        }
        else
        {
            btnNuovaPartita.setAnimation(null);
        }
    }

    void finePartitaGrafica(float val, int win)
    {

        if(win == 0)
        {
            tabellaInfo.setTypeface(Typeface.DEFAULT);
            view.setBackground(getDrawable(R.drawable.background));
            tabellaInfo.setTextSize(TypedValue.COMPLEX_UNIT_SP,24);
            btnNuovaPartita.setTypeface(Typeface.DEFAULT);
            animazioneTestoFinePartita(false);
        }
        else
        {
            tabellaInfo.setTypeface(Typeface.DEFAULT_BOLD);
            tabellaInfo.setTextSize(TypedValue.COMPLEX_UNIT_SP,30);
            btnNuovaPartita.setTypeface(Typeface.DEFAULT_BOLD);
            animazioneTestoFinePartita(true);
            if(win==1)
            {
                view.setBackground(getDrawable(R.drawable.gradient_background_win));
            }
            else if(win==2)
            {
                view.setBackground(getDrawable(R.drawable.gradient_background_lose));
            }
            else if(win==3)
            {
                view.setBackground(getDrawable(R.drawable.background));
            }
        }
        imageView.setAlpha(val);
        for (Cerchio[] cerchi:matriceCerchi
        ) {
            for (Cerchio cerchio:cerchi
            ) {
                cerchio.setAlpha(val);
            }
        }
        for (Croce[] croci:matriceCroci
        ) {
            for (Croce croce:croci
            ) {
                croce.setAlpha(val);
            }
        }
    }

    void aggiornaTabella() {
        if (gioco.vittoria("x")) {
            tabellaInfo.setText("HAI VINTO!");
            finePartitaGrafica((float)0.5,1);

        } else if (gioco.vittoria("o")) {
            tabellaInfo.setText("HAI PERSO");
            finePartitaGrafica((float)0.5,2);

        } else if (gioco.pareggio()) {
            tabellaInfo.setText("PAREGGIO");
            finePartitaGrafica((float)0.5,3);

        } else {
            if (gioco.giocatore) {
                tabellaInfo.setText("E' IL TUO TURNO");
            } else {
                tabellaInfo.setText("ATTENDI...");
            }
        }
        if(gioco.statsVuote())
        {
            btnAzzeraStats.setEnabled(false);
        }
        else
        {
            btnAzzeraStats.setEnabled(true);
        }
    }
}
