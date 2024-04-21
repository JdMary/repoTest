package dealdrop.controller;

import dealdrop.manager.UIManager;
import dealdrop.util.*;
import javafx.animation.Interpolator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {

    private boolean menuVisible = true;
    @FXML
    private AnchorPane menu;
    @FXML
    private Separator topseparator;

    @FXML
    private AnchorPane container;
    @FXML
    private StackPane content;

    @FXML
    private VBox iconReference;
    @FXML
    private VBox menucontainer;
    @FXML
    private ImageView showhidemenu;

    private double menuSize = 184;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
    }
    @FXML
    void onB1Click() throws IOException {
        UIManager.getOuterPage("principal").swapInnerPage("content1");
    }

    @FXML
    void onB2Click() throws IOException {
        UIManager.getOuterPage("principal").swapInnerPage("content2");
    }

    @FXML
    public void showHideMenu(){
        double menuWidth = menuSize * 2 / 6;
        double iconTransition = menuWidth / 4;
        if(iconReference.getProperties().get(AnimationType.ROTATE) == null) {
            if (menuVisible) {
                NodeAnimationUtil.resizeWidth(menu, menuWidth, 0.8);

                NodeAnimationUtil.rotate(showhidemenu, 180, 0.2, RotationReset.KEEP, Interpolator.EASE_BOTH);

                menucontainer.getChildren().forEach((Node node) -> {
                    VBox vBox = (VBox) node;
                    VBox icon = (VBox) ((HBox) (vBox.getChildren().get(0))).getChildren().get(0);
                    Label label = (Label) ((HBox) (vBox.getChildren().get(0))).getChildren().get(1);
                    NodeAnimationUtil.rotate(icon, -60, 0.8, RotationReset.REVERSE, Interpolator.EASE_BOTH);
                    NodeAnimationUtil.translateX(icon, iconTransition, 0.6, Interpolator.EASE_BOTH);
                    NodeAnimationUtil.fadeInOut(label, 0, 0.1, Interpolator.LINEAR);
                });

                menuVisible = false;

            } else {
                NodeAnimationUtil.resizeWidth(menu, menuSize, 0.8);
                NodeAnimationUtil.rotate(showhidemenu, -180, 0.2, RotationReset.KEEP, Interpolator.EASE_BOTH);

                menucontainer.getChildren().forEach((Node node) -> {
                    VBox vBox = (VBox) node;
                    VBox icon = (VBox) ((HBox) (vBox.getChildren().get(0))).getChildren().get(0);
                    Label label = (Label) ((HBox) (vBox.getChildren().get(0))).getChildren().get(1);
                    NodeAnimationUtil.rotate(icon, -60, 0.8, RotationReset.REVERSE, Interpolator.EASE_BOTH);
                    NodeAnimationUtil.translateX(icon, -iconTransition, 0.6, Interpolator.EASE_BOTH);
                    NodeAnimationUtil.fadeInOut(label, 1, 1, Interpolator.EASE_IN);
                });

                menuVisible = true;

            }
        }

    }
    public void hoverIcon(MouseEvent event){
        Node icon = ((HBox)event.getSource()).getChildren().get(0);
        NodeCssAnimationUtil.stopFadeGradiantAnimation(icon);
        NodeCssAnimationUtil.fadeGradiantAnimation(icon,0.2, Colors.INVISIBLE_TURQUOISE.getColor(), Colors.INVISIBLE_DARKER_BLUE.getColor(), 0.11, 0);

        NodeAnimationUtil.stopOscillating(icon, false);
        NodeAnimationUtil.oscillate(icon, 2.5, -1, -6, 6, 0.25);
        NodeAnimationUtil.jumpAnimation(icon, "10*exp(-0.6*x)", 1, 0, -1, 3.5, AnimationAcceleration.SLOWING);
    }
    public void unHoverIcon(MouseEvent event){
        Node icon = ((HBox)event.getSource()).getChildren().get(0);

        NodeCssAnimationUtil.stopFadeGradiantAnimation(icon);
        NodeCssAnimationUtil.fadeGradiantAnimation(icon,0.2, Colors.FAINT_TURQUOISE.getColor(), Colors.TRANSPARENT.getColor(), 0, 0);

        NodeAnimationUtil.stopOscillating(icon, true);
        NodeAnimationUtil.stopJumping(icon, true, 1);
    }
    @FXML
    public void expandShrinkNavElement(MouseEvent event){
        Node source = (Node) event.getSource();
        VBox parent = (VBox) source.getParent();
        VBox navElement = (VBox) parent.getChildren().get(1);
        Boolean navElementVisible = (Boolean) navElement.getProperties().get("visible");
        if(navElementVisible == null || navElementVisible){
            navElement.getProperties().put("visible", false);
            NodeAnimationUtil.scaleY(navElement, 1, 0.3, YScalePivot.TOP, Interpolator.EASE_OUT);
        }else{
            navElement.getProperties().put("visible", true);
            NodeAnimationUtil.scaleY(navElement, 0, 0.3, YScalePivot.TOP, Interpolator.EASE_OUT);
        }

    }

}