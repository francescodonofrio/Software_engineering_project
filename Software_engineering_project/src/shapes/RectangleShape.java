package shapes;


import javafx.scene.shape.Rectangle;

public class RectangleShape extends CloseContourShape {

    private double width, height;

    /**
     * Creates a new instance of RectangleShape
     */
    public RectangleShape() {
        this.shape = new Rectangle();

        if (isBeingLoaded || hasBeenInserted) {
            RectangleShape.cont++;
            hasBeenInserted = false;
        }

        this.name = "Rectangle " + RectangleShape.cont;
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
        width = finalX - initialX;
        height = finalY - initialY;
        if (width < 0) {
            width = -width;
            shape.setLayoutX(finalX);
        }
        if (height < 0) {
            height = -height;
            shape.setLayoutY(finalY);
        }

        ((Rectangle) shape).setWidth(width);
        ((Rectangle) shape).setHeight(height);

        hasBeenInserted = true;
    }
}