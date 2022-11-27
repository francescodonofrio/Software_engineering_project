package shapes;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public interface ShapeInterface {

    /**
     * Sets the X coordinate for this shape
     *
     * @param X the new X coordinate
     */
    void setX(double X);

    /**
     * Sets the Y coordinate for this shape
     *
     * @param Y the new Y coordinate
     */
    void setY(double Y);

    /**
     * Returns the shape contained in the current object
     *
     * @return the shape contained
     */
    Shape getShape();

    /**
     * Sets a new internal color for a shape
     *
     * @param newColor the new color to set
     */
    void setInternalColor(Color newColor);

    /**
     * Sets a new contour color for a shape
     *
     * @param newColor the new color to set
     */
    void setContourColor(Color newColor);

    /**
     * Updates the current shape as defined by the parameters
     *
     * @param X             the new X coordinate
     * @param Y             the new Y coordinate
     * @param internalColor the internal color
     * @param contourColor  the contour color
     */
    void draw(double X, double Y, Color internalColor, Color contourColor);

    /**
     * Updates the dimentions of the shape
     *
     * @param initialX the initial X coordinate
     * @param initialY the initial Y coordinate
     * @param finalX   the final X coordinate
     * @param finalY   the final Y coordinate
     */
    void setDim(double initialX, double initialY, double finalX, double finalY);
}