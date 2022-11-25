package shapes;

import serializedIO.SerializableEllipse;

public class EllipseShape extends CloseContourShape {

    /**
     * Creates a new instance of  EllipseShape
     */
    public EllipseShape() {
        this.shape = new SerializableEllipse();
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
        javafx.scene.shape.Ellipse ellipse = (javafx.scene.shape.Ellipse) shape;
        double radiusX = finalX - initialX;
        double radiusY = finalY - initialY;
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
