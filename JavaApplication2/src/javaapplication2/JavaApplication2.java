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
public class JavaApplication2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        /*WektorRozwiazania wektor1 = new WektorRozwiazania(3);
        WektorRozwiazania wektor2 = new WektorRozwiazania(10);
        int[] test = {2,3,5,7};
        WektorRozwiazania wektor3 = new WektorRozwiazania(test);*/
        WektorRozwiazania wektor4 = new WektorRozwiazania(10);
        wektor4.losujRozwiazanie();
        
        /*wektor1.wypiszRozwiazanie();
        wektor2.wypiszRozwiazanie();
        wektor3.wypiszRozwiazanie(); */
        wektor4.wypiszRozwiazanie();
        
        int[][] macierz = new int[4][4];
        MacierzOdleglosci mo = new MacierzOdleglosci(macierz);
    }
    
}
