/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

/**
 *
 * @author Patrycja
 */
public class WektorRozwiazania {
    private int[] wektor;
    
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
}
