/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FX;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javaapplication2.MacierzOdleglosci;
import javaapplication2.Populacja;
import javaapplication2.TymczasoweTesty;
import javaapplication2.WczytywanieCSV;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
    }

    private void alertZlyFormat() {
        Alert a1 = new Alert(Alert.AlertType.ERROR);
        a1.setTitle("Błąd formatu liczby");
        a1.setContentText(null);
        a1.setHeaderText("Wartość wpisana musi być liczbą całkowitą");
        a1.showAndWait();
    }
}

