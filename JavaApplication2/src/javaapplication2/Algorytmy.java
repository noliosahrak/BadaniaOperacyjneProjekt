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
public class Algorytmy {
    public static WektorRozwiazania optymalizacja2opt(MacierzOdleglosci macierz, WektorRozwiazania wektor) {
        WektorRozwiazania najlepszy = wektor;
        WektorRozwiazania nowyWektor;
        int trasa = macierz.obliczTrase(wektor);
        int nowaTrasa;
        
        for (int a = 0; a < wektor.dlugoscWektora() - 2; a++) {
            for (int b = a + 2; b < wektor.dlugoscWektora(); b++) {
                nowyWektor = wektor.mod2opt(a, b);
                nowaTrasa = macierz.obliczTrase(nowyWektor);
                if (nowaTrasa < trasa) {
                    najlepszy = nowyWektor;
                    trasa = nowaTrasa;
                }
            }
        }
        return najlepszy;
    }
    
    public  static WektorRozwiazania minimumLokalane2opt(MacierzOdleglosci macierz, WektorRozwiazania wektor) {
        WektorRozwiazania minimum = wektor;
        int trasa;
        int nowaTrasa = macierz.obliczTrase(wektor);
        do {
            trasa = nowaTrasa;
            minimum = optymalizacja2opt(macierz,minimum);
            nowaTrasa = macierz.obliczTrase(minimum);
        } while(nowaTrasa < trasa);
        return minimum;
    }
    
    public void AlgorytmGenetyczny(MacierzOdleglosci macierz, int liczebnoscPopulacji, 
            int szansaMutacji, boolean warunekStopu, int liczbaIteracji) {
        int licznikIteracji = 0;
        int najlepszyWynik;
        int srednia;
        //Utworzenie populacji
        Populacja populacja = new Populacja(liczebnoscPopulacji, macierz);
        populacja.losujPopulacje();
        //Ocena jakości
        populacja.sortujPopulacje();
        do {
            srednia = populacja.sredniaWartosc();
            najlepszyWynik = populacja.wezNajlepszyWynik();
            //Krzyżowanie i mutacje
            populacja.krzyzowanie(liczebnoscPopulacji);
            populacja.mutuj(szansaMutacji);
            //Lokalna optymalizacja
            populacja.lokalnaOptymalizacja();
            //Ocena jakości
            populacja.sortujPopulacje();
            //Selekcja
            populacja.selekcja(liczebnoscPopulacji);
            licznikIteracji++;
        } 
        //Warunek stopu
        while (!sprawdzWarunekStopu(warunekStopu,licznikIteracji,liczbaIteracji,srednia,najlepszyWynik,populacja));
        //Podanie wyniku
        populacja.szukanieMinLokalnych();
        populacja.selekcja(1);
        populacja.wypiszPopulacje();
    }

    private boolean sprawdzWarunekStopu(boolean warunekStopu, int licznikIteracji, int liczbaIteracji, int srednia, int najlepszyWynik, Populacja populacja) {
        if (warunekStopu) {
            if (licznikIteracji < liczbaIteracji) return false;
            else return true;
        } else {
            if (populacja.sredniaWartosc() < srednia || populacja.wezNajlepszyWynik() < najlepszyWynik) return false;
            else return true;
        }
    }
    
}
