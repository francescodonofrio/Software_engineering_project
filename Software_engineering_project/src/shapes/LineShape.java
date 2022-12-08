package shapes;

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
        double boundX;
        if(shape.getRotate() != 0){
            if((shape.getRotate() >= 0 && shape.getRotate() <= 90) || (shape.getRotate() >= -180 && shape.getRotate() < -135))
                boundX = shape.getBoundsInParent().getMinX();
            else
                boundX = shape.getBoundsInParent().getMaxX();
            shape.setLayoutX(boundX);
            shape.setLayoutY(shape.getBoundsInParent().getMinY());
            initialX = boundX;
            initialY = shape.getBoundsInParent().getMinY();
//            ((Line) shape).setEndX(finalX - boundX);
//            ((Line) shape).setEndY(finalY - shape.getBoundsInParent().getMinY());
            shape.setRotate(0);
        }
        ((Line) shape).setEndX(finalX - initialX);
        ((Line) shape).setEndY(finalY - initialY);
        
        hasBeenInserted = true;
    }
}