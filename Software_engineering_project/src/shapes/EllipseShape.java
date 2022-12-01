package shapes;

import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;

public class EllipseShape extends CloseContourShape {

    private double radiusX, radiusY;
    
    /**
     * Creates a new instance of  EllipseShape
     */
    public EllipseShape() {
        this.shape = new Ellipse();
        this.name = "Ellipse "+EllipseShape.cont;
        EllipseShape.cont++;
    }

    /**
     * Updates the dimentions of the shape
     *
     * @param initialX the initial X coordinate
     * @param initialY the initial Y coordinate
     * @param finalX   the final X coordinate
     * @param finalY   the final Y coordinate
     */
    @Override
    public void setDim(double initialX, double initialY, double finalX, double finalY) {
        radiusX = finalX - initialX;
        radiusY = finalY - initialY;
        if (radiusX < 0) {
            radiusX = -radiusX;
        }
        if (radiusY < 0) {
            radiusY = -radiusY;
        }

        ((Ellipse)shape).setRadiusX(radiusX);
        ((Ellipse)shape).setRadiusY(radiusY);
    }
    
    /**
     * Set the internal implementation of javafx.scene.shape.Shape interface
     * 
     * @param shape the shape to be set
     */
    @Override
    public void setShape(Shape shape){
        this.shape = shape;
    }
}
