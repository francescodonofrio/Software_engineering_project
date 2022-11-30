package shapes;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public abstract class ShapeAbstract implements ShapeInterface {

    protected Shape shape;
    protected String name = "CIAO";
    protected static int cont = 1;

    /**
     * Returns the shape contained in the current object
     *
     * @return the shape contained
     */
    @Override
    public Shape getShape() {
        return this.shape;
    }

    /**
     * Sets a new internal color for a shape
     *
     * @param newColor the new color to set
     */
    @Override
    public void setInternalColor(Color newColor) {
        shape.setFill(newColor);
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
     * Updates the current shape as defined by the parameters
     *
     * @param X             the new X coordinate
     * @param Y             the new Y coordinate
     * @param internalColor the internal color
     * @param contourColor  the contour color
     */
    @Override
    public void setProperties(double X, double Y, Color internalColor, Color contourColor) {
        this.setX(X);
        this.setY(Y);
        this.setInternalColor(internalColor);
        this.setContourColor(contourColor);
    }
    
    @Override
    public String getName(){
        return name;
    } 
    
    public static void resetCont() {
        cont = 1;
    }
    
    @Override
    public void setShape(Shape shape){
        this.shape = shape;
    }
}
