module com.example.mmc {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.persistence;
    requires mysql.connector.java;
    requires java.sql;

    opens com.example.mmc to javafx.fxml;
    opens com.example.mmc.model to org.hibernate.orm.core;
    exports com.example.mmc;
    exports com.example.mmc.controller;
    opens com.example.mmc.controller to javafx.fxml;
}