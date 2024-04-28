package app;

import manager.UIManager;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class DealDropApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        UIManager ui = UIManager.create(stage);
        UIManager.initialize();
        UIManager.show();
    }

    public static void main(String[] args) {
        launch();
    }
}