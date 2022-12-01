package shapes;

import javafx.scene.shape.Line;

public class LineShape extends OpenContourShape {
    
    /**
     * Creates a new instance of LineShape
     */
    public LineShape() {
        this.shape = new Line();
        this.shape.setStrokeWidth(2);
        this.name = "Line "+LineShape.cont;
        LineShape.cont++;
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
        ((Line)shape).setEndX(finalX - initialX);
        ((Line)shape).setEndY(finalY - initialY);
    }
}