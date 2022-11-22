package shapes;

import javafx.scene.paint.Color;

import java.io.Serializable;

public abstract class CloseContourShape implements Shape, Serializable {

    protected javafx.scene.shape.Shape shape;

    /**
     * A function to make a shape focussed
     */
    @Override
    public void focus() {

    }

    /**
     * A function that copies the current shape on the clipboard passed as an argument.
     *
     * @param clipboard the clipboard where to save the current shape
     */
    @Override
    public void copy(javafx.scene.shape.Shape clipboard) {
        clipboard = this.shape;
    }

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
     * Sets a new color for a shape
     * @param newColor the new color to set
     */
    public void setInternalColor(Color newColor){
        shape.setFill(newColor);
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
