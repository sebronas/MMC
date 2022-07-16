package com.example.mmc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MusicMC extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MusicMC.class.getResource("MusicMC.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 780, 540);
        stage.setTitle("Music Master's Companion");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}