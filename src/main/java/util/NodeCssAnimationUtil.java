package util;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class NodeCssAnimationUtil {

    public static void fadeGradiantAnimation(Node node, double duration, Color color1, Color color2, double endOpacity1, double endOpacity2) {
        Timeline radiantAnimation = (Timeline) node.getProperties().get(AnimationType.GRADIENT);
        if( radiantAnimation != null) return;
        SimpleDoubleProperty opacity1 = new SimpleDoubleProperty();
        SimpleDoubleProperty opacity2 = new SimpleDoubleProperty();

        opacity1.set(color1.getOpacity());
        opacity2.set(color2.getOpacity());
        opacity1.addListener((observable, oldValue, newValue) -> {
            node.setStyle("-fx-background-color: radial-gradient(focus-distance 0%, center 50% 50%, radius 55%, "
                    + "rgba(" + (int) (color1.getRed() * 255) + ", " + (int) (color1.getGreen() * 255) + ", " + (int) (color1.getBlue() * 255) + ", " + String.format("%.4f", opacity1.get()) + "), "
                    + "rgba(" + (int) (color2.getRed() * 255) + ", " + (int) (color2.getGreen() * 255) + ", " + (int) (color2.getBlue() * 255) + ", " + String.format("%.4f", opacity2.get()) + ")"
                    + ");");
        });

        Timeline timeline1 = new Timeline(new KeyFrame(Duration.seconds(duration), new KeyValue(opacity1, endOpacity1)));
        timeline1.play();

        timeline1.setOnFinished(e -> node.getProperties().remove(AnimationType.GRADIENT));
        Timeline timeline2 = new Timeline(new KeyFrame(Duration.seconds(duration), new KeyValue(opacity2, endOpacity2)));
        timeline2.play();

        node.getProperties().put(AnimationType.GRADIENT, new Timeline[]{timeline1, timeline2});

    }

    public static void stopFadeGradiantAnimation(Node node){
        Timeline[] timelines = (Timeline[]) node.getProperties().get(AnimationType.GRADIENT);
        if(timelines != null){
            timelines[0].stop();
            timelines[1].stop();
            node.getProperties().remove(AnimationType.GRADIENT);
        }
    }

}
