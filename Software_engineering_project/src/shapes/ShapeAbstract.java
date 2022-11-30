package shapes;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public abstract class ShapeAbstract implements ShapeInterface {

    protected Shape shape;
    protected String name;
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
     * Moves the shape to a new position
     *
     * @param X the new X coordinate
     * @param Y the new Y coordinate
     */
    @Override
    public void move( double X, double Y){
        this.setX(X);
        this.setY(Y);
    }
     /**
      * Returns the name contained in the current object
      * 
      * @return the name contained
      */
    @Override
    public String getName(){
        return name;
    } 
    
    /**
     * Reset the static variable used for numering the created shapes
     */
    public static void resetCont() {
        cont = 1;
    }
    
    /**
     * Set the internal shape to shape
     * 
     * @param shape the new shape to set
     */
    @Override
    public void setShape(Shape shape){
        this.shape = shape;
    }
}
