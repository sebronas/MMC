package com.example.mmc;

import com.example.mmc.Model.StudentEntity;
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

import java.sql.DriverManager;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

/** "Music Master's Companion" JavaFX Controller class
 *  @author Kristaps Sebris, Elena Bebrisa, Georgijs Kadolciks
 *  @version 19th July 2022
 */

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

    // Declares and instantiates TreeSet fields for automatic sorting and filtering distinct data
    private final TreeSet<String> studentSet = new TreeSet<>();
    private final TreeSet<String> gradeSet = new TreeSet<>();
    private final TreeSet<String> instrumentSet = new TreeSet<>();
    private final TreeSet<String> teacherSet = new TreeSet<>();
    private final TreeSet<String> accompanistSet = new TreeSet<>();

    // Declares and instantiates ObservableList fields for storing ListView data
    private final ObservableList<String> studentObservList = FXCollections.observableArrayList();
    private final ObservableList<String> gradeObservList = FXCollections.observableArrayList();
    private final ObservableList<String> instrumentObservList = FXCollections.observableArrayList();
    private final ObservableList<String> teacherObservList = FXCollections.observableArrayList();
    private final ObservableList<String> accompanistObservList = FXCollections.observableArrayList();

    // Declares and instantiates ObservableList fields for storing user's selected ListView data
    private final ObservableList<String> selectedStudentList = FXCollections.observableArrayList();
    private final ObservableList<String> selectedGradeList = FXCollections.observableArrayList();
    private final ObservableList<String> selectedInstrumentList = FXCollections.observableArrayList();
    private final ObservableList<String> selectedTeacherList = FXCollections.observableArrayList();
    private final ObservableList<String> selectedAccompanistList = FXCollections.observableArrayList();

    // Declares a List for storing all data
    private List<StudentEntity> allStudents;

    /** Initializes Controller
     */
    public void initialize() {

        // Opens a Hibernate session, loads all data and closes the session
        Session session = HibernateUtil.getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(StudentEntity.class);
        Root<StudentEntity> root = query.from(StudentEntity.class);
        allStudents = session.createQuery(query).getResultList();
        session.close();

        // Associates ListViews with ObservableLists
        studentList.setItems(studentObservList);
        gradeList.setItems(gradeObservList);
        instrumentList.setItems(instrumentObservList);
        teacherList.setItems(teacherObservList);
        accompanistList.setItems(accompanistObservList);

        // Sets multiple selection mode for the ListViews
        studentList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        gradeList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        instrumentList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        teacherList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        accompanistList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    /** Shows Image for the GUI
     */
    @FXML
    private void showImage() {
        Image image = new Image("Icon.jpg");
        myImageView.setImage(image);
        myImageView.setCache(true);
    }

    /** Processes user's request for selected data, or shows all data if no selection was made
     */
    @FXML
    private void onRequestOrReloadAction(ActionEvent event) {

        // Loads user's selections into ObservableList fields
        selectedStudentList.setAll(studentList.getSelectionModel().getSelectedItems());
        selectedGradeList.setAll(gradeList.getSelectionModel().getSelectedItems());
        selectedInstrumentList.setAll(instrumentList.getSelectionModel().getSelectedItems());
        selectedTeacherList.setAll(teacherList.getSelectionModel().getSelectedItems());
        selectedAccompanistList.setAll(accompanistList.getSelectionModel().getSelectedItems());

        // Uses streams for filtering data based on user's selections, and stores results in another list
        List<StudentEntity> filteredStudents = allStudents.stream()
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

        // Resets TreeSets
        studentSet.clear();
        gradeSet.clear();
        instrumentSet.clear();
        teacherSet.clear();
        accompanistSet.clear();

        // Makes filtered data ordered and distinct by adding them to TreeSets
        for (StudentEntity st : filteredStudents) {
            studentSet.add(st.getStudent());
            gradeSet.add(st.getGrade());
            instrumentSet.add(st.getInstrumentId().getInstrument());
            teacherSet.add(st.getTeacherId().getTeacher());
            accompanistSet.add(st.getAccompanistId().getAccompanist());
        }

        // Populates ObservableLists with filtered, ordered, and distinct data from the TreeSets
        studentObservList.setAll(studentSet);
        gradeObservList.setAll(gradeSet);
        instrumentObservList.setAll(instrumentSet);
        teacherObservList.setAll(teacherSet);
        accompanistObservList.setAll(accompanistSet);
    }

    /** Adds students' list or user's selections to the editable text area
     */
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

    /** Sends the contents of the text area to printer
     */
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