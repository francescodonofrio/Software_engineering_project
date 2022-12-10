package shapes;

import exceptions.ShapeWithNullWidthException;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

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
     * Sets the internal shape to shape
     *
     * @param shape the new shape
     */
    void setShape(Shape shape);

    /**
     * Returns the name of the current object
     *
     * @return the name
     */
    String getName();

    /**
     * Sets a new name for a shape
     *
     * @param name the new name
     */
    void setName(String name);

    /**
     * Sets a new contour color for a shape
     *
     * @param newColor the new color to set
     */
    void setContourColor(Color newColor);

    /**
     * Retur the contour color of that shape
     *
     * @return Color
     */
    Color getCountourColor();

    /**
     * Retur the internal color of that shape
     *
     * @return Color
     */
    Color getInternalColor();

    /**
     * Sets a new internal color for a shape
     *
     * @param newColor the new color to set
     */
    void setInternalColor(Color newColor);

    /**
     * Updates the current shape as defined by the parameters
     *
     * @param X             the new X coordinate
     * @param Y             the new Y coordinate
     * @param internalColor the internal color
     * @param contourColor  the contour color
     */
    void setProperties(double X, double Y, Color internalColor, Color contourColor);

    /**
     * Updates the dimentions of the shape
     *
     * @param initialX the initial X coordinate
     * @param initialY the initial Y coordinate
     * @param finalX   the final X coordinate
     * @param finalY   the final Y coordinate
     */
    void setDim(double initialX, double initialY, double finalX, double finalY);

    /**
     * Moves the shape to a new position
     *
     * @param X the new X coordinate
     * @param Y the new Y coordinate
     */
    void move(double X, double Y);

    /**
     * Set the focus of a shape
     *
     * @param value the status of the focus
     */
    void setFocus(boolean value);
    
    /**
     * Return the dimension along the x-axis
     * 
     * @return the dimension of the shape along the x-axis
     */
    double getDimX();
    
    /**
      * Return the dimension along the y-axis
     * 
     * @return the dimension of the shape along the y-axis
     */
    double getDimY();
    
    /**
     * Set the shape inserted property of the shape
     * 
     * @param inserted the status of the insertion
     */
    void setInserted(boolean inserted);
    
    /**
     * Return the shape inserted property of the shape
     * 
     * @return return the shape inserted property of the shape
     */
    boolean getInserted();
    
    /**
     * 
     * @return the rotate transform applicated to the shape
     */
    Rotate getRotate();
    
    /**
     * Set the rotate property
     * 
     * @param rotate
     */
    void setRotate(Rotate rotate);
    
    /**
     * 
     * @return the scale transform applicated to the shape
     */
    Scale getScale();
    
    /**
     * Set the scale property
     * 
     * @param scale
     */
    void setScale(Scale scale);
    
    /**
     * 
     * @return the translate transform applicated to the shape
     */
    Translate getTranslate();
    
    /**
     * Set the translate property
     * 
     * @param translate
     */
    void setTranslate(Translate translate);
    
    /**
     * Stretch the shape along the x-axis
     * 
     * @param X the point along the x-axis for whom stretch the shape
     * @throws exceptions.ShapeWithNullWidthException
     */
    void setStretchX(double X) throws ShapeWithNullWidthException;
    
    /**
     * Rotate the shape of passed parameter
     * 
     * @param angle the angle of rotation
     */
    void setRotation(double angle);
    
    /**
     * 
     * @return the angle of rotation of the shape
     */
    double getRotation();
}