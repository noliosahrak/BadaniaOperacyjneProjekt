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
public class MacierzOdleglosci {
    private int[][] macierz;
    
    public MacierzOdleglosci(int wymiar) {
        macierz = new int[wymiar][wymiar];
    }
    
    public MacierzOdleglosci(int[][] macierz) {
        if (macierz.length == macierz[0].length) this.macierz = macierz;
    }
}
