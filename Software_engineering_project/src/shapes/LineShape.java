package shapes;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class LineShape extends OpenContourShape {

    /**
     * Creates a new instance of LineShape
     */
    public LineShape() {
        this.shape = new Line();

        if (isBeingLoaded || hasBeenInserted) {
            LineShape.cont++;
            hasBeenInserted = false;
        }

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

        hasBeenInserted = true;
    }
}