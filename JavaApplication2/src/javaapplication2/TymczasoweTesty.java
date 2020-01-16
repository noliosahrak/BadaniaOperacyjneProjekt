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
public class TymczasoweTesty {
    public static void testy() {
        
        //WektorRozwiazania wektor1 = new WektorRozwiazania(5);
        //WektorRozwiazania wektor2 = new WektorRozwiazania(10);
        
        //WektorRozwiazania wektor4 = new WektorRozwiazania(10);
        //wektor4.losujRozwiazanie();
        //wektor4.wypiszRozwiazanie();
        
        //wektor1.wypiszRozwiazanie();
        //wektor2.wypiszRozwiazanie();
        //wektor3.wypiszRozwiazanie(); 
        //wektor4.wypiszRozwiazanie();
        
        /*int[][] macierz = {{0,   341, 584, 486, 304},
                           {341, 0,   299, 341, 304},
                           {584, 299, 0,   304, 403},
                           {486, 341, 304, 0,   280},
                           {304, 304, 403, 280, 0}};
        String[] miasta = {"Gdańsk","Warszawa","Kraków","Wrocław","Poznań"};*/
        MacierzOdleglosci mo = WczytywanieCSV.wczytajMacierzOdleglosciCSV("Macierz3.csv");
        /*System.out.println(mo.wypiszTrase(wektor1));
        System.out.println(mo.obliczTrase(wektor1));  
        wektor1.losujRozwiazanie();
        System.out.println(mo.wypiszTrase(wektor1));
        System.out.println(mo.obliczTrase(wektor1));
        wektor1.losujRozwiazanie();
        System.out.println(mo.wypiszTrase(wektor1));
        System.out.println(mo.obliczTrase(wektor1));
        wektor1.losujRozwiazanie();
        System.out.println(mo.wypiszTrase(wektor1));
        System.out.println(mo.obliczTrase(wektor1));
        wektor1.losujRozwiazanie();
        System.out.println(mo.wypiszTrase(wektor1));
        System.out.println(mo.obliczTrase(wektor1));*/
        
        //System.out.println(mo.obliczTrase(wektor1));
        //WektorRozwiazania w1 = wektor1.mod2opt(2, 4);
        //w1.wypiszRozwiazanie();
        //System.out.println(mo.obliczTrase(w1));
        //WektorRozwiazania w2 = wektor1.mod2opt(0, 4);
        //w2.wypiszRozwiazanie();
        //System.out.println(mo.obliczTrase(w2));
        WektorRozwiazania w3 = new WektorRozwiazania(mo.wymiarMacierzy());
        w3.losujRozwiazanie();
        System.out.println(mo.wypiszTrase(w3));
        w3.wypiszRozwiazanie();
        System.out.println(mo.obliczTrase(w3));
        
        WektorRozwiazania w5 = Algorytmy.optymalizacja2opt(mo, w3);
        System.out.println(mo.wypiszTrase(w5));
        w5.wypiszRozwiazanie();
        System.out.println(mo.obliczTrase(w5));
        
        WektorRozwiazania w4 = Algorytmy.minimumLokalane2opt(mo, w3);
        System.out.println(mo.wypiszTrase(w4));
        w4.wypiszRozwiazanie();
        System.out.println(mo.obliczTrase(w4)); 
    }
}
