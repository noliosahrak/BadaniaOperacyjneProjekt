/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FX;

import java.io.File;
import javaapplication2.MacierzOdleglosci;
import javaapplication2.TymczasoweTesty;
import javaapplication2.WczytywanieCSV;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

/**
 *
 * @author Patrycja
 */
public class Kontroller {
    @FXML
    private AnchorPane anchorPaneStart;

    FileChooser fileChooser = new FileChooser();
    
   
    @FXML
    void wczytajCSV(ActionEvent event) {
        File plik = fileChooser.showOpenDialog(javaapplication2.JavaApplication2.stage);
        if (plik != null) {
            String sciezkaDoPliku = plik.getPath();
            javaapplication2.JavaApplication2.macierz = WczytywanieCSV.wczytajMacierzOdleglosciCSV(sciezkaDoPliku);
            if (javaapplication2.JavaApplication2.macierz.sprawdzPoprawnoscDanych()) {
                javaapplication2.JavaApplication2.wczytanoPoprawnie = true;
                //javaapplication2.JavaApplication2.macierz.sprawdzSymetrieMacierzy();
                TymczasoweTesty.testy();
            } else {
                javaapplication2.JavaApplication2.wczytanoPoprawnie = false;
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
}

