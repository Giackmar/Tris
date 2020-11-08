package com.example.tris;

import android.widget.Button;


public class Gioco{

    private Cella[][] matriceGioco;
    private int difficolta;
    Boolean giocatore;
    Boolean inGioco;
    int[] vittorieX;
    int[] vittorieO;
    int[] pareggio;

    public Gioco(Button[][] matriceBottoni, Cerchio[][] matriceCerchi, Croce[][] matriceCroci, double rateo, int difficolta)
    {
        this.difficolta = difficolta;
        matriceGioco = new Cella[3][3];
        giocatore = true;
        inGioco = true;
        vittorieX = new int[4];
        vittorieO = new int[4];
        pareggio = new int[4];

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
        resettaGioco();
    }

    void resettaGioco()
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

    public boolean pareggio()
    {
        boolean finita = true;
        for(int riga=0; riga<3; riga++)
        {
            for(int colonna=0; colonna<3; colonna++)
            {
                if(matriceGioco[riga][colonna].getPlayer()=="")
                {
                    finita = false;
                }
            }
        }
        if(finita)
        {
            inGioco = false;
            pareggio[difficolta]++;
        }
        return finita;
    }

    public boolean haVinto(String s)
    {
        boolean vittoria = controlloVittoria(s);
        if(vittoria)
        {
            if(s=="x")
            {
                vittorieX[difficolta]++;
            }
            else
            {
                vittorieO[difficolta]++;
            }
            inGioco = false;
        }
        return vittoria;
    }

    public boolean controlloVittoria(String s)
    {
        boolean vittoria = false;
        int cont = 0;
        for (int riga = 0; riga < 3; riga++) {
            cont = 0;
            for (int colonna = 0; colonna < 3; colonna++) {
                if (matriceGioco[riga][colonna].getPlayer() == s) {
                    cont++;
                }
            }
            if (cont == 3) {
                vittoria=true;
            }
        }

        for (int colonna = 0; colonna < 3; colonna++) {
            cont = 0;
            for (int riga = 0; riga < 3; riga++) {
                if (matriceGioco[riga][colonna].getPlayer() == s) {
                    cont++;
                }
            }
            if (cont == 3) {
                vittoria=true;
            }
        }

        cont = 0;
        for (int pos = 0; pos < 3; pos++) {
            if (matriceGioco[pos][pos].getPlayer() == s) {
                cont++;
            }
        }
        if (cont == 3) {
            vittoria=true;
        }

        cont = 0;
        for (int pos = 0; pos < 3; pos++) {
            if (matriceGioco[pos][2 - pos].getPlayer() == s) {
                cont++;
            }
            if (cont == 3) {
                vittoria=true;
            }
        }
        return vittoria;
    }

    public boolean partitaFinita()
    {
        if(pareggio())
        {
            pareggio[difficolta]--;
            return true;
        }
        if(controlloVittoria("x") || controlloVittoria("o"))
        {
            return true;
        }
        return false;
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
        this.difficolta = difficolta;
        for(int linea=0; linea<3; linea++)
        {
            for(int colonna=0; colonna<3; colonna++)
            {
                if(matriceGioco[linea][colonna].getButton()==bottoneCliccato && !matriceGioco[linea][colonna].occupato)
                {
                    matriceGioco[linea][colonna].scrivi("x");
                    if(partitaFinita())return;
                    if(difficolta==1)
                    {
                        botClickFacile();
                    }
                    else if(difficolta==2)
                    {
                        botClickDifficile();
                    }
                    else
                    {
                        botClickImpossibile();
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
        if(giocatoreStaVincendo("o"))
        {
            posizionaPerVittoria("o");
        }
        else if(giocatoreStaVincendo("x"))
        {
            Cella cella = bloccaAvversario();
            if(cella!=null)
            {
                cella.scrivi("o");
            }
            else
            {
                botClickIntelligente();
            }
        }
        else
        {
            botClickIntelligente();
        }
    }

    public void botClickImpossibile()
    {
        if(giocatoreStaVincendo("o"))
        {
            posizionaPerVittoria("o");
        }
        else if(giocatoreStaVincendo("x"))
        {
            Cella cella = bloccaAvversario();
            if(cella!=null)
            {
                cella.scrivi("o");
            }
            else
            {
                botClickIntelligente();
            }
        }
        else if(casoSpeciale()>0)
        {
            bloccaCasoSpeciale(casoSpeciale());
        }
        else
        {
            botClickIntelligente();
        }
    }

    public int casoSpeciale()
    {
        if(matriceGioco[0][0].getPlayer()=="x" && matriceGioco[2][2].getPlayer()=="x")
        {
            if(!matriceGioco[0][1].occupato && !matriceGioco[0][2].occupato && !matriceGioco[1][2].occupato)
            {
                return 1;
            }
            if(!matriceGioco[1][0].occupato && !matriceGioco[2][0].occupato && !matriceGioco[2][1].occupato)
            {
                return 2;
            }
        }
        if(matriceGioco[0][2].getPlayer()=="x" && matriceGioco[2][0].getPlayer()=="x")
        {
            if(!matriceGioco[0][1].occupato && !matriceGioco[0][0].occupato && !matriceGioco[1][0].occupato)
            {
                return 1;
            }
            if(!matriceGioco[1][2].occupato && !matriceGioco[2][1].occupato && !matriceGioco[2][2].occupato)
            {
                return 2;
            }
        }
        return 0;
    }

    public void bloccaCasoSpeciale(int caso)
    {
        if(caso == 1)
        {
            matriceGioco[2][1].scrivi("o");
        }
        else
        {
            matriceGioco[2][1].scrivi("o");
        }
    }

    public void posizionaPerVittoria(String giocatore)
    {
        for(int riga=0; riga<3; riga++)
        {
            int cont = 0;
            boolean ok = true;
            Cella cella = null;
            for(int colonna=0; colonna<3; colonna++)
            {
                if(matriceGioco[riga][colonna].getPlayer()==giocatore)
                {
                    cont++;
                }
                else if(matriceGioco[riga][colonna].occupato)
                {
                    ok = false;
                }
                else
                {
                    cella = matriceGioco[riga][colonna];
                }

            }
            if(cont>1 && ok && cella!= null)
            {
                cella.scrivi("o");
                return;
            }
        }

        for(int colonna=0; colonna<3; colonna++)
        {
            int cont = 0;
            boolean ok = true;
            Cella cella = null;
            for(int riga=0; riga<3; riga++)
            {
                if(matriceGioco[riga][colonna].getPlayer()==giocatore)
                {
                    cont++;
                }
                else if(matriceGioco[riga][colonna].occupato)
                {
                    ok = false;
                }
                else
                {
                    cella = matriceGioco[riga][colonna];
                }
            }
            if(cont>1 && ok && cella!= null)
            {
                cella.scrivi("o");
                return;
            }
        }

        int cont = 0;
        boolean ok = true;
        Cella cella = null;
        for(int pos=0; pos<3;pos++)
        {
            if(matriceGioco[pos][pos].getPlayer()==giocatore)
            {
                cont++;
            }
            else if(matriceGioco[pos][pos].occupato)
            {
                ok = false;
            }
            else
            {
                cella = matriceGioco[pos][pos];
            }
        }
        if(cont>1 && ok && cella!= null)
        {
            cella.scrivi("o");
            return;
        }

        cont = 0;
        ok = true;
        cella = null;
        for(int pos=0; pos<3;pos++)
        {
            if(matriceGioco[pos][2-pos].getPlayer()==giocatore)
            {
                cont++;
            }
            else if(matriceGioco[pos][2-pos].occupato)
            {
                ok = false;
            }
            else
            {
                cella = matriceGioco[pos][2-pos];
            }
        }
        if(cont>1 && ok && cella!= null)
        {
            cella.scrivi("o");
            return;
        }
    }

    public void botClickIntelligente()
    {
        if(!matriceGioco[1][1].occupato) {
            matriceGioco[1][1].scrivi("o");
            return;
        }
        int cont00 = 0;
        int cont22 = 0;
        int cont20 = 0;
        int cont02 = 0;

        if(!matriceGioco[0][0].occupato)
        {
            cont00=contatore(0,0,0);
        }
        if(!matriceGioco[2][2].occupato)
        {
            cont22=contatore(2,2,0);
        }
        if(!matriceGioco[2][0].occupato)
        {
            cont20=contatore(2,0,2);
        }
        if(!matriceGioco[0][2].occupato)
        {
            cont02=contatore(0,2,2);
        }
        int max = 0;
        String[] posizioni = new String[4];
        int cont = 0;
        if(cont00>max)
        {
            max=cont00;
            posizioni[cont] = "cont00";
            cont++;
        }
        else if(cont00==max)
        {
            posizioni[cont] = "cont00";
            cont++;
        }
        if(cont22>max)
        {
            max=cont22;
            cont=0;
            posizioni = new String[4];
            posizioni[cont] = "cont22";
            cont++;
        }
        else if(cont22==max)
        {
            posizioni[cont] = "cont22";
            cont++;
        }
        if(cont20>max)
        {
            max=cont20;
            cont=0;
            posizioni = new String[4];
            posizioni[cont] = "cont20";
            cont++;
        }
        else if(cont20==max)
        {
            posizioni[cont] = "cont20";
            cont++;
        }
        if(cont02>max)
        {
            max=cont02;
            cont=0;
            posizioni = new String[4];
            posizioni[cont] = "cont02";
            cont++;

        }
        else if(cont02==max)
        {
            posizioni[cont] = "cont02";
            cont++;
        }
        int pos = (int)(Math.random()*cont);
        if(max==0)
        {
            botClickFacile();
            return;
        }
        switch (posizioni[pos]){
            case "cont00":{
                matriceGioco[0][0].scrivi("o");
                return;
            }
            case "cont22":{
                matriceGioco[2][2].scrivi("o");
                return;
            }
            case "cont20":{
                matriceGioco[2][0].scrivi("o");
                return;
            }
            case "cont02":{
                matriceGioco[0][2].scrivi("o");
                return;
            }
        }
        botClickFacile();
    }

    public int contatore(int n1, int n2, int diag)
    {
        int cont = 0;
        for(int i=0; i<3; i++)
        {
            if(matriceGioco[n1][i].getPlayer()=="x")
            {
                cont++;
            }
            if(diag!=0)
            {
                if(matriceGioco[i][diag-i].getPlayer()=="x")
                {
                    cont++;
                }
            }
            else
            {
                if(matriceGioco[i][i].getPlayer()=="x")
                {
                    cont++;
                }
            }
            if(matriceGioco[i][n2].getPlayer()=="x")
            {
                cont++;
            }
        }
        return cont;
    }

    public boolean giocatoreStaVincendo(String giocatore)
    {
        for(int riga=0; riga<3; riga++)
        {
            int cont = 0;
            boolean ok = true;
            for(int colonna=0; colonna<3; colonna++)
            {
                if(matriceGioco[riga][colonna].getPlayer()==giocatore)
                {
                    cont++;
                }
                else if(matriceGioco[riga][colonna].occupato)
                {
                    ok = false;
                }
            }
            if(cont>1 && ok)
            {
                return true;
            }
        }

        for(int colonna=0; colonna<3; colonna++)
        {
            int cont = 0;
            boolean ok = true;
            for(int riga=0; riga<3; riga++)
            {
                if(matriceGioco[riga][colonna].getPlayer()==giocatore)
                {
                    cont++;
                }
                else if(matriceGioco[riga][colonna].occupato)
                {
                    ok = false;
                }
            }
            if(cont>1 && ok)
            {
                return true;
            }
        }

        int cont = 0;
        boolean ok = true;
        for(int pos=0; pos<3;pos++)
        {
            if(matriceGioco[pos][pos].getPlayer()==giocatore)
            {
                cont++;
            }
            else if(matriceGioco[pos][pos].occupato)
            {
                ok = false;
            }
        }
        if(cont>1 && ok)
        {
            return true;
        }

        cont = 0;
        ok = true;
        for(int pos=0; pos<3;pos++)
        {
            if(matriceGioco[pos][2-pos].getPlayer()==giocatore)
            {
                cont++;
            }
            else if(matriceGioco[pos][2-pos].occupato)
            {
                ok = false;
            }
        }
        if(cont>1 && ok)
        {
            return true;
        }

        return false;
    }

    public Cella bloccaAvversario()
    {
        int cont;
        Cella cella = null;
        boolean ok = true;

        for(int riga=0; riga<3; riga++)
        {
            cont = 0;
            cella = null;
            ok = true;
            for(int colonna=0; colonna<3; colonna++)
            {
                if(matriceGioco[riga][colonna].getPlayer()=="x")
                {
                    cont++;
                }
                else if(matriceGioco[riga][colonna].getPlayer()=="o")
                {
                    ok = false;
                }
                else
                {
                    cella = matriceGioco[riga][colonna];
                }
            }
            if(cont==2 && ok)
            {
                return cella;
            }
        }

        for(int colonna=0; colonna<3; colonna++)
        {
            cont = 0;
            cella = null;
            ok = true;
            for(int riga=0; riga<3; riga++)
            {
                if(matriceGioco[riga][colonna].getPlayer()=="x")
                {
                    cont++;
                }
                else if(matriceGioco[riga][colonna].getPlayer()=="o")
                {
                    ok = false;
                }
                else
                {
                    cella = matriceGioco[riga][colonna];
                }
            }
            if(cont==2 && ok)
            {
                return cella;
            }
        }

        cont = 0;
        cella = null;
        ok = true;
        for(int pos=0; pos<3;pos++)
        {
            if(matriceGioco[pos][pos].getPlayer()=="x")
            {
                cont++;
            }
            else if(matriceGioco[pos][pos].getPlayer()=="o")
            {
                ok = false;
            }
            else
            {
                cella = matriceGioco[pos][pos];
            }
        }
        if(cont==2 && ok)
        {
            return cella;
        }

        cont = 0;
        cella = null;
        ok = true;
        for(int pos=0; pos<3;pos++)
        {
            if(matriceGioco[pos][2-pos].getPlayer()=="x")
            {
                cont++;
            }
            else if(matriceGioco[pos][2-pos].getPlayer()=="o")
            {
                ok = false;
            }
            else
            {
                cella = matriceGioco[pos][2-pos];
            }
        }
        if(cont==2 && ok)
        {
            return cella;
        }
        return null;
    }

    public String ottieniStats()
    {
        String stats = "\n\n\n\n\n\n";
        stats+="  MODALITA FACILE:\n";
        stats+="    Vinte:  "+vittorieX[1]+"\n";
        stats+="    Perse:  "+vittorieO[1]+"\n";
        stats+="    Pareggi:  "+pareggio[1]+"\n\n";
        stats+="  MODALITA DIFFICILE:\n";
        stats+="    Vinte:  "+vittorieX[2]+"\n";
        stats+="    Perse:  "+vittorieO[2]+"\n";
        stats+="    Pareggi:  "+pareggio[2]+"\n\n";
        stats+="  MODALITA IMPOSSIBILE:\n";
        stats+="    Vinte:  "+vittorieX[3]+"\n";
        stats+="    Perse:  "+vittorieO[3]+"\n";
        stats+="    Pareggi:  "+pareggio[3];
        return stats;
    }

    public String ottieniStatsMultyPlayer()
    {
        String stats = "\n\n\n\n\n\n";
        stats+="    Vittorie x:  "+vittorieX[0]+"\n";
        stats+="    Vittorie o:  "+vittorieO[0]+"\n";
        stats+="    Pareggi:  "+pareggio[0];
        return stats;
    }

    public void azzeraStats()
    {
        vittorieX = new int[4];
        vittorieO = new int[4];
        pareggio = new int[4];
    }

    public boolean statsVuote()
    {
        boolean vuote = true;
        for (int stat:vittorieX) {
            if(stat != 0)
            {
                vuote = false;
            }
        }
        for (int stat:vittorieO) {
            if(stat != 0)
            {
                vuote = false;
            }
        }
        for (int stat:pareggio) {
            if (stat != 0) {
                vuote = false;
            }
        }
        return vuote;
    }
}
