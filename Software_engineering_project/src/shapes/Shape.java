package shapes;

import javafx.scene.paint.Color;

public interface Shape {

    /**
     * A function to make a shape focussed
     */
    void focus();

    /**
     * A function that copies the current shape on the clipboard passed as an argument.
     *
     * @param clipboard the clipboard where to save the current shape
     */
    void copy(javafx.scene.shape.Shape clipboard);

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
    javafx.scene.shape.Shape getShape();

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
}