package dealdrop.util;

import javafx.scene.paint.Color;

public enum Colors {
    TRANSPARENT(new Color(0, 0, 0, 0)),
    TURQUOISE(new Color(0f, 255f/255, 234f/255 , 1f)),
    DARK_BLUE(new Color(21f/255 ,23f/255 ,32f/255 , 1f)),
    DARKER_BLUE(new Color(16f/255, 17f/255, 25f/255 , 1f)),
    FAINT_TURQUOISE(new Color(0f, 255f/255, 234f/255, 0.11f)),
    INVISIBLE_TURQUOISE(new Color(0f, 255f/255, 234f/255, 0f)),
    INVISIBLE_DARKER_BLUE(new Color(16f/255, 17f/255, 25f/255 , 0f));
    private final Color color;
    Colors(Color color) {
        this.color = color;
    }
    public Color getColor() {
        return color;
    }
}
