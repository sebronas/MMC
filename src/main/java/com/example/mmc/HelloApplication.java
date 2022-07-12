package com.example.mmc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

public class HelloApplication extends Application {

    Connection connection;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("LIST.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 780, 540);
        stage.setTitle("List");
        stage.setScene(scene);
        stage.show();
//        try {
//            connection = DriverManager.getConnection("jdbc:...");
//        }
//        catch (SQLException sqlException) {
//            sqlException.printStackTrace();
//            System.exit(1);
//        }
    }

    public static void main(String[] args) {
        launch();
    }
}