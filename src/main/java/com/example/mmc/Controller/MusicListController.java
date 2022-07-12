package com.example.mmc.Controller;

import com.example.mmc.DAO.AccompanistDAO;
import com.example.mmc.DAO.InstrumentDAO;
import com.example.mmc.DAO.StudentDAO;
import com.example.mmc.DAO.TeacherDAO;
import com.example.mmc.Model.AccompanistEntity;
import com.example.mmc.Model.InstrumentsEntity;
import com.example.mmc.Model.StudentsEntity;
import com.example.mmc.Model.TeachersEntity;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
public class MusicListController {

    @FXML
    public ListView listStudent;
    ObservableList<StudentsEntity> studentList;

    @FXML
    public ListView listTeachers;
    ObservableList<TeachersEntity> teachersList;

    @FXML
    public ListView listInstruments;
    ObservableList<InstrumentsEntity> instrumentsList;

    @FXML
    public ListView listGrades;
    ObservableList<StudentsEntity> gradesList;

    @FXML
    public ListView listAccompanists;
    ObservableList<AccompanistEntity> accompanistList;


    @FXML
    void switchToHelloView(ActionEvent event) {

    }

    @FXML
    public TableView tableItem;


    public void initialize() {
        StudentDAO dao = new StudentDAO();
        studentList = dao.getAll();
        gradesList = dao.getGrades();
        listStudent.setItems(studentList);
        listGrades.setItems(gradesList);

        InstrumentDAO daoInst = new InstrumentDAO();
        instrumentsList = daoInst.getAll();
        listInstruments.setItems(instrumentsList);

        TeacherDAO daoTeach = new TeacherDAO();
        teachersList = daoTeach.getAll();
        listTeachers.setItems(teachersList);

        AccompanistDAO daoAcomp = new AccompanistDAO();
        accompanistList = daoAcomp.getAll();
        listAccompanists.setItems(accompanistList);


    }
}