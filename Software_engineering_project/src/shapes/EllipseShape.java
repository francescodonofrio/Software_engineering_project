package shapes;

import javafx.scene.shape.Ellipse;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

public class EllipseShape extends CloseContourShape {

    private double radiusX, radiusY;

    /**
     * Creates a new instance of  EllipseShape
     */
    public EllipseShape() {
        this.shape = new Ellipse();

        if (isBeingLoaded || inserted) {
            EllipseShape.cont++;
            inserted = false;
        }
        rotate = new Rotate();
        translate = new Translate();
        scale = new Scale();
        shape.getTransforms().add(rotate);
        shape.getTransforms().add(scale);
        shape.getTransforms().add(translate);

        this.name = "Ellipse " + EllipseShape.cont;
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

        ((Ellipse) shape).setRadiusX(radiusX);
        ((Ellipse) shape).setRadiusY(radiusY);
    }


    @Override
    public double getDimX() {
        return ((Ellipse) shape).getRadiusX();
    }

    @Override
    public double getDimY() {
        return ((Ellipse) shape).getRadiusY();
    }
}

