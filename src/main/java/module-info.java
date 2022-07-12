module com.example.mmc {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.persistence;
    requires mysql.connector.java;
    requires java.sql;

    opens com.example.mmc to javafx.fxml;
    opens com.example.mmc.Controller to javafx.fxml;
    opens com.example.mmc.Model to org.hibernate.orm.core;
    exports com.example.mmc;
}