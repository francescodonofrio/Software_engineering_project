package shapes;

import javafx.scene.paint.Color;

public abstract class CloseContourShape extends ShapeAbstract {

    private Color internalColor;

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
        this.internalColor = internalColor;
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
        return internalColor;
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


}
