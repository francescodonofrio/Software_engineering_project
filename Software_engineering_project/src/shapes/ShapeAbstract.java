package shapes;

import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public abstract class ShapeAbstract implements ShapeInterface, java.lang.Cloneable {

    protected static int cont = 1;
    protected static boolean hasBeenInserted = false;
    protected static boolean isBeingLoaded = false;
    protected Shape shape;
    protected Color countourColor;
    protected String name;

    /**
     * Reset the static variable used for numering the created shapes
     */
    public static void resetCont() {
        cont = 1;
    }

    /**
     * Prepares the class for being read from file
     */
    public static void initializeLoad() {
        cont = 0;
        isBeingLoaded = true;
    }

    /**
     * Prepares the class for being used with the standard behaviour
     */
    public static void finalizeLoad() {
        isBeingLoaded = true;
    }

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
     * Sets the internal shape to shape
     *
     * @param shape the new shape
     */
    @Override
    public void setShape(Shape shape) {
        this.shape = shape;
    }

    /**
     * Sets a new contour color for a shape
     *
     * @param newColor the new color to set
     */
    @Override
    public void setContourColor(Color newColor) {
        this.countourColor = newColor;
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
    public void move(double X, double Y) {
        this.setX(X);
        this.setY(Y);
    }

    @Override
    public Color getCountourColor() {
        return countourColor;
    }

    /**
     * Returns the name contained in the current object
     *
     * @return the name contained
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Sets a new name for a shape
     *
     * @param name the new name
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set the focus of a shape
     *
     * @param value the status of the focus
     */
    @Override
    public void setFocus(boolean value) {
        Effect effect = null;
        if (value)
            effect = new DropShadow(BlurType.GAUSSIAN, Color.DODGERBLUE, 5, 0.75, 0, 0);
        this.shape.setEffect(effect);
    }

    @Override
    public ShapeAbstract clone() throws CloneNotSupportedException {
        ShapeAbstract temp = (ShapeAbstract) super.clone();
        String oldName = Integer.toString(cont);
        cont++;
        String newName = Integer.toString(cont);
        temp.name = temp.name.replace(oldName, newName);
        return temp;
    }

}
