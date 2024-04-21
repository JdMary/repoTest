package dealdrop.app;

import dealdrop.manager.UIManager;
import dealdrop.util.MyDataBase;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class DealDropApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        MyDataBase.testConnection();
        UIManager ui = UIManager.create(stage);
        UIManager.initialize();
        UIManager.show();
    }

    public static void main(String[] args) {
        launch();
    }
}