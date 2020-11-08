package com.example.tris;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MultiplayerActivity extends AppCompatActivity {

    Gioco gioco;


    View.OnClickListener clickBottoneTris;
    Button btnNuovaPartita;
    Button btnAzzeraPunti;
    TextView tabellaInfo;
    TextView textViewX;
    TextView textViewO;
    View view;
    Button btnMenu;

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
    public void onBackPressed(){
        startActivity(new Intent(MultiplayerActivity.this, StartActivity.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_gioco);
        findViewById(R.id.btn_difficolta).setVisibility(View.INVISIBLE);


        tabellaInfo = findViewById(R.id.txt_Info);
        textViewO = findViewById(R.id.textViewO);
        textViewX = findViewById(R.id.textViewX);
        btnMenu = findViewById(R.id.btn_back);
        view = findViewById(R.id.view);
        btnNuovaPartita = findViewById(R.id.btn_nuovaPartita);
        btnAzzeraPunti = findViewById(R.id.btn_azzeraPunti);



        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        final float rateo = metrics.xdpi/254;


        btnNuovaPartita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gioco.resettaGioco();
                aggiornaTabella();
            }
        });

        btnAzzeraPunti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gioco.vittorieO = 0;
                gioco.vittorieX = 0;
                textViewX.setText("Giocatore X: "+0);
                textViewO.setText("Giocatore O: "+0);
            }
        });

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MultiplayerActivity.this, StartActivity.class));
                finish();
            }
        });


        clickBottoneTris = new View.OnClickListener() {
            @Override
            public void onClick(View btn) {
                if(gioco.inGioco)
                {
                    gioco.clickCella(dammiBottone(btn.getId()));
                    aggiornaTabella();
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




        gioco = new Gioco(matriceBottoni, matriceCerchi, matriceCroci,rateo);

        impostoAzioneBottoniTris();
        aggiornaTabella();
    }




    void impostoAzioneBottoniTris()
    {
        for (Button[] btnMatrice: matriceBottoni
        ) {
            for (Button btn:btnMatrice
            ) {
                btn.setText("");
                btn.setOnClickListener(clickBottoneTris);
            }
        }
    }

    Button dammiBottone(int id)
    {
        for (Button[] listaBtn:matriceBottoni
        ) {
            for (Button btn:listaBtn
            ) {
                if(btn.getId()==id)
                {
                    return btn;
                }
            }
        }
        return null;
    }

    void aggiornaTabella()
    {
        if(gioco.haVinto("x"))
        {
            tabellaInfo.setText("Ha vinto il giocatore: X");

        }
        else if(gioco.haVinto("o"))
        {
            tabellaInfo.setText("Ha vinto il giocatore: O");

        }
        else if(gioco.pareggio())
        {
            tabellaInfo.setText("Pareggio");

        }
        else
        {
            if(gioco.giocatore)
            {
                tabellaInfo.setText("E' il turno del giocatore: X");
            }
            else
            {
                tabellaInfo.setText("E' il turno del giocatore: O");
            }
        }
        textViewX.setText("Giocatore X: "+gioco.vittorieX);
        textViewO.setText("Giocatore O: "+gioco.vittorieO);
    }
}
