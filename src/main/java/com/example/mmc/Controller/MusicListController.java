package com.example.mmc.Controller;

import com.example.mmc.DAO.StudentDAO;
import com.example.mmc.Model.StudentsEntity;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;

public class MusicListController {

    @FXML
    void switchToHelloView(ActionEvent event) {

    }

    @FXML
    public ListView listStudent;

    @FXML
    public TableView tableItem;
    ObservableList<StudentsEntity> studentList;

    public void initialize() {
        StudentDAO dao = new StudentDAO();
        studentList = dao.getAll();
        listStudent.setItems(studentList);
    }
}