package com.example.mmc.controller;

import com.example.mmc.dao.StudentDAO;

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

import java.util.ArrayList;
import java.util.List;

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

    // Declares and instantiates ObservableList fields for storing list view data
    private final ObservableList<String> studentObservList = FXCollections.observableArrayList();
    private final ObservableList<String> gradeObservList = FXCollections.observableArrayList();
    private final ObservableList<String> instrumentObservList = FXCollections.observableArrayList();
    private final ObservableList<String> teacherObservList = FXCollections.observableArrayList();
    private final ObservableList<String> accompanistObservList = FXCollections.observableArrayList();

    // Instantiates a StudentDAO for data access
    StudentDAO studentDAO = new StudentDAO();

    public void initialize() {

        // Associates list views with observable lists
        studentList.setItems(studentObservList);
        gradeList.setItems(gradeObservList);
        instrumentList.setItems(instrumentObservList);
        teacherList.setItems(teacherObservList);
        accompanistList.setItems(accompanistObservList);

        // Sets multiple selection mode for the list views
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

        // Filters data on user's selection
        studentDAO.filterData(
                studentList.getSelectionModel().getSelectedItems(),
                gradeList.getSelectionModel().getSelectedItems(),
                instrumentList.getSelectionModel().getSelectedItems(),
                teacherList.getSelectionModel().getSelectedItems(),
                accompanistList.getSelectionModel().getSelectedItems());

        // Populates observable lists with filtered, ordered, and distinct data
        studentObservList.setAll(studentDAO.getStudents());
        gradeObservList.setAll(studentDAO.getGrades());
        instrumentObservList.setAll(studentDAO.getInstruments());
        teacherObservList.setAll(studentDAO.getTeachers());
        accompanistObservList.setAll(studentDAO.getAccompanists());
    }

    /** Adds students' list or user's selection to the editable text area
     */
    @FXML
    private void onListAddAction(ActionEvent event) {
        List<String> selectedStudents = new ArrayList<>(studentList.getSelectionModel().getSelectedItems());
        if (!selectedStudents.isEmpty()) {
            for (String string : selectedStudents)
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