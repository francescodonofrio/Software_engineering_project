package shapes;

import javafx.scene.paint.Color;
import java.io.Serializable;
import javafx.scene.shape.Shape;

public abstract class OpenContourShape implements ShapeInterface, Serializable {
    protected Shape shape;

    @Override
    public Shape getShape() {
        return this.shape;
    }

    /**
     * Sets a new color for a shape
     *
     * @param newColor the new color to set
     */
    @Override
    public void setInternalColor(Color newColor) {
    }

    /**
     * Sets the X coordinate for this shape
     *
     * @param X the new X coordinate
     */
    @Override
    public void setX(double X) {
        shape.setLayoutX(X);
    }

    /**
     * Sets the Y coordinate for this shape
     *
     * @param Y the new Y coordinate
     */
    @Override
    public void setY(double Y) {
        shape.setLayoutY(Y);
    }

    /**
     * Sets a new contour color for a shape
     *
     * @param newColor the new color to set
     */
    @Override
    public void setContourColor(Color newColor) {
        shape.setStroke(newColor);
    }

    /**
     * Updates the current shape as defined by the parameters
     *
     * @param X             the new X coordinate
     * @param Y             the new Y coordinate
     * @param internalColor the internal color
     * @param contourColor  the contour color
     */
    @Override
    public void draw(double X, double Y, Color internalColor, Color contourColor) {
        this.setX(X);
        this.setY(Y);
        this.setInternalColor(internalColor);
        this.setContourColor(contourColor);
    }

}
