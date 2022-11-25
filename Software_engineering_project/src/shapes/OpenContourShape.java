package shapes;

import javafx.scene.paint.Color;

import java.io.Serializable;

public abstract class OpenContourShape implements Shape, Serializable {
    protected javafx.scene.shape.Shape shape;

    @Override
    public javafx.scene.shape.Shape getShape() {
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
     * @param newColor the new color to set
     */
    @Override
    public void setContourColor(Color newColor){
        shape.setStroke(newColor);
    }
    
    @Override
    public void draw(double X, double Y, Color internalColor, Color contourColor){
        this.setX(X);
        this.setY(Y);
        this.setInternalColor(internalColor);
        this.setContourColor(contourColor);
    }

}
