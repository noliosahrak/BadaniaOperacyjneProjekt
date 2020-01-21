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
public class WynikIteracji {
    private final int numerIteracji;
    private final int najlepszyWynik;
    private final int srednia;
    private final String trasy;
    private final String najlepszaTrasa;

    public WynikIteracji(Populacja populacja, int numerIteracji) {
        this.numerIteracji = numerIteracji;
        najlepszyWynik = populacja.wezNajlepszyWynik();
        srednia = populacja.sredniaWartosc();
        trasy = populacja.zawartoscPopulacji();
        najlepszaTrasa = populacja.wypiszNajlepszaTrase();
    }
    
    public int getNumerIteracji() {
        return numerIteracji;
    }
    
    public int getNajlepszyWynik() {
        return najlepszyWynik;
    }
    
    public int getSrednia() {
        return srednia;
    }
    
    public String getTrasy() {
        return trasy;
    }
    
    public String getNajlepszaTrase() {
        return najlepszaTrasa;
    }
}
