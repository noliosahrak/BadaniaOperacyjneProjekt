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
public class WektorRozwiazania {
    private int[] wektor;
    Random losuj = new Random();
    
    public WektorRozwiazania(int wymiar) {
        wektor = new int[wymiar];
        for (int i = 0; i < wymiar; i++) {
            wektor[i] = i;
        }
    }
    
    public WektorRozwiazania(int[] rozwiazanie) {
        wektor = rozwiazanie;
    }
    
    public void wypiszRozwiazanie() {
        for (int i = 0; i < wektor.length; i++) {
            System.out.print(wektor[i] + ", ");
        }
        System.out.println();
    }
    
    public void losujRozwiazanie() {
        for (int i = wektor.length - 1; i > 1 ; i--) {
            zamien(i,losuj.nextInt(i-1)+1);
        }
    }

    private void zamien(int a, int b) {
        if (a != b) {
            int tymczasowy = wektor[a];
            wektor[a] = wektor[b];
            wektor[b] = tymczasowy;
        }
    }
}
