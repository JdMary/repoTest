package util;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.sql.Time;

public class NodeCssAnimationUtil {
    public static String modifyStyleProperty(String style, String property, String value) {
        // Remove any existing occurrence of the property
        style = style.replaceAll("(?i)" + property + "\\s*:\\s*[^;]*;?", "");

        // Append the new property
        style += property + ": " + value + ";";
        return style;
    }
    public static String getStylePropertyValue(String style, String property){
        if (style != null && !style.isEmpty()) {
            String[] stylePairs = style.split(";");
            for (String pair : stylePairs) {
                String[] keyValue = pair.split(":");
                if (keyValue.length == 2 && keyValue[0].trim().equals(property.trim())) {
                    return keyValue[1].trim();
                }
            }
        }
        return null;
    }

    public static String removeCssProperty(String style, String propertyName) {
        StringBuilder result = new StringBuilder();

        String[] declarations = style.split(";");

        for (String declaration : declarations) {
            String[] parts = declaration.split(":");

            if (parts.length == 2 && parts[0].trim().equals(propertyName.trim())) {
                continue;
            }

            // Otherwise, append this property to the result string
            result.append(declaration.trim()).append(";");
        }



        return result.toString();
    }
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
        timeline1.setCycleCount(1);
        timeline1.play();
        timeline1.setOnFinished(e -> node.getProperties().remove(AnimationType.GRADIENT));

        Timeline timeline2 = new Timeline(new KeyFrame(Duration.seconds(duration), new KeyValue(opacity2, endOpacity2)));
        timeline2.setCycleCount(1);
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

    public static void changeColor(Node node, CSSProperty property, Color color, double startingOpacity, double duration, Interpolator easing){
        switch(property){
            case BORDER -> {
                if(duration <=0)
                    node.setStyle(modifyStyleProperty(node.getStyle(), "-fx-border-color", "rgba(%f, %f, %f, %f)".formatted(color.getRed() * 255, color.getGreen() * 255, color.getBlue() * 255, color.getOpacity() * 255)));
                else{
                    SimpleDoubleProperty simpleDoubleProperty = new SimpleDoubleProperty(startingOpacity);
                    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(duration), new KeyValue(simpleDoubleProperty, color.getOpacity())));
                    simpleDoubleProperty.addListener((observable, oldValue, newValue) -> {
                        node.setStyle(modifyStyleProperty(node.getStyle(), "-fx-border-color", "rgba(%f, %f, %f, %f)".formatted(color.getRed() * 255, color.getGreen() * 255, color.getBlue() * 255, simpleDoubleProperty.get())));
                    });
                    timeline.setCycleCount(1);
                    timeline.play();
                    timeline.setOnFinished(actionEvent -> {
                        node.getProperties().remove(CSSProperty.BORDER);
                    });
                    node.getProperties().put(CSSProperty.BORDER, timeline);
                }
            }
        }
    }

    public static void stopChangingColor(Node node, CSSProperty property){
        switch (property){
            case BORDER -> {
                Timeline timeline = (Timeline) node.getProperties().get(CSSProperty.BORDER);
                if(timeline != null) {
                    timeline.stop();
                    node.getProperties().remove(CSSProperty.BORDER);
                }
            }
        }
    }

}