/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FX;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javaapplication2.MacierzOdleglosci;
import javaapplication2.Populacja;
import javaapplication2.TymczasoweTesty;
import javaapplication2.WczytywanieCSV;
import javaapplication2.WynikIteracji;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

/**
 *
 * @author Patrycja
 */
public class Kontroller implements Initializable{

    @FXML
    private AnchorPane anchorPaneStart;
    
    @FXML
    private Tab tabAlGen;
    
    @FXML
    private TextArea AGpopulacja;

    @FXML
    private Label AGlicznikIteracji;

    @FXML
    private Label AGczas;

    @FXML
    private TextArea AGnajlepszy;

    @FXML
    private Label AGlicznikDynamiczny;

    @FXML
    private TextField AGmutacja;

    @FXML
    private ChoiceBox AGwarunek;

    @FXML
    private TextField AGliczbaIteracjiWarunek;
    
    @FXML
    private TextField AGliczebnosc;

    @FXML
    private Tab tabLokOpt;

    @FXML
    private TextField LOliczebnosc;

    @FXML
    private Label LOczas;

    @FXML
    private TextArea LOpopulacjaStartowa;

    @FXML
    private TextArea LOminima;

    @FXML
    private Label LOwynik;

    FileChooser fileChooser = new FileChooser();
    public static MacierzOdleglosci macierz;
    ObservableList<String> listaWarunkowStopu = FXCollections.observableArrayList("Liczba iteracji","Wynik i średnia");
    ArrayList<WynikIteracji> listaWynikow;
    int wskaznikIteracji;
    
    @FXML
    void AGpokazNastepny(ActionEvent event) {
        if (wskaznikIteracji < listaWynikow.size() - 1) {
            wskaznikIteracji++;
            pokazPopulacje(wskaznikIteracji);
        }
    }

    @FXML
    void AGpokazPoprzedni(ActionEvent event) {
        if (wskaznikIteracji > 0) {
            wskaznikIteracji--;
            pokazPopulacje(wskaznikIteracji);
        }
    }

    @FXML
    void algorytmGenetyczny(ActionEvent event) {
        try {
            int szansaMutacji = Integer.parseInt(AGmutacja.getText());
            wskaznikIteracji = 0;
            String warunekStopu = (String) AGwarunek.getValue();
            int maxLiczbaIteracji = Integer.parseInt(AGliczbaIteracjiWarunek.getText());
            long start = System.currentTimeMillis();
            int najlepszyWynik;
            //Utworzenie populacji
            int liczebnoscPopulacji = Integer.parseInt(AGliczebnosc.getText());
            Populacja populacja = new Populacja(liczebnoscPopulacji, macierz);
            populacja.losujPopulacje();
            //Ocena jakości
            populacja.sortujPopulacje();
            najlepszyWynik = populacja.wezNajlepszyWynik();
            listaWynikow = new ArrayList<>();
            listaWynikow.add(new WynikIteracji(populacja,wskaznikIteracji));
            pokazPopulacje(0);
            pokazNajlepszyWynik(0);
            do {
                //Krzyżowanie i mutacje
                populacja.krzyzowanie(liczebnoscPopulacji);
                populacja.mutuj(szansaMutacji);
                //Lokalna optymalizacja
                populacja.lokalnaOptymalizacja();
                //Ocena jakości
                populacja.sortujPopulacje();
                //Selekcja
                populacja.selekcja(liczebnoscPopulacji);
                wskaznikIteracji++;
                listaWynikow.add(new WynikIteracji(populacja,wskaznikIteracji));
                if (populacja.wezNajlepszyWynik() < najlepszyWynik) {
                    najlepszyWynik = populacja.wezNajlepszyWynik();
                    pokazNajlepszyWynik(wskaznikIteracji);
                }
                aktualizujCzas(start);
                AGlicznikDynamiczny.setText("Wykonano " + wskaznikIteracji + " iteracji");
            } 
            //Warunek stopu
            while (!sprawdzWarunekStopu(warunekStopu, maxLiczbaIteracji));
            //Podanie wyniku
            populacja.szukanieMinLokalnych();
            wskaznikIteracji++;
            listaWynikow.add(new WynikIteracji(populacja,wskaznikIteracji));
            if (populacja.wezNajlepszyWynik() < najlepszyWynik) {
                    najlepszyWynik = populacja.wezNajlepszyWynik();
                    pokazNajlepszyWynik(wskaznikIteracji);
                }
            wskaznikIteracji = 0;
        } catch (NumberFormatException e) {
            alertZlyFormat();
        }
    }
    
    private boolean sprawdzWarunekStopu(String warunekStopu, int maxLiczbaIteracji) {
        switch (warunekStopu) {
            case "Liczba iteracji":
                if (wskaznikIteracji < maxLiczbaIteracji) return false;
                else return true;
            case "Wynik i średnia":
                if (listaWynikow.get(wskaznikIteracji).getSrednia() < listaWynikow.get(wskaznikIteracji - 1).getSrednia()
                        || listaWynikow.get(wskaznikIteracji).getNajlepszyWynik() < listaWynikow.get(wskaznikIteracji - 1).getNajlepszyWynik())
                    return false;
                else return true;
            default:
                return true;
        }
    }
    
    @FXML
    void lokalnaOptymalizacja(ActionEvent event) {
        try {
            LOpopulacjaStartowa.clear();
            LOminima.clear();
            int liczebnosc = Integer.parseInt(LOliczebnosc.getText());
            Populacja populacja = new Populacja(liczebnosc, macierz);
            populacja.losujPopulacje();
            populacja.sortujPopulacje();
            LOpopulacjaStartowa.setText(populacja.zawartoscPopulacji());
            long start = System.currentTimeMillis();
            populacja.szukanieMinLokalnych();
            populacja.sortujPopulacje();
            LOminima.setText(populacja.zawartoscPopulacji());
            long stop = System.currentTimeMillis();
            long czas = stop - start;
            long milisek = czas % 1000;
            long sek = (czas / 1000) % 60;
            long min = czas / 60000;
            String ileCzasu = min + " min " + sek + " s " + milisek + " ms";
            LOczas.setText(ileCzasu);
            String najlepszyWynik = String.valueOf(populacja.wezNajlepszyWynik());
            LOwynik.setText(najlepszyWynik);
        } catch (NumberFormatException e) {
            alertZlyFormat();
        }
    }
    
    @FXML
    void wczytajCSV(ActionEvent event) {
        File plik = fileChooser.showOpenDialog(javaapplication2.JavaApplication2.stage);
        if (plik != null) {
            String sciezkaDoPliku = plik.getPath();
            macierz = WczytywanieCSV.wczytajMacierzOdleglosciCSV(sciezkaDoPliku);
            if (macierz.sprawdzPoprawnoscDanych()) {
                javaapplication2.JavaApplication2.wczytanoPoprawnie = true;
                //javaapplication2.JavaApplication2.macierz.sprawdzSymetrieMacierzy();
                tabAlGen.setDisable(false);
                tabLokOpt.setDisable(false);
                //TymczasoweTesty.testy();
            } else {
                javaapplication2.JavaApplication2.wczytanoPoprawnie = false;
                tabAlGen.setDisable(true);
                tabLokOpt.setDisable(true);
                alertZlyFormatDanych();
            }
        }
    }
    
    private void alertZlyFormatDanych() {
        Alert a1 = new Alert(Alert.AlertType.ERROR);
        a1.setTitle("Zły format danych w pliku");
        a1.setContentText(null);
        a1.setHeaderText("W pierwszym wierszu powinny być nazwy miejscowości\n"
                + "Kolejne powinny tworzyć macierz kwadratową \n"
                + "i zawierać odległości miedzy miastami");
        a1.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tabAlGen.setDisable(true);
        tabLokOpt.setDisable(true);
        AGwarunek.setItems(listaWarunkowStopu);
    }

    private void alertZlyFormat() {
        Alert a1 = new Alert(Alert.AlertType.ERROR);
        a1.setTitle("Błąd formatu liczby");
        a1.setContentText(null);
        a1.setHeaderText("Wartość wpisana musi być liczbą całkowitą");
        a1.showAndWait();
    }

    private void aktualizujCzas(long start) {
        long stop = System.currentTimeMillis();
        long czas = stop - start;
        long milisek = czas % 1000;
        long sek = (czas / 1000) % 60;
        long min = czas / 60000;
        String ileCzasu = min + " min " + sek + " s " + milisek + " ms";
        AGczas.setText(ileCzasu);
    }

    private void pokazPopulacje(int numerIteracji) {
        String wyswietl = "Średnia długość trasy w populacji = " + listaWynikow.get(numerIteracji).getSrednia();
        wyswietl = wyswietl + "\n" + listaWynikow.get(numerIteracji).getTrasy();
        AGpopulacja.setText(wyswietl);
        AGlicznikIteracji.setText("Populacja po " + numerIteracji + " iteracji");
    }

    private void pokazNajlepszyWynik(int numerIteracji) {
        String wyswietl = "Najlepszy wynik do tej pory znaleziono po " + numerIteracji + " iteracji";
        wyswietl = wyswietl + "\n" + listaWynikow.get(numerIteracji).getTrasy();
        AGnajlepszy.setText(wyswietl);
    }
}

