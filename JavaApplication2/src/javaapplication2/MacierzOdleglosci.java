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
    private String[] miasta;
    
    public MacierzOdleglosci(int wymiar) {
        macierz = new int[wymiar][wymiar];
        miasta = new String[wymiar];
    }
    
    public MacierzOdleglosci(int[][] macierz) {
        if (macierz.length == macierz[0].length) {
            this.macierz = macierz;
            miasta = new String[macierz.length];
        }
    }

    MacierzOdleglosci(int[][] macierz, String[] miasta) {
        if (macierz.length == macierz[0].length && macierz.length == miasta.length) {
            this.macierz = macierz;
            this.miasta = miasta;
        }
    }
    
    public int obliczTrase(WektorRozwiazania rozwiazanie) {
        int suma = 0;
        int a, b = 0;
        if (macierz.length == rozwiazanie.dlugoscWektora()) {
            for (int i = 0; i < macierz.length - 1; i++) {
                a = rozwiazanie.pobierzMiasto(i);
                b = rozwiazanie.pobierzMiasto(i + 1);
                suma += macierz[a][b];
            }
            suma += macierz[b][0];
        }
        return suma;
    }
    
    public String wypiszTrase(WektorRozwiazania rozwiazanie) {
        String trasa = "";
        if (macierz.length == rozwiazanie.dlugoscWektora()) {
            for (int i = 0; i < macierz.length; i++) {
                trasa = trasa + miasta[rozwiazanie.pobierzMiasto(i)] + "-";
            }
            trasa = trasa + miasta[0];
        }
        return trasa;
    }
}
