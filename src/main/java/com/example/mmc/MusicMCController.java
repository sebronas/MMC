package com.example.mmc;

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
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MusicMCController {

    @FXML
    private ImageView myImageView;

    @FXML
    private TextArea textArea;

    @FXML
    public ListView studentList;
    ObservableList<StudentsEntity> studentObservList;

    @FXML
    public ListView teacherList;
    ObservableList<TeachersEntity> teacherObservList;

    @FXML
    public ListView instrumentList;
    ObservableList<InstrumentsEntity> instrumentObservList;

    @FXML
    public ListView gradeList;
    ObservableList<StudentsEntity> gradeObservList;

    @FXML
    public ListView accompanistList;
    ObservableList<AccompanistEntity> accompanistObservList;

    public void initialize() {
        StudentDAO dao = new StudentDAO();
        studentObservList = dao.getAll();
        gradeObservList = dao.getGrades();
        studentList.setItems(studentObservList);
        gradeList.setItems(gradeObservList);

        InstrumentDAO daoInst = new InstrumentDAO();
        instrumentObservList = daoInst.getAll();
        instrumentList.setItems(instrumentObservList);

        TeacherDAO daoTeach = new TeacherDAO();
        teacherObservList = daoTeach.getAll();
        teacherList.setItems(teacherObservList);

        AccompanistDAO daoAcomp = new AccompanistDAO();
        accompanistObservList = daoAcomp.getAll();
        accompanistList.setItems(accompanistObservList);
    }

    @FXML
    private void showImage() {
        Image image = new Image("Icon.jpg");
        myImageView.setImage(image);
        myImageView.setCache(true);
    }

    @FXML
    private void onRequestOrReloadAction(ActionEvent event) {

    }

    @FXML
    private void onListAddAction(ActionEvent event) {

    }
}