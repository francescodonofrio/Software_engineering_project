package shapes;

import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class LineShape extends OpenContourShape {

    private Line line;
    
    /**
     * Creates a new instance of LineShape
     */
    public LineShape() {
        this.shape = new Line();
        this.line = (Line)shape;
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
        line.setEndX(finalX - initialX);
        line.setEndY(finalY - initialY);
    }
    
    /**
     * Set the internal implementation of javafx.scene.shape.Shape interface
     * 
     * @param shape the shape to be set
     */
    @Override
    public void setShape(Shape shape){
        this.shape = shape;
        this.line = (Line)shape;
    }
}