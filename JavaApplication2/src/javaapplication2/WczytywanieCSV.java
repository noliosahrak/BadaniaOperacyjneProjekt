/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author Patrycja
 */
public class WczytywanieCSV {

    public static MacierzOdleglosci wczytajMacierzOdleglosciCSV(String nazwaPliku) {
        String[] miasta;
        int[][] macierz = null;
        Path sciezkaDoPliku = Paths.get(nazwaPliku);

        try (BufferedReader br1 = Files.newBufferedReader(sciezkaDoPliku, StandardCharsets.ISO_8859_1)) {
            int wymiarMacierzy = (int) (br1.lines().count() - 1);
            macierz = new int[wymiarMacierzy][wymiarMacierzy];
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
         
        try (BufferedReader br2 = Files.newBufferedReader(sciezkaDoPliku, StandardCharsets.ISO_8859_1)) {
            String linia = br2.readLine();
            miasta = linia.split(",");

            linia = br2.readLine();
            int wiersz = 0;
            while (linia != null) { 
                String[] wartosc = linia.split(",");
                for (int i = 0; i < wartosc.length; i++) {
                    macierz[wiersz][i] = Integer.parseInt(wartosc[i]);
                }
                linia = br2.readLine();
                wiersz++;
            }
            return new MacierzOdleglosci(macierz,miasta);
            
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return null;
    }
}
