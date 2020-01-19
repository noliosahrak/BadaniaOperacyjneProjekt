/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

import java.util.Random;

/**
 *
 * @author Patrycja
 */
public class WektorRozwiazania implements Comparable<WektorRozwiazania>{
    private int[] wektor;
    private Random losuj = new Random();
    private int trasa = 0;
    
    public WektorRozwiazania(int wymiar) {
        wektor = new int[wymiar];
        for (int i = 0; i < wymiar; i++) {
            wektor[i] = i;
        }
    }
    
    public WektorRozwiazania(int[] rozwiazanie) {
        wektor = rozwiazanie;
    }
    
    public int pobierzMiasto(int n) {
        return wektor[n];
    }
    
    public int dlugoscWektora() {
        return wektor.length;
    }
    
    public void wypiszRozwiazanie() {
        for (int i = 0; i < wektor.length; i++) {
            System.out.print(wektor[i] + ", ");
        }
        if (trasa != 0) System.out.print("długość trasy = "+trasa);
        System.out.println();
    }
    
    public void losujRozwiazanie() {
        for (int i = wektor.length - 1; i > 1 ; i--) {
            zamien(i,losuj.nextInt(i-1)+1);
        }
    }
    
    public WektorRozwiazania mod2opt(int a, int b) {
        int[] nowy = new int[wektor.length];
        for (int i = 0; i < wektor.length; i++) {
            if (i > a && i <= b) {
                nowy[i] = wektor[a + 1 + b - i];
            } else {
                nowy[i] = wektor[i];
            }
        }
        return new WektorRozwiazania(nowy);
    }

    private void zamien(int a, int b) {
        if (a != b) {
            int tymczasowy = wektor[a];
            wektor[a] = wektor[b];
            wektor[b] = tymczasowy;
        }
    }
    
    public int ObliczDlugoscTrasy(MacierzOdleglosci macierz) {
        trasa = macierz.obliczTrase(this);
        return trasa;
    }
    
    public int pobierzDlugoscTrasy() {
        return trasa;
    }

    @Override
    public int compareTo(WektorRozwiazania wektorPorownywany) {
        int porownanaTrasa = ((WektorRozwiazania)wektorPorownywany).pobierzDlugoscTrasy();
        return this.trasa - porownanaTrasa;
    }
}
