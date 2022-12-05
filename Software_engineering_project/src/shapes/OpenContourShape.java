package shapes;

import javafx.scene.paint.Color;

public abstract class OpenContourShape extends ShapeAbstract {

    /**
     * Sets a new internal color for a shape
     *
     * @param newColor the new color to set
     */
    @Override
    public void setInternalColor(Color newColor) {
        shape.setFill(Color.TRANSPARENT);
    }
}
