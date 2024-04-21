package dealdrop.controller;

import dealdrop.manager.UIManager;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.io.IOException;
import javafx.util.Duration;

public class MainPageController {


    @FXML
    private VBox nav;
    @FXML
    private Label profileLABEL;
    @FXML
    void onB1Click() throws IOException {
        UIManager.getOuterPage("principal").swapInnerPage("content1");
    }

    @FXML
    void onB2Click() throws IOException {
        UIManager.getOuterPage("principal").swapInnerPage("content2");
    }
    @FXML
    public void translate(MouseEvent event) {
        System.out.println("hello");

        Node sourceNode = (Node) event.getSource();
        int index = nav.getChildren().indexOf(sourceNode);

        // Get the next element after the source node
        Pane nextElement = null;
        if (index != -1 && index < nav.getChildren().size() - 1) {
            Node nextNode = nav.getChildren().get(index + 1);
            if (nextNode instanceof Pane) {
                nextElement = (Pane) nextNode;
            }
        }

        if (nextElement != null) {
            Timeline timeline = new Timeline();
            KeyValue keyValue = new KeyValue(nextElement.prefHeightProperty(), 300); // Final height
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), keyValue);
            timeline.getKeyFrames().add(keyFrame);
            timeline.play();
        } else {
            System.out.println("No next element found.");
        }
    }

    public void redirectProfile(MouseEvent mouseEvent) {
        UIManager.getOuterPage("principal").swapInnerPage("profile");
    }
}