module com.example.librairie {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires static lombok;
    requires java.sql;

    opens com.example.librairie to javafx.fxml;
    exports com.example.librairie;
}