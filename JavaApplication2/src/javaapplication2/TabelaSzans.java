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
        double najgorszaTrasa = (double) wektory.get(wektory.size() - 1).pobierzDlugoscTrasy();
        for (WektorRozwiazania w : wektory) {
            int indeks = wektory.indexOf(w);
            double dlugosc = (double) w.pobierzDlugoscTrasy();
            double szansa = Math.pow(najgorszaTrasa / dlugosc, 4);
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
