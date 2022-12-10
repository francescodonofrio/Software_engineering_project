package shapes;

import exceptions.ShapeWithNullWidthException;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

public abstract class ShapeAbstract implements ShapeInterface{

    protected static int cont = 1;
    protected static boolean inserted = false;
    protected static boolean isBeingLoaded = false;
    protected Shape shape;
    protected String name;
    protected Translate translate = null;
    protected Rotate rotate = null;
    protected Scale scale = null;

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
    private Rotate scalle;

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

    /**
     * Get the contour color of the shape
     * 
     * @return the contour color of the shape
     */
    @Override
    public Color getCountourColor() {
        return (Color)shape.getStroke();
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
    
    /**
     * Get the internal color of the shape
     * 
     * @return the internal color of the shape
     */
    @Override
    public Color getInternalColor() {
       return (Color)shape.getFill();
    }

    /**
     * Set the shape inserted property of the shape
     * 
     * @param inserted the status of the insertion
     */
    @Override
    public void setInserted(boolean inserted) {
        this.inserted = inserted;
    }
    
    /**
     * Return the shape inserted property of the shape
     * 
     * @return return the shape inserted property of the shape
     */
    @Override
    public boolean getInserted(){
        return inserted;
    }
    
    /**
     * 
     * @return the Rotate transform applicated to the shape
     */
    @Override
    public Rotate getRotate(){
        return rotate;
    }
    
    /**
     * Set the rotation property to rotate
     * 
     * @param rotate
     */
    @Override
    public void setRotate(Rotate rotate){
        this.rotate = rotate;
    }
    
    /**
     * 
     * @return the scale transform applicated to the shape
     */
    @Override
    public Scale getScale(){
        return this.scale;
    }
    
    /**
     * Set the scale property
     * 
     * @param scale
     */
    @Override
    public void setScale(Scale scale){
        this.scale = scale;
    }
    
    /**
     * 
     * @return the translate transform applicated to the shape
     */
    @Override
    public Translate getTranslate(){
        return this.translate;
    }
    
    /**
     * Set the translate property
     * 
     * @param translate
     */
    @Override
    public void setTranslate(Translate translate){
        this.translate = translate;
    }
    
    /**
     * Stretch the shape along the x-axis
     * 
     * @param X the point along the x-axis for whom stretch the shape
     * @throws exceptions.ShapeWithNullWidthException
     */
    @Override
    public void setStretchX(double X) throws ShapeWithNullWidthException{
        double width = this.getDimX();
        if(width == 0)
            throw new ShapeWithNullWidthException();
        double halfWidth = width/2;
        double stretchOffset = Math.abs((2*(X-width))/halfWidth);
        if(stretchOffset < 1)
            stretchOffset = 1;
        if(X > -Math.abs(width))
            scale.setX(stretchOffset);
    }
    
    /**
     * Rotate the shape of passed parameter
     * 
     * @param angle the angle of rotation
     */
    @Override
    public void setRotation(double angle){
        this.rotate.setAngle(angle);
    }
    
    /**
     * 
     * @return the angle of rotation of the shape
     */
    @Override
    public double getRotation(){
        return rotate.getAngle();
    }
}
