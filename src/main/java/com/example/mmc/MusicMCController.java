package com.example.mmc;

import com.example.mmc.DAO.AccompanistDAO;
import com.example.mmc.DAO.InstrumentDAO;
import com.example.mmc.DAO.StudentDAO;
import com.example.mmc.DAO.TeacherDAO;
import com.example.mmc.Model.AccompanistEntity;
import com.example.mmc.Model.InstrumentEntity;
import com.example.mmc.Model.StudentEntity;
import com.example.mmc.Model.TeacherEntity;
import com.example.mmc.Utility.HibernateUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.print.*;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class MusicMCController {

    @FXML
    private ImageView myImageView;

    @FXML
    private TextArea textArea;

    @FXML
    public ListView studentList;

    @FXML
    public ListView gradeList;

    @FXML
    public ListView instrumentList;

    @FXML
    public ListView teacherList;

    @FXML
    public ListView accompanistList;

    private final TreeSet<String> studentSet = new TreeSet<>();
    private final TreeSet<String> gradeSet = new TreeSet<>();
    private final TreeSet<String> instrumentSet = new TreeSet<>();
    private final TreeSet<String> teacherSet = new TreeSet<>();
    private final TreeSet<String> accompanistSet = new TreeSet<>();

    private final ObservableList<String> studentObservList = FXCollections.observableArrayList();
    private final ObservableList<String> gradeObservList = FXCollections.observableArrayList();
    private final ObservableList<String> instrumentObservList = FXCollections.observableArrayList();
    private final ObservableList<String> teacherObservList = FXCollections.observableArrayList();
    private final ObservableList<String> accompanistObservList = FXCollections.observableArrayList();

    private final ObservableList<String> selectedStudentList = FXCollections.observableArrayList();
    private final ObservableList<String> selectedGradeList = FXCollections.observableArrayList();
    private final ObservableList<String> selectedInstrumentList = FXCollections.observableArrayList();
    private final ObservableList<String> selectedTeacherList = FXCollections.observableArrayList();
    private final ObservableList<String> selectedAccompanistList = FXCollections.observableArrayList();

    private List<StudentEntity> allStudents, filteredStudents;

    public void initialize() {
        Session session = HibernateUtil.getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(StudentEntity.class);
        Root<StudentEntity> root = query.from(StudentEntity.class);
        allStudents = session.createQuery(query).getResultList();
        session.close();
        studentList.setItems(studentObservList);
        gradeList.setItems(gradeObservList);
        instrumentList.setItems(instrumentObservList);
        teacherList.setItems(teacherObservList);
        accompanistList.setItems(accompanistObservList);
        studentList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        gradeList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        instrumentList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        teacherList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        accompanistList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @FXML
    private void showImage() {
        Image image = new Image("Icon.jpg");
        myImageView.setImage(image);
        myImageView.setCache(true);
    }

    @FXML
    private void onRequestOrReloadAction(ActionEvent event) {
        selectedStudentList.setAll(studentList.getSelectionModel().getSelectedItems());
        selectedGradeList.setAll(gradeList.getSelectionModel().getSelectedItems());
        selectedInstrumentList.setAll(instrumentList.getSelectionModel().getSelectedItems());
        selectedTeacherList.setAll(teacherList.getSelectionModel().getSelectedItems());
        selectedAccompanistList.setAll(accompanistList.getSelectionModel().getSelectedItems());
        filteredStudents = allStudents.stream()
                .filter(st -> selectedStudentList.isEmpty()
                        || selectedStudentList.contains(st.getStudent()))
                .filter(st -> selectedGradeList.isEmpty()
                        || selectedGradeList.contains(st.getGrade()))
                .filter(st -> selectedInstrumentList.isEmpty()
                        || selectedInstrumentList.contains(st.getInstrumentId().getInstrument()))
                .filter(st -> selectedTeacherList.isEmpty()
                        || selectedTeacherList.contains(st.getTeacherId().getTeacher()))
                .filter(st -> selectedAccompanistList.isEmpty()
                        || selectedAccompanistList.contains(st.getAccompanistId().getAccompanist()))
                .collect(Collectors.toList());
        studentSet.clear();
        gradeSet.clear();
        instrumentSet.clear();
        teacherSet.clear();
        accompanistSet.clear();
        for (StudentEntity st : filteredStudents) studentSet.add(st.getStudent());
        for (StudentEntity st : filteredStudents) gradeSet.add(st.getGrade());
        for (StudentEntity st : filteredStudents) instrumentSet.add(st.getInstrumentId().getInstrument());
        for (StudentEntity st : filteredStudents) teacherSet.add(st.getTeacherId().getTeacher());
        for (StudentEntity st : filteredStudents) accompanistSet.add(st.getAccompanistId().getAccompanist());
        studentObservList.setAll(studentSet);
        gradeObservList.setAll(gradeSet);
        instrumentObservList.setAll(instrumentSet);
        teacherObservList.setAll(teacherSet);
        accompanistObservList.setAll(accompanistSet);
    }

    @FXML
    private void onListAddAction(ActionEvent event) {
        ObservableList<String> selectedList = FXCollections.observableArrayList();
        selectedList.setAll(studentList.getSelectionModel().getSelectedItems());
        if (!selectedList.isEmpty()) {
            for (String string : selectedList)
                textArea.appendText(string + "\n");
            return;
        }
        if (!studentObservList.isEmpty())
            for (String string : studentObservList)
                textArea.appendText(string + "\n");
    }

    @FXML
    private void onPrintAction(ActionEvent event) {
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null && job.showPrintDialog(textArea.getScene().getWindow())) {
            Printer printer = job.getPrinter();
            PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.HARDWARE_MINIMUM);
            PrintResolution resolution = job.getJobSettings().getPrintResolution();
            boolean success = job.printPage(pageLayout, textArea);
            if (success) job.endJob();
        }
    }
}