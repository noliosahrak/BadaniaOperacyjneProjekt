/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

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
    
    public String zawartoscPopulacji() {
        String zawartosc = "";
        for (WektorRozwiazania w : wektory) {
            zawartosc += macierz.wypiszTrase(w);
            zawartosc += "\n" + w.pobierzDlugoscTrasy() + "\n";
        }
        return zawartosc;
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

    public void krzyzowanie(int liczbaKrzyzowan) {
        TabelaSzans tabelaSzans = new TabelaSzans(wektory);
        for (int i = 0; i < liczbaKrzyzowan; i++) {
            int[] para = tabelaSzans.losujPare();
            WektorRozwiazania nowyWektor = wektory.get(para[0]).krzyzuj(wektory.get(para[1]));
            nowyWektor.ObliczDlugoscTrasy(macierz);
            wektory.add(nowyWektor);
        }
    }

    public void mutuj(int szansaMutacji) {
        int liczbaMutacji = szansaMutacji * wektory.size() / 1000;
        Random losuj = new Random();
        for (int i = 0; i < liczbaMutacji; i++) {
            int w = losuj.nextInt(wektory.size());
            int a = losuj.nextInt(wektory.get(0).dlugoscWektora());
            int b = losuj.nextInt(wektory.get(0).dlugoscWektora());
            WektorRozwiazania nowy = wektory.get(w);
            nowy.zamien(a, b);
            nowy.ObliczDlugoscTrasy(macierz);
            wektory.set(w, nowy);
        }
    }

    public int wezNajlepszyWynik() {
        return wektory.get(0).pobierzDlugoscTrasy();
    }

    public int sredniaWartosc() {
        int srednia = 0;
        for (WektorRozwiazania w : wektory) {
            srednia += w.pobierzDlugoscTrasy();
        }
        srednia /= wektory.size();
        return srednia;
    }
}
