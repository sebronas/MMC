package com.example.mmc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HelloApplication extends Application {

    Connection connection;

    private void closeConnection() {
        try {
            connection.close();
            System.exit(0);
        }
        catch (SQLException sqlException) {
            System.err.println(sqlException.getMessage());
            System.exit(1);
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("LIST.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 780, 540);
        stage.setTitle("List");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e -> closeConnection());
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mmc_db_with_names", "root", "1234");
        }
        catch (SQLException sqlException) {
            System.err.println(sqlException.getMessage());
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}