/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Patrycja
 */
public class JavaApplication2 extends Application{
    
    public static Stage stage;
    public static boolean wczytanoPoprawnie = false;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        launch(args);
    }   

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/FX/Komiwojazer.fxml"));
        AnchorPane anchorPane = loader.load();
        Scene scene = new Scene(anchorPane);
        stage = primaryStage;
        stage.setScene(scene);
        stage.setTitle("Start");
        stage.show(); 
    }
}
