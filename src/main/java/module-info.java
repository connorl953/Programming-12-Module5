module com.example.module5 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires java.sql;

    opens com.connor.module5 to javafx.fxml;
    exports com.connor.module5;
}