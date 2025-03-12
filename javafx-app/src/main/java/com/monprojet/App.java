package com.monprojet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Charger le fichier FXML
            VBox root = FXMLLoader.load(getClass().getResource("/interface.fxml"));

            // Créer la scène
            Scene scene = new Scene(root, 300, 200);
            scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

            // Configurer la fenêtre principale
            primaryStage.setTitle("Ma Première Application JavaFX");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erreur lors du chargement du fichier FXML !");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}