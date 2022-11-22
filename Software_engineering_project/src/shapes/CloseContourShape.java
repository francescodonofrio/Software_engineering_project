package shapes;

import javafx.scene.paint.Color;

import java.io.Serializable;

public abstract class CloseContourShape implements Shape, Serializable {

    protected javafx.scene.shape.Shape shape;
    
    /**
     * Returns the shape contained in the current object
     *
     * @return the shape contained
     */
    @Override
    public javafx.scene.shape.Shape getShape() {
        return this.shape;
    }

    /**
     * Sets a new internal color for a shape
     * @param newColor the new color to set
     */
    @Override
    public void setInternalColor(Color newColor){
        shape.setFill(newColor);
    }
    
    /**
     * Sets a new contour color for a shape
     * @param newColor the new color to set
     */
    @Override
    public void setContourColor(Color newColor){
        shape.setStroke(newColor);
    }
    /**
     * Sets the X coordinate for this shape
     * @param X the new X coordinate
     */
    @Override
    public void setX(double X) {
        shape.setLayoutX(X);
    }

    /**
     * Sets the Y coordinate for this shape
     * @param Y the new Y coordinate
     */
    @Override
    public void setY(double Y) {
        shape.setLayoutY(Y);
    }
}
