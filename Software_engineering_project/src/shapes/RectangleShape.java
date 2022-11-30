package shapes;


import javafx.scene.shape.Rectangle;

public class RectangleShape extends CloseContourShape {

    private double width, height;
    private final Rectangle rectangle;
    
    /**
     * Creates a new instance of RectangleShape
     */
    public RectangleShape() {
        this.shape = new Rectangle();
        this.rectangle = (Rectangle)shape;
        this.name = "Rectangle "+RectangleShape.cont;
        RectangleShape.cont++;
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
            rectangle.setLayoutX(finalX);
        }
        if (height < 0) {
            height = -height;
            rectangle.setLayoutY(finalY);
        }

        rectangle.setWidth(width);
        rectangle.setHeight(height);
    }
}