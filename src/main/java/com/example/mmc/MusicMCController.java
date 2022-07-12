package com.example.mmc;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class MusicMCController {
    @FXML
    private Button myButton;
    @FXML
    private Label welcomeText;

    private Stage stage;
    private Scene scene;
    private FXMLLoader root;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void switchToHelloView(ActionEvent event) throws IOException {
        root = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        scene = new Scene(root.load());
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void switchToLIST(ActionEvent event) throws IOException {
        root = new FXMLLoader(getClass().getResource("LIST.fxml"));
        scene = new Scene(root.load());
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


}