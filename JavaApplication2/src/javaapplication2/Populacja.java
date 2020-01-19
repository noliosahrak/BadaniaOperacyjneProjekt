/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Patrycja
 */
public class Populacja {
    ArrayList<WektorRozwiazania> wektory;
    MacierzOdleglosci macierz;
    
    public Populacja(int liczebnosc, MacierzOdleglosci macierz) {
        this.macierz = macierz;
        wektory = new ArrayList<>();
        wektory.add(new WektorRozwiazania(macierz.wymiarMacierzy()));
        for (int i = 1; i < liczebnosc; i++) {
            wektory.add(new WektorRozwiazania(macierz.wymiarMacierzy()));
        }
    }
    
    public void losujPopulacje() {
        for (WektorRozwiazania w : wektory) {
            w.losujRozwiazanie();
            w.ObliczDlugoscTrasy(macierz);
        }
    }
    
    public void sortujPopulacje() {
        Collections.sort(wektory);
    }
    
    public void wypiszPopulacje() {
        for (WektorRozwiazania w : wektory) {
            w.wypiszRozwiazanie();
        }
    }
    /**
     * Wczesniej należy posortować populację
     * @param liczebnosc Tyle osobników zostanie w populacji
     */
    public void selekcja(int liczebnosc) {
        while (wektory.size() > liczebnosc) {
            wektory.remove(wektory.size() - 1);
        }
    }
    
    public void lokalnaOptymalizacja() {
        ArrayList<WektorRozwiazania> noweWektory = new ArrayList<>();
        for (WektorRozwiazania w : wektory) {
            WektorRozwiazania nowyWektor = Algorytmy.optymalizacja2opt(macierz, w);
            nowyWektor.ObliczDlugoscTrasy(macierz);
            noweWektory.add(nowyWektor);  
        }
        wektory = noweWektory;
    }
    
    public void szukanieMinLokalnych() {
        ArrayList<WektorRozwiazania> noweWektory = new ArrayList<>();
        for (WektorRozwiazania w : wektory) {
            WektorRozwiazania nowyWektor = Algorytmy.minimumLokalane2opt(macierz, w);
            nowyWektor.ObliczDlugoscTrasy(macierz);
            noweWektory.add(nowyWektor);  
        }
        wektory = noweWektory;
    }

    void krzyzowanie(int liczebnoscPopulacji) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void mutuj(int szansaMutacji) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    int wezNajlepszyWynik() {
        return wektory.get(0).pobierzDlugoscTrasy();
    }

    int sredniaWartosc() {
        int srednia = 0;
        for (WektorRozwiazania w : wektory) {
            srednia += w.pobierzDlugoscTrasy();
        }
        srednia /= wektory.size();
        return srednia;
    }
}
