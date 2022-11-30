package shapes;

import javafx.scene.shape.Ellipse;

public class EllipseShape extends CloseContourShape {

    private double radiusX, radiusY;
    private final Ellipse ellipse;
    
    /**
     * Creates a new instance of  EllipseShape
     */
    public EllipseShape() {
        this.shape = new Ellipse();
        this.ellipse = (Ellipse)this.shape;
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

        ellipse.setRadiusX(radiusX);
        ellipse.setRadiusY(radiusY);
    }

}
