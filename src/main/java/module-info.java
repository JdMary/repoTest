module com.example.dealdrop {
    requires javafx.controls;
    requires javafx.fxml;


    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires exp4j;
    requires jbcrypt;
    requires java.sql;

    opens controller to javafx.fxml;
    exports controller;
    exports app;
    opens app to javafx.fxml;
}