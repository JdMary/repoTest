package util;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;
import javafx.scene.layout.Region;


public class NodePropertyUtil {


    public static DoubleProperty getNodeWidthProperty(Node node) {
        DoubleProperty widthProperty = new SimpleDoubleProperty();
        if (node instanceof Region) {
            widthProperty.bindBidirectional(((Region) node).prefWidthProperty());
        }
        return widthProperty;
    }

    public static DoubleProperty getNodeHeightProperty(Node node) {
        DoubleProperty heightProperty = new SimpleDoubleProperty();
        if (node instanceof Region) {
            heightProperty.bindBidirectional(((Region) node).prefHeightProperty());
        }
        return heightProperty;
    }
}
