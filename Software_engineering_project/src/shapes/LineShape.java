package shapes;

import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

public class LineShape extends OpenContourShape {

    /**
     * Creates a new instance of LineShape
     */
    public LineShape() {
        this.shape = new Line();

        if (isBeingLoaded || inserted) {
            LineShape.cont++;
            inserted = false;
        }
        rotate = new Rotate();
        translate = new Translate();
        scale = new Scale();
        shape.getTransforms().add(rotate);
        shape.getTransforms().add(scale);
        shape.getTransforms().add(translate);

        this.name = "Line " + LineShape.cont;
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
        ((Line) shape).setEndX(finalX - initialX);
        ((Line) shape).setEndY(finalY - initialY);
    }

    @Override
    public double getDimX() {
        return ((Line) shape).getEndX();
    }

    @Override
    public double getDimY() {
        return ((Line) shape).getEndY();
    }

}