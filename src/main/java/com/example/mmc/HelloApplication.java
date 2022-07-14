package com.example.mmc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

public class HelloApplication extends Application {

    static Connection connection;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("LIST.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 820, 560);
        stage.setTitle("List");
        stage.setScene(scene);
        stage.show();
        HelloController.openConnection();
        stage.setOnCloseRequest(e -> HelloController.closeConnection());
    }

    public static void main(String[] args) {
        launch();
    }
}