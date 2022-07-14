package com.example.mmc;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

import java.sql.*;
import java.util.TreeSet;

public class HelloController {

    @FXML
    private ListView studentList;

    @FXML
    private ListView gradeList;

    @FXML
    private ListView instrumentList;

    @FXML
    private ListView teacherList;

    @FXML
    private ListView accompanistList;

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

    public void initialize() {
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
    private void onAction(ActionEvent event) {
        retrieveData(generateQuery());
    }

    static void openConnection() {
        try {
            HelloApplication.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mmc_db", "root", "1234");
        } catch (SQLException sqlException) {
            System.err.println(sqlException.getMessage());
            System.exit(1);
        }
    }

    static void closeConnection() {
        try {
            HelloApplication.connection.close();
            System.exit(0);
        }
        catch (SQLException sqlException) {
            System.err.println(sqlException.getMessage());
            System.exit(1);
        }
    }

    private String generateQuery() {
        selectedStudentList.setAll(studentList.getSelectionModel().getSelectedItems());
        selectedGradeList.setAll(gradeList.getSelectionModel().getSelectedItems());
        selectedInstrumentList.setAll(instrumentList.getSelectionModel().getSelectedItems());
        selectedTeacherList.setAll(teacherList.getSelectionModel().getSelectedItems());
        selectedAccompanistList.setAll(accompanistList.getSelectionModel().getSelectedItems());
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT Student, Grade, Instrument, Teacher, Accompanist FROM students ");
        queryBuilder.append("INNER JOIN instruments ON students.InstrumentID = instruments.InstrumentID ");
        queryBuilder.append("INNER JOIN teachers ON students.TeacherID = teachers.TeacherID ");
        queryBuilder.append("INNER JOIN accompanist ON students.AccompanistID = accompanist.AccompanistID ");
        boolean isFirstFiltering = true;
        if (!selectedStudentList.isEmpty()) {
            queryBuilder.append(isFirstFiltering ? "WHERE " : "AND ");
            isFirstFiltering = false;
            queryBuilder.append("Student IN (");
            for (String string : selectedStudentList) {
                queryBuilder.append("'" + string + "', ");
            }
            queryBuilder.delete(queryBuilder.length() - 2, queryBuilder.length());
            queryBuilder.append(") ");
        }
        if (!selectedGradeList.isEmpty()) {
            queryBuilder.append(isFirstFiltering ? "WHERE " : "AND ");
            isFirstFiltering = false;
            queryBuilder.append("Grade IN (");
            for (String string : selectedGradeList) {
                queryBuilder.append("'" + string + "', ");
            }
            queryBuilder.delete(queryBuilder.length() - 2, queryBuilder.length());
            queryBuilder.append(") ");
        }
        if (!selectedInstrumentList.isEmpty()) {
            queryBuilder.append(isFirstFiltering ? "WHERE " : "AND ");
            isFirstFiltering = false;
            queryBuilder.append("Instrument IN (");
            for (String string : selectedInstrumentList) {
                queryBuilder.append("'" + string + "', ");
            }
            queryBuilder.delete(queryBuilder.length() - 2, queryBuilder.length());
            queryBuilder.append(") ");
        }
        if (!selectedTeacherList.isEmpty()) {
            queryBuilder.append(isFirstFiltering ? "WHERE " : "AND ");
            isFirstFiltering = false;
            queryBuilder.append("Teacher IN (");
            for (String string : selectedTeacherList) {
                queryBuilder.append("'" + string + "', ");
            }
            queryBuilder.delete(queryBuilder.length() - 2, queryBuilder.length());
            queryBuilder.append(") ");
        }
        if (!selectedAccompanistList.isEmpty()) {
            queryBuilder.append(isFirstFiltering ? "WHERE " : "AND ");
            isFirstFiltering = false;
            queryBuilder.append("Accompanist IN (");
            for (String string : selectedAccompanistList) {
                queryBuilder.append("'" + string + "', ");
            }
            queryBuilder.delete(queryBuilder.length() - 2, queryBuilder.length());
            queryBuilder.append(") ");
        }

        // This is the query output, just for testing
        System.out.println(queryBuilder);

        return queryBuilder.toString();
    }

    private void retrieveData (String query) {
        studentSet.clear();
        gradeSet.clear();
        instrumentSet.clear();
        teacherSet.clear();
        accompanistSet.clear();
        try {
            Statement statement = HelloApplication.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                studentSet.add(resultSet.getString("Student"));
                gradeSet.add(resultSet.getString("Grade"));
                instrumentSet.add(resultSet.getString("Instrument"));
                teacherSet.add(resultSet.getString("Teacher"));
                accompanistSet.add(resultSet.getString("Accompanist"));
            }
        }
        catch (SQLException sqlException) {
            System.err.println(sqlException.getMessage());
            System.exit(1);
        }
        studentObservList.setAll(studentSet);
        gradeObservList.setAll(gradeSet);
        instrumentObservList.setAll(instrumentSet);
        teacherObservList.setAll(teacherSet);
        accompanistObservList.setAll(accompanistSet);
    }
}