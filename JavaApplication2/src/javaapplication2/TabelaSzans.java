/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Patrycja
 */
class TabelaSzans {
    private ArrayList<Integer> tabelaSzans;
    private Random losuj = new Random();

    TabelaSzans(ArrayList<WektorRozwiazania> wektory) {
        tabelaSzans = new ArrayList<>();
        int najgorszaTrasa = wektory.get(wektory.size() - 1).pobierzDlugoscTrasy();
        for (WektorRozwiazania w : wektory) {
            int indeks = wektory.indexOf(w);
            int dlugosc = w.pobierzDlugoscTrasy();
            int szansa = (najgorszaTrasa * najgorszaTrasa * najgorszaTrasa * najgorszaTrasa) / 
                    (dlugosc * dlugosc * dlugosc * dlugosc);
            for (int j = 0; j < szansa; j++) {
                tabelaSzans.add(indeks);
            }
        }
    }

    int[] losujPare() {
        int[] para = new int[2];
        para[0] = tabelaSzans.get(losuj.nextInt(tabelaSzans.size()));
        do {
            para[1] = tabelaSzans.get(losuj.nextInt(tabelaSzans.size()));
        } while (para[0] == para[1]);
        return para;
    }
    
}
