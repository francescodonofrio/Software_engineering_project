package shapes;

import javafx.scene.paint.Color;

public interface Shape {

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
    
    void draw(double X, double Y, Color internalColor, Color contourColor);
    
    void setDim(double initialDim1,double initialDim2, double finalDim1, double finalDim2);
}