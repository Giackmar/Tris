package com.example.tris;

import android.widget.Button;

import java.util.Random;

public class Gioco{

    private Cella[][] matriceGioco;
    Boolean giocatore;
    Boolean inGioco;
    int vittorieX;
    int vittorieO;

    public Gioco(Button[][] matriceBottoni, Cerchio[][] matriceCerchi, Croce[][] matriceCroci, double rateo)
    {
        matriceGioco = new Cella[3][3];
        giocatore = true;
        inGioco = true;
        vittorieX=0;
        vittorieO=0;

        float dimensione = (int)(rateo*104);

        for(int riga=0; riga<3; riga++)
        {
            for(int colonna=0; colonna<3; colonna++)
            {
                Button bottone = matriceBottoni[riga][colonna];
                Cerchio cerchio = matriceCerchi[riga][colonna];
                Croce croce = matriceCroci[riga][colonna];
                croce.setDimensione(dimensione);
                cerchio.setDimensione((int)dimensione);
                matriceGioco[riga][colonna] = new Cella(bottone,cerchio,croce);
            }
        }
        resetta();
    }

    void resetta()
    {
        for (Cella[] subMatriceGioco:matriceGioco
             ) {
            for (Cella cella:subMatriceGioco
            ) {
                cella.resetta();
            }
        }
        inGioco = true;
        giocatore = true;
    }

    public void clickCella(Button bottoneCliccato)
    {
        for(int linea=0; linea<3; linea++)
        {
            for(int colonna=0; colonna<3; colonna++)
            {
                if(matriceGioco[linea][colonna].getButton()==bottoneCliccato && !matriceGioco[linea][colonna].occupato)
                {
                    if(giocatore)
                    {
                        matriceGioco[linea][colonna].scrivi("x");
                        giocatore = false;
                    }
                    else
                    {
                        matriceGioco[linea][colonna].scrivi("o");
                        giocatore = true;
                    }
                }
            }
        }
    }

    public void clickCellaBot(Button bottoneCliccato, int difficolta)
    {
        for(int linea=0; linea<3; linea++)
        {
            for(int colonna=0; colonna<3; colonna++)
            {
                if(matriceGioco[linea][colonna].getButton()==bottoneCliccato && !matriceGioco[linea][colonna].occupato)
                {
                    matriceGioco[linea][colonna].scrivi("x");
                    if(stopClick())return;
                    if(difficolta==1)
                    {
                        botClickFacile();
                    }else
                    {
                        botClickDifficile();
                    }
                }
            }
        }
    }

    public void botClickFacile()
    {
        boolean cellaVuota = false;
        while(!cellaVuota)
        {
            int riga = (int)(Math.random()*3);
            int colonna = (int)(Math.random()*3);
            Cella cella = matriceGioco[riga][colonna];
            if(!cella.occupato)
            {
                cellaVuota = true;
                cella.scrivi("o");
                giocatore = true;
            }
        }

    }

    public void botClickDifficile()
    {
        if(avversarioStaPerVincere())
        {
            Cella cella = bloccaAvversario();
            if(cella!=null)
            {
                cella.scrivi("o");
            }
            else
            {
                botClickFacile();
            }
        }
        else
        {
            botClickFacile();
        }
    }

    public boolean avversarioStaPerVincere()
    {
        for(int riga=0; riga<3; riga++)
        {
            int cont = 0;
            for(int colonna=0; colonna<3; colonna++)
            {
                if(matriceGioco[riga][colonna].getPlayer()=="x")
                {
                    cont++;
                }
            }
            if(cont>1)
            {
                return true;
            }
        }

        for(int colonna=0; colonna<3; colonna++)
        {
            int cont = 0;
            for(int riga=0; riga<3; riga++)
            {
                if(matriceGioco[riga][colonna].getPlayer()=="x")
                {
                    cont++;
                }
            }
            if(cont>1)
            {
                return true;
            }
        }

        int cont = 0;
        for(int pos=0; pos<3;pos++)
        {
            if(matriceGioco[pos][pos].getPlayer()=="x")
            {
                cont++;
            }
        }
        if(cont>1)
        {
            return true;
        }

        cont = 0;
        for(int pos=0; pos<3;pos++)
        {
            if(matriceGioco[pos][2-pos].getPlayer()=="x")
            {
                cont++;
            }
        }
        if(cont>1)
        {
            return true;
        }

        return false;
    }

    public Cella bloccaAvversario()
    {
        int cont;
        Cella cella = null;

        for(int riga=0; riga<3; riga++)
        {
            cont = 0;
            cella = null;
            for(int colonna=0; colonna<3; colonna++)
            {
                if(matriceGioco[riga][colonna].getPlayer()=="x")
                {
                    cont++;
                }
                else if(matriceGioco[riga][colonna].getPlayer()=="o")
                {

                }
                else
                {
                    cella = matriceGioco[riga][colonna];
                }
            }
            if(cont==2)
            {
                return cella;
            }
        }

        for(int colonna=0; colonna<3; colonna++)
        {
            cont = 0;
            cella = null;
            for(int riga=0; riga<3; riga++)
            {
                if(matriceGioco[riga][colonna].getPlayer()=="x")
                {
                    cont++;
                }
                else if(matriceGioco[riga][colonna].getPlayer()=="o")
                {

                }
                else
                {
                    cella = matriceGioco[riga][colonna];
                }
            }
            if(cont==2)
            {
                return cella;
            }
        }

        cont = 0;
        cella = null;
        for(int pos=0; pos<3;pos++)
        {
            if(matriceGioco[pos][pos].getPlayer()=="x")
            {
                cont++;
            }
            else if(matriceGioco[pos][pos].getPlayer()=="o")
            {

            }
            else
            {
                cella = matriceGioco[pos][pos];
            }
        }
        if(cont==2)
        {
            return cella;
        }

        cont = 0;
        cella = null;
        for(int pos=0; pos<3;pos++)
        {
            if(matriceGioco[pos][2-pos].getPlayer()=="x")
            {
                cont++;
            }
            else if(matriceGioco[pos][2-pos].getPlayer()=="o")
            {

            }
            else
            {
                cella = matriceGioco[pos][2-pos];
            }
        }
        if(cont==2)
        {
            return cella;
        }
        return null;
    }

    public boolean finePartita()
    {
        boolean finita = true;
        for(int linea=0; linea<3; linea++)
        {
            for(int colonna=0; colonna<3; colonna++)
            {
                if(matriceGioco[linea][colonna].getPlayer()=="")
                {
                    finita = false;
                }
            }
        }
        if(finita)
        {
            inGioco = false;
        }
        return finita;
    }

    public boolean vincitore(String s)
    {
        boolean vittoria = controlloVittoria(s);
        if(vittoria)
        {
            if(s=="x")
            {
                vittorieX++;
            }
            else
            {
                vittorieO++;
            }
            inGioco = false;
        }
        return vittoria;
    }

    public boolean controlloVittoria(String s)
    {
        boolean vittoria = false;
        int cont = 0;
        for (int linea = 0; linea < 3; linea++) {
            cont = 0;
            for (int colonna = 0; colonna < 3; colonna++) {
                if (matriceGioco[linea][colonna].getPlayer() == s) {
                    cont++;
                }
            }
            if (cont == 3) {
                vittoria=true;
            }
        }

        for (int linea = 0; linea < 3; linea++) {
            cont = 0;
            for (int colonna = 0; colonna < 3; colonna++) {
                if (matriceGioco[colonna][linea].getPlayer() == s) {
                    cont++;
                }
            }
            if (cont == 3) {
                vittoria=true;
            }
        }

        cont = 0;
        for (int linea = 0; linea < 3; linea++) {
            if (matriceGioco[linea][linea].getPlayer() == s) {
                cont++;
            }
        }
        if (cont == 3) {
            vittoria=true;
        }

        cont = 0;
        for (int linea = 0; linea < 3; linea++) {
            if (matriceGioco[linea][2 - linea].getPlayer() == s) {
                cont++;
            }
            if (cont == 3) {
                vittoria=true;
            }
        }
        return vittoria;
    }

    public boolean stopClick()
    {
        if(finePartita() || controlloVittoria("x") || controlloVittoria("o"))
        {
            return true;
        }
        return false;
    }

}
