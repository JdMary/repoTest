package dealdrop.page;

import dealdrop.app.DealDropApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class InnerPage<T> {
    protected Parent root;
    protected String name;

    public Parent getRoot() {
        return root;
    }

    public void setRoot(Parent root) {
        this.root = root;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getController() {
        return controller;
    }

    public void setController(T controller) {
        this.controller = controller;
    }

    protected T controller;

    public InnerPage(){}

    public InnerPage(String fxmlPath, String name) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DealDropApplication.class.getResource(fxmlPath));
        this.root = fxmlLoader.load();
        this.name = name;
        this.controller = fxmlLoader.getController();
    }
}
