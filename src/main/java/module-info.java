module com.example.dealdrop {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires jbcrypt;

    opens dealdrop.controller to javafx.fxml;
    exports dealdrop.controller;
    exports dealdrop.app;
    opens dealdrop.app to javafx.fxml;
}